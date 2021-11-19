/*
 * Copyright (C) 2021 Sonicle S.r.l.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License version 3 as published by
 * the Free Software Foundation with the addition of the following permission
 * added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED
 * WORK IN WHICH THE COPYRIGHT IS OWNED BY SONICLE, SONICLE DISCLAIMS THE
 * WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301 USA.
 *
 * You can contact Sonicle S.r.l. at email address sonicle[at]sonicle[dot]com
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License version 3.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License
 * version 3, these Appropriate Legal Notices must retain the display of the
 * Sonicle logo and Sonicle copyright notice. If the display of the logo is not
 * reasonably feasible for technical reasons, the Appropriate Legal Notices must
 * display the words "Copyright (C) 2021 Sonicle S.r.l.".
 */
package com.sonicle.webtop.tasks.io;

import com.sonicle.commons.InternetAddressUtils;
import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.core.app.util.log.BufferingLogHandler;
import com.sonicle.webtop.core.app.util.log.LogEntry;
import com.sonicle.webtop.core.app.util.log.LogHandler;
import com.sonicle.webtop.core.app.util.log.LogMessage;
import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.core.util.ICal4jUtils;
import com.sonicle.webtop.core.util.ICalendarUtils;
import com.sonicle.webtop.tasks.model.TaskBase;
import com.sonicle.webtop.tasks.model.TaskRecurrence;
import jakarta.mail.internet.InternetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.PercentComplete;
import net.fortuna.ical4j.model.property.Priority;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Status;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public class ICalendarInput implements TasksStreamReader {
	private DateTimeZone defaultTimezone;
	private boolean defaultIsPrivate = false;
	private boolean returnCalendarObject = false;
	private Map<String, String> categoriesToTagsMap = null;
	private LogHandler logHandler = null;
	
	public ICalendarInput(DateTimeZone defaultTimezone) {
		this.defaultTimezone = defaultTimezone;
	}
	
	public ICalendarInput withDefaultTimezone(DateTimeZone defaultTimezone) {
		this.defaultTimezone = defaultTimezone;
		return this;
	}
	
	public ICalendarInput withDefaultIsPrivate(boolean defaultIsPrivate) {
		this.defaultIsPrivate = defaultIsPrivate;
		return this;
	}
	
	public ICalendarInput withReturnCalendarObject(boolean returnCalendarObject) {
		this.returnCalendarObject = returnCalendarObject;
		return this;
	}
	
	public ICalendarInput withCategoriesToTagsMap(Map<String, String> categoriesToTagsMap) {
		this.categoriesToTagsMap = categoriesToTagsMap;
		return this;
	}
	
	public ICalendarInput withLogHandler(LogHandler logHandler) {
		this.logHandler = logHandler;
		return this;
	}
	
	@Override
	public List<TaskInput> read(InputStream is) throws IOException, WTException {
		return parseICalendar(is);
	}
	
	public ArrayList<TaskInput> parseICalendar(InputStream is) throws IOException, WTException {
		try {
			return parseToDoObjects(ICalendarUtils.parse(is));
		} catch (ParserException ex) {
			throw new IOException("Unable to parse", ex);
		}	
	}
	
	public ArrayList<TaskInput> parseToDoObjects(Calendar calendar) throws WTException {
		// http://www.kanzaki.com/docs/ical/
		ArrayList<TaskInput> results = new ArrayList<>();
		
		int count = 0;
		for (Iterator xi = calendar.getComponents().iterator(); xi.hasNext();) {
			final Component component = (Component) xi.next();
			if (component instanceof VToDo) {
				count++;
				final VToDo vtd = (VToDo)component;
				final int vtdNo = count;
				
				BufferingLogHandler buffLogHandler = null;
				if (logHandler != null) {
					buffLogHandler = new BufferingLogHandler() {
						@Override
						public List<LogEntry> first() {
							return Arrays.asList(new LogMessage(0, LogEntry.Level.INFO, "VTODO #{} [{}]", vtdNo, ICal4jUtils.printDump(vtd)));
						}
					};
				}
				
				try {
					final TaskInput result = parseToDoObject(vtd, buffLogHandler);
					//if (result.task.trimFieldLengths()) {
					//	log(buffLogHandler, 1, LogEntry.Level.WARN, "Some fields were truncated due to max-length");
					//}
					results.add(result);
					
				} catch(Throwable t) {
					log(buffLogHandler, 0, LogEntry.Level.ERROR, "Reason: {}", LangUtils.getThrowableMessage(t));
				}
				
				if (logHandler != null && buffLogHandler != null) {
					final List<LogEntry> entries = buffLogHandler.flush();
					if (entries != null) logHandler.handle(entries);
				}
			}
		}
		return results;
	}
	
	public TaskInput parseToDoObject(VToDo vtodo) throws WTException {
		return parseToDoObject(vtodo, null);
	}
	
	private TaskInput parseToDoObject(VToDo vtodo, LogHandler logHandler) throws WTException {
		// https://www.kanzaki.com/docs/ical/vtodo.html
		TaskBase task = new TaskBase();
		TaskRecurrence taskRecurrence = null;
		Set<String> tagNames = null;
		String relatedToUid = null;
		
		//TODO: pass string field lengths in constructor or take them from db field definitions
		
		// UID: globally unique identifier
		// http://www.kanzaki.com/docs/ical/uid.html
		String uid = ICal4jUtils.getPropertyValue(vtodo.getUid());
		if (!StringUtils.isBlank(uid)) {
			task.setPublicUid(uid);
		} else {
			log(logHandler, 1, LogEntry.Level.WARN, "Uid is missing");
		}
		
		// ORGANIZER: who have organized the entity
		// https://www.kanzaki.com/docs/ical/organizer.html
		String owner = toTaskOrganizer(vtodo.getOrganizer());
		if (!StringUtils.isBlank(owner)) {
			task.setOrganizer(owner);
		} else {
			log(logHandler, 1, LogEntry.Level.WARN, "Organizer invalid or empty [{}]", ICal4jUtils.getPropertyValue(vtodo.getOrganizer()));
		}
		
		// CREATED: the date and time when the entity was created in the store
		// https://www.kanzaki.com/docs/ical/created.html
		task.setCreationTimestamp(ICalendarUtils.getPropertyValueAsDateTime(vtodo.getCreated(), org.joda.time.DateTimeZone.UTC));
		
		// LAST-MODIFIED: the date and time when the entity was last-revised in the store
		// http://www.kanzaki.com/docs/ical/lastModified.html
		task.setRevisionTimestamp(ICalendarUtils.getPropertyValueAsDateTime(vtodo.getLastModified(), org.joda.time.DateTimeZone.UTC));
		
		// SEQUENCE: the revision sequence number
		// http://www.kanzaki.com/docs/ical/sequence.html
		Sequence sequence = vtodo.getSequence();
		if (sequence != null) {
			task.setRevisionSequence(sequence.getSequenceNo());
		}
		
		// SUMMARY: a brief description of the entity
		// https://www.kanzaki.com/docs/ical/summary.html
		String summary = ICal4jUtils.getPropertyValue(vtodo.getSummary());
		if (!StringUtils.isBlank(summary)) {
			task.setSubject(summary);
		} else {
			task.setSubject("");
			log(logHandler, 1, LogEntry.Level.WARN, "Subject is empty");
		}
		
		// LOCATION: the intended venue for the activity
		// http://www.kanzaki.com/docs/ical/location.html
		String location = ICal4jUtils.getPropertyValue(vtodo.getLocation());
		if (!StringUtils.isBlank(location)) {
			task.setLocation(location);
		} else {
			task.setLocation(null);
		}
		
		// DTSTART: when the todo begins
		// https://www.kanzaki.com/docs/ical/dtstart.html
		org.joda.time.DateTime start = ICal4jUtils.toJodaDateTime(ICal4jUtils.getDatePropertyValue(vtodo.getStartDate()), defaultTimezone);
		task.setStart(start);
		
		// DUE: when the todo is expected to be completed
		// https://www.kanzaki.com/docs/ical/due.html
		org.joda.time.DateTime due = ICal4jUtils.toJodaDateTime(ICal4jUtils.getDatePropertyValue(vtodo.getDue()), defaultTimezone);
		task.setDue(due);
		
		// DURATION: duration of the todo (alternative to DUE) -- NOT SUPPORTED
		// https://www.kanzaki.com/docs/ical/duration.html
		if (vtodo.getDuration() != null) {
			log(logHandler, 1, LogEntry.Level.WARN, "Duration is NOT supported");
		}
		
		// DESCRIPTION: the complete description
		// http://www.kanzaki.com/docs/ical/description.html
		String description = ICal4jUtils.getPropertyValue(vtodo.getDescription());
		if (!StringUtils.isBlank(description)) {
			task.setDescription(description);
		} else {
			task.setDescription(null);
		}
		//TODO: add support to X-ALT-DESC for HTML descriptions
		
		// COMPLETED: when to-do was actually completed
		// https://www.kanzaki.com/docs/ical/completed.html
		task.setCompletedOn(ICalendarUtils.getPropertyValueAsDateTime(vtodo.getDateCompleted(), org.joda.time.DateTimeZone.UTC));
		
		// PERCENT-COMPLETE: percent completion of a to-do
		// https://www.kanzaki.com/docs/ical/percentComplete.html
		PercentComplete percent = (PercentComplete)vtodo.getPercentComplete();
		if (percent != null) {
			task.setProgress((short)percent.getPercentage());
		}
		
		// STATUS: the overall status or confirmation
		// https://www.kanzaki.com/docs/ical/status.html
		task.setStatus(toTaskStatus(vtodo.getStatus()));
		
		// PRIORITY: the relative priority
		// https://www.kanzaki.com/docs/ical/priority.html
		task.setImportance((short)toTaskPriority(vtodo.getPriority()));
		
		// CLASS: capture the scope of the access the owner intends for information
		// https://www.kanzaki.com/docs/ical/class.html
		// https://appgenix.uservoice.com/forums/280499-business-calendar-2/suggestions/18698599-i-would-like-to-see-another-privacy-option-confid
		String clazz = ICal4jUtils.getPropertyValue(vtodo.getClassification());
		if (!StringUtils.isBlank(location)) {
			// CONFIDENTIAL or PRIVATE are private synonyms
			task.setIsPrivate(StringUtils.equals(clazz, Clazz.CONFIDENTIAL.getValue()) || StringUtils.equals(clazz, Clazz.PRIVATE.getValue()));
		} else {
			task.setIsPrivate(defaultIsPrivate);
		}
		
		// RRULE, EXDATE: defines the repeating pattern and the list of exceptions
		// https://www.kanzaki.com/docs/ical/rrule.html
		// https://www.kanzaki.com/docs/ical/exdate.html
		ICalendarUtils.RecurInfo recurInfo = ICalendarUtils.extractRecurInfo(vtodo);
		if (recurInfo.recur != null && start != null) {
			taskRecurrence = new TaskRecurrence(recurInfo.recur.toString(), start, recurInfo.exDates);
		} else if (recurInfo.recur != null) {
			log(logHandler, 1, LogEntry.Level.WARN, "Recurrence rule ignored: Start is missing");
		}
		
		// RECURRENCE-ID: identifies a specific instance of a recurring master entry
		// https://www.kanzaki.com/docs/ical/recurrenceId.html
		ICalendarUtils.RecurringRefs recurringRefs = ICalendarUtils.extractRecurringRefs(vtodo);
		
		// CATEGORIES: specified categories or tags
		// https://www.kanzaki.com/docs/ical/categories.html
		tagNames = ICalendarUtils.toCategoriesSet((Categories)vtodo.getProperty(Property.CATEGORIES));
		
		// RELATED-TO: represent a relationship or reference between one entry to another
		// https://www.kanzaki.com/docs/ical/relatedTo.html
		relatedToUid = ICal4jUtils.getPropertyValue(vtodo.getProperty(Property.RELATED_TO));
		
		// RDATE, EXRULE, RSTATUS -> Not Supported!
		// ATTACH, ATTENDEE -> Ignored!
		
		Set<String> names = new LinkedHashSet(Arrays.asList(Property.CONTACT, Property.GEO, Property.URL, Property.COMMENT, Property.RESOURCES));
		PropertyList extraProps = ICalendarUtils.extractProperties(vtodo, names, true, null);
		
		return new TaskInput(task, taskRecurrence, recurringRefs, tagNames, relatedToUid, extraProps, returnCalendarObject ? vtodo : null);
	}
	
	public String toTaskOrganizer(Organizer organizer) {
		// http://www.kanzaki.com/docs/ical/organizer.html
		
		if (organizer != null) {
			// Extract email and common name (CN)
			// Eg: CN=Henry Cabot:MAILTO:hcabot@host2.com -> drop ":MAILTO:"
			Cn cn = (Cn)organizer.getParameter(Parameter.CN);
			URI uri = organizer.getCalAddress();
			if (uri != null) {
				String address = uri.getSchemeSpecificPart();
				InternetAddress ia = InternetAddressUtils.toInternetAddress(address, (cn == null) ? address : cn.getValue());
				return InternetAddressUtils.toFullAddress(ia);
			}
		}
		return null;
	}
	
	public TaskBase.Status toTaskStatus(Status status) {
		// https://www.kanzaki.com/docs/ical/status.html
		/*
			Implemented mapping:
			 - NULL -> "unspecified" / "waiting" (WA)
			 - NEEDS_ACTION -> "require action" / "not started" (NA)
			 - CANCELLED -> "cancelled" / "deferred" (CA)
			 - IN_PROCESS -> "in progress" (IP)
			 - COMPLETED -> "completed" (CO)
		*/
		if (status == null) {
			return TaskBase.Status.WAITING;
		} else if (Status.VTODO_NEEDS_ACTION.equals(status)) {
			return TaskBase.Status.NEEDS_ACTION;
		} else if (Status.VTODO_COMPLETED.equals(status)) {
			return TaskBase.Status.COMPLETED;
		} else if (Status.VTODO_IN_PROCESS.equals(status)) {
			return TaskBase.Status.IN_PROGRESS;
		} else if (Status.VTODO_CANCELLED.equals(status)) {
			return TaskBase.Status.CANCELLED;
		} else {
			return null;
		}
	}
	
	public int toTaskPriority(Priority priority) {
		// https://www.kanzaki.com/docs/ical/priority.html
		/*
			There are 3 schemes:
			 1) Full range (0..9) -> 0: undefined, 1: high, 2..9: second-high..lowest
			 2) Three-Levels -> 1..4: high, 5: normal/medium, 6..9: low
			 3) A1, A2... -> UNSUPPORTED!!
		
			IW -> low: 9, medium: 0, high: 1
			TB -> low: 9, normal: 5, high: 1, undefined: 0
		
			Implemented mapping:
			 - null, 0 -> 5 (normal)
		     - level -> level
			 We use 9 (low), 5 (normal), 1 (high)
		*/
		if (priority == null) {
			return 5;
		} else {
			int level = priority.getLevel();
			return level == 0 ? 5 : level;
		}
	}
	
	/*
	Set<String> catNames = ICalendarUtils.toCategoriesSet((Categories)vtodo.getProperty(Property.CATEGORIES));
		if (categoriesToTagsMap != null && catNames != null) {
			for (String catName : catNames) {
				String tagId = categoriesToTagsMap.get(catName);
				if (tagId != null) {
					
				} else {
					//TODO: log warning categories is lost
				}
			}
		} else if (catNames != null) {
			//TODO: log warning categories are lost
		}
	*/
	
	private void log(LogHandler logHandler, int depth, LogEntry.Level level, String message, Object... arguments) {
		if (logHandler != null) {
			try {
				logHandler.handle(new LogMessage(depth, level, message, arguments));
			} catch(Throwable t) {}
		}
	}
}

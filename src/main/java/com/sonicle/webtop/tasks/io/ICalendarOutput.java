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

import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.core.app.util.log.BufferingLogHandler;
import com.sonicle.webtop.core.app.util.log.LogEntry;
import com.sonicle.webtop.core.app.util.log.LogHandler;
import com.sonicle.webtop.core.app.util.log.LogMessage;
import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.core.util.ICal4jUtils;
import com.sonicle.webtop.core.util.ICalendarUtils;
import com.sonicle.webtop.tasks.model.TaskBase;
import com.sonicle.webtop.tasks.model.TaskEx;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Completed;
import net.fortuna.ical4j.model.property.Created;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Due;
import net.fortuna.ical4j.model.property.LastModified;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.PercentComplete;
import net.fortuna.ical4j.model.property.Priority;
import net.fortuna.ical4j.model.property.RelatedTo;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Uid;
import net.sf.qualitycheck.Check;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

/**
 *
 * @author malbinola
 */
public class ICalendarOutput {
	private final String prodId;
	private TagsMappingMode tagsMappingMode = TagsMappingMode.NAME;
	private Map<String, String> tagNamesByIdMap = null;
	private LogHandler logHandler = null;
	
	public ICalendarOutput(String prodId) {
		this.prodId = Check.notNull(prodId, "prodId");
	}
	
	public ICalendarOutput withTagsMappingMode(TagsMappingMode tagsMappingMode) {
		this.tagsMappingMode = Check.notNull(tagsMappingMode, "tagsMappingMode");
		return this;
	}
	
	public ICalendarOutput withTagNamesByIdMap(Map<String, String> tagNamesByIdMap) {
		this.tagNamesByIdMap = tagNamesByIdMap;
		return this;
	}
	
	public ICalendarOutput withLogHandler(LogHandler logHandler) {
		this.logHandler = logHandler;
		return this;
	}
	
	public String writeICalendar(TaskEx task, PropertyList extraProps, String relatedToUid) throws WTException, IOException {
		return ICalendarUtils.print(createToDoObjectsCollection(Arrays.asList(new TaskOutput(task, extraProps, relatedToUid))));
	}
	
	public Calendar createToDoObjectsCollection(Collection<TaskOutput> outputs) throws WTException {
		Calendar ical = ICalendarUtils.newCalendar(prodId, null);
		
		int count = 0;
		for (TaskOutput output : outputs) {
			count++;
			final TaskEx task = output.task;
			final int taskNo = count;
			
			BufferingLogHandler buffLogHandler = null;
			if (logHandler != null) {
				buffLogHandler = new BufferingLogHandler() {
					@Override
					public List<LogEntry> first() {
						return Arrays.asList(new LogMessage(0, LogEntry.Level.INFO, "Task #{} [{}, {}]", taskNo, task.getPublicUid(), task.getSubject()));
					}
				};
			}
			
			try {
				ical.getComponents().add(createToDoObject(output.task, output.extraProps, output.relatedToUid, logHandler));
			} catch(Throwable t) {
				log(buffLogHandler, 0, LogEntry.Level.ERROR, "Reason: {}", LangUtils.getThrowableMessage(t));
			}
			
			if (logHandler != null && buffLogHandler != null) {
				final List<LogEntry> entries = buffLogHandler.flush();
				if (entries != null) logHandler.handle(entries);
			}
		}
		return ical;
	}
	
	public VToDo createToDoObject(TaskEx task, PropertyList extraProps, String relatedToUid) throws WTException {
		return createToDoObject(task, extraProps, relatedToUid, null);
	}
	
	private VToDo createToDoObject(TaskEx task, PropertyList extraProps, String relatedToUid, LogHandler logHandler) throws WTException {
		VToDo vtodo = new VToDo();
		
		// UID: globally unique identifier
		// http://www.kanzaki.com/docs/ical/uid.html
		ICal4jUtils.addProperty(vtodo, new Uid(task.getPublicUid()));
		
		// ORGANIZER: who have organized the entity
		// https://www.kanzaki.com/docs/ical/organizer.html
		Organizer organizer = ICalendarUtils.toOrganizerProp(task.getOrganizerAddress(), task.getOrganizerCN());
		if (organizer != null) ICal4jUtils.addProperty(vtodo, organizer);
		
		// CREATED: the date and time when the entity was created in the store
		// https://www.kanzaki.com/docs/ical/created.html
		ICal4jUtils.addProperty(vtodo, new Created(ICal4jUtils.toIC4jDateTimeUTC(task.getCreationTimestamp())));
		
		// LAST-MODIFIED: the date and time when the entity was last-revised in the store
		// http://www.kanzaki.com/docs/ical/lastModified.html
		ICal4jUtils.addProperty(vtodo, new LastModified(ICal4jUtils.toIC4jDateTimeUTC(task.getRevisionTimestamp())));
		
		// SEQUENCE: the revision sequence number
		// http://www.kanzaki.com/docs/ical/sequence.html
		ICal4jUtils.addProperty(vtodo, new Sequence(task.getRevisionSequence()));
		
		// SUMMARY: a brief description of the entity
		// https://www.kanzaki.com/docs/ical/summary.html
		ICal4jUtils.addProperty(vtodo, new Summary(task.getSubject()));
		
		// LOCATION: the intended venue for the activity
		// http://www.kanzaki.com/docs/ical/location.html
		if (!StringUtils.isEmpty(task.getLocation())) {
			ICal4jUtils.addProperty(vtodo, new Location(task.getLocation()));
		}
		
		// DTSTART: when the todo begins
		// https://www.kanzaki.com/docs/ical/dtstart.html
		if (task.getStart() != null) {
			ICal4jUtils.addProperty(vtodo, new DtStart(ICal4jUtils.toIC4jDateTimeUTC(task.getStart())));
		}
		
		// DUE: when the todo is expected to be completed
		// https://www.kanzaki.com/docs/ical/due.html
		if (task.getDue() != null) {
			ICal4jUtils.addProperty(vtodo, new Due(ICal4jUtils.toIC4jDateTimeUTC(task.getDue())));
		}
		
		// DESCRIPTION: the complete description
		// http://www.kanzaki.com/docs/ical/description.html
		if (!StringUtils.isEmpty(task.getDescription())) {
			ICal4jUtils.addProperty(vtodo, new Description(task.getDescription()));
		}
		//TODO: add support to X-ALT-DESC for HTML descriptions
		
		// COMPLETED: when to-do was actually completed
		// https://www.kanzaki.com/docs/ical/completed.html
		if (task.getCompletedOn() != null) {
			ICal4jUtils.addProperty(vtodo, new Completed(ICal4jUtils.toIC4jDateTimeUTC(task.getCompletedOn())));
		}
		
		// PERCENT-COMPLETE: percent completion of a to-do
		// https://www.kanzaki.com/docs/ical/percentComplete.html
		ICal4jUtils.addProperty(vtodo, new PercentComplete(task.getProgress()));
		
		// STATUS: the overall status or confirmation
		// https://www.kanzaki.com/docs/ical/status.html
		Status status = toToDoStatus(task.getStatus());
		if (status != null) {
			ICal4jUtils.addProperty(vtodo, status);
		}
		
		// PRIORITY: the relative priority
		// https://www.kanzaki.com/docs/ical/priority.html
		ICal4jUtils.addProperty(vtodo, new Priority(task.getImportance()));
		
		// CLASS: capture the scope of the access the owner intends for information
		// https://www.kanzaki.com/docs/ical/class.html
		// https://appgenix.uservoice.com/forums/280499-business-calendar-2/suggestions/18698599-i-would-like-to-see-another-privacy-option-confid
		ICal4jUtils.addProperty(vtodo, task.getIsPrivate() ? Clazz.CONFIDENTIAL : Clazz.PUBLIC);
		
		// RRULE, EXDATE: defines the repeating pattern and the list of exceptions
		// https://www.kanzaki.com/docs/ical/rrule.html
		// https://www.kanzaki.com/docs/ical/exdate.html
		if (task.hasRecurrence()) {
			Recur recur = task.getRecurrence().getRuleRecur();
			if (recur != null) {
				if (task.getRecurrence().getExcludedDates() != null) {
					LocalTime startTime = task.getStart().withZone(DateTimeZone.UTC).toLocalTime();
					vtodo.getProperties().add(ICal4jUtils.toIC4jExDate(task.getRecurrence().getExcludedDates(), startTime, DateTimeZone.UTC, true));
				}
			} else {
				log(logHandler, 1, LogEntry.Level.WARN, "Recur rule is null");
			}
		}
		
		// CATEGORIES: specified categories or tags
		// https://www.kanzaki.com/docs/ical/categories.html
		if (task.hasTags() && TagsMappingMode.NAME.equals(tagsMappingMode) && tagNamesByIdMap != null) {
			Categories categories = ICalendarUtils.toCategories(mapCategoryNames(task.getTags(), logHandler));
			if (categories != null) vtodo.getProperties().add(categories);
		} else if (task.hasTags() && TagsMappingMode.ID.equals(tagsMappingMode)) {
			Categories categories = ICalendarUtils.toCategories(task.getTags());
			if (categories != null) vtodo.getProperties().add(categories);
		}
		
		// RELATED-TO: represent a relationship or reference between one entry to another
		// https://www.kanzaki.com/docs/ical/relatedTo.html
		if (!StringUtils.isBlank(relatedToUid)) {
			vtodo.getProperties().add(new RelatedTo(relatedToUid));
		}
		
		// RDATE, EXRULE, RSTATUS -> Not Supported!
		// ATTACH, ATTENDEE -> Ignored!
		// CONTACT, GEO, URL, COMMENT, RESOURCES -> Not Supported! (from extra props)
		
		if (extraProps != null) {
			for (Property property : extraProps) {
				vtodo.getProperties().add(property);
			}
		}
		
		return vtodo;
	}
	
	public Status toToDoStatus(TaskBase.Status status) {
		if (TaskBase.Status.WAITING.equals(status)) {
			return null;
		} else if (TaskBase.Status.COMPLETED.equals(status)) {
			return Status.VTODO_COMPLETED;
		} else if (TaskBase.Status.IN_PROGRESS.equals(status)) {
			return Status.VTODO_IN_PROCESS;
		} else if (TaskBase.Status.CANCELLED.equals(status)) {
			return Status.VTODO_CANCELLED;
		} else {
			return Status.VTODO_NEEDS_ACTION;
		}
	}
	
	private Set<String> mapCategoryNames(Set<String> tags, LogHandler logHandler) {
		if (tags == null || tagNamesByIdMap == null) return null;
		LinkedHashSet<String> catNames = new LinkedHashSet<>();
		for (String tag : tags) {
			if (tagNamesByIdMap.containsKey(tag)) {
				catNames.add(tagNamesByIdMap.get(tag));
			} else {
				log(logHandler, 1, LogEntry.Level.WARN, "Unable to find tag name [{}]", tag);
			}
		}
		return catNames;
	}
	
	private void log(LogHandler logHandler, int depth, LogEntry.Level level, String message, Object... arguments) {
		if (logHandler != null) {
			try {
				logHandler.handle(new LogMessage(depth, level, message, arguments));
			} catch(Throwable t) {}
		}
	}
	
	public static enum TagsMappingMode {
		NAME, ID, NONE
	}
}

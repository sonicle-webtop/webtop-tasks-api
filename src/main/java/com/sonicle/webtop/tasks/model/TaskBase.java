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
package com.sonicle.webtop.tasks.model;

import com.google.gson.annotations.SerializedName;
import com.sonicle.commons.InternetAddressUtils;
import jakarta.mail.internet.InternetAddress;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public class TaskBase {
	protected Integer categoryId;
	protected String publicUid;
	protected RevisionStatus revisionStatus;
	protected DateTime revisionTimestamp;
	protected Integer revisionSequence;
	protected DateTime creationTimestamp;
	protected String organizer;
	protected String organizerId;
	protected String subject;
	protected String location;
	protected String description;
	protected BodyType descriptionType;
	protected String timezone;
	protected DateTime start;
	protected DateTime due;
	protected DateTime completedOn;
	protected Short progress;
	protected Status status;
	protected Short importance;
	protected Boolean isPrivate;
	protected String href;
	protected String etag;
	protected Integer reminder;
	protected String contact;
	protected String contactId;
	protected String documentRef;
	protected boolean censorized = false;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getPublicUid() {
		return publicUid;
	}

	public void setPublicUid(String publicUid) {
		this.publicUid = publicUid;
	}

	public RevisionStatus getRevisionStatus() {
		return revisionStatus;
	}

	public void setRevisionStatus(RevisionStatus revisionStatus) {
		this.revisionStatus = revisionStatus;
	}

	public DateTime getRevisionTimestamp() {
		return revisionTimestamp;
	}

	public void setRevisionTimestamp(DateTime revisionTimestamp) {
		this.revisionTimestamp = revisionTimestamp;
	}
	
	public Integer getRevisionSequence() {
		return revisionSequence;
	}

	public void setRevisionSequence(Integer revisionSequence) {
		this.revisionSequence = revisionSequence;
	}

	public DateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(DateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	
	public String getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(String organizerId) {
		this.organizerId = organizerId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BodyType getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(BodyType descriptionType) {
		this.descriptionType = descriptionType;
	}
	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public DateTime getStart() {
		return start;
	}

	public void setStart(DateTime start) {
		this.start = start;
	}

	public DateTime getDue() {
		return due;
	}

	public void setDue(DateTime due) {
		this.due = due;
	}

	public DateTime getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(DateTime completedOn) {
		this.completedOn = completedOn;
	}

	public Short getProgress() {
		return progress;
	}

	public void setProgress(Short progress) {
		this.progress = progress;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Short getImportance() {
		return importance;
	}

	public void setImportance(Short importance) {
		this.importance = importance;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public Integer getReminder() {
		return reminder;
	}

	public void setReminder(Integer reminder) {
		this.reminder = reminder;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getDocumentRef() {
		return documentRef;
	}

	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
	}
	
	public Float getProgressPercentage() {
		if (progress == null) {
			return (float)0;
		} else {
			return Math.max(Math.min(progress.floatValue()/100, (float)100), (float)0);
		}
	}
	
	public InternetAddress getOrganizerInternetAddress() {
		return InternetAddressUtils.toInternetAddress(getOrganizer());
	}
	
	public String getOrganizerAddress() {
		InternetAddress ia = getOrganizerInternetAddress();
		return (ia != null) ? ia.getAddress() : null;
	}
	
	public String getOrganizerCN() {
		InternetAddress ia = getOrganizerInternetAddress();
		return (ia != null) ? ia.getPersonal() : null;
	}
	
	public DateTimeZone getTimezoneObject() {
		return DateTimeZone.forID(getTimezone());
	}
	
	public boolean isCensorized() {
		return this.censorized;
	}
	
	public void censorize() {
		this.setSubject("(***)");
		this.setDescription("");
		this.setReminder(null);
		this.censorized = true;		
	}
	
	public static enum RevisionStatus {
		@SerializedName("N") NEW,
		@SerializedName("M") MODIFIED,
		@SerializedName("D") DELETED
	}
	
	public static enum BodyType {
		@SerializedName("text") TEXT,
		@SerializedName("html") HTML
	}
	
	public static enum Status {
		@SerializedName("NA") NEEDS_ACTION,
		@SerializedName("CO") COMPLETED,
		@SerializedName("IP") IN_PROGRESS,
		@SerializedName("CA") CANCELLED,
		@SerializedName("WA") WAITING
	}
}

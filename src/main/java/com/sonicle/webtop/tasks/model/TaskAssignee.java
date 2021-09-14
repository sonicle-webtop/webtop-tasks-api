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
import com.sonicle.webtop.core.sdk.UserProfileId;
import jakarta.mail.internet.InternetAddress;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jooq.tools.StringUtils;

/**
 *
 * @author malbinola
 */
public class TaskAssignee {
	protected String assigneeId;
	protected String recipient;
	protected String recipientUserId;
	protected ResponseStatus responseStatus;

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public String getRecipientUserId() {
		return recipientUserId;
	}

	public void setRecipientUserId(String recipientUserId) {
		this.recipientUserId = recipientUserId;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	public boolean hasEmailRecipient() {
		return getRecipientAddress() != null;
	}
	
	public boolean hasRecipientUserId() {
		return getRecipientUserId() != null;
	}
	
	public UserProfileId getRecipientProfileId(String domainId) {
		String userId = getRecipientUserId();
		if (userId == null || domainId == null) return null;
		return new UserProfileId(domainId, getRecipientUserId());
	}
	
	public InternetAddress getRecipientInternetAddress() {
		return InternetAddressUtils.toInternetAddress(getRecipient());
	}
	
	public String getRecipientAddress() {
		InternetAddress ia = getRecipientInternetAddress();
		return (ia != null) ? ia.getAddress() : null;
	}
	
	public String getRecipientCN() {
		InternetAddress ia = getRecipientInternetAddress();
		return (ia != null) ? ia.getPersonal() : null;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAssigneeId())
			.append(getRecipient())
			.append(getRecipientUserId())
			.append(getResponseStatus())
			.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TaskAssignee == false) return false;
		if (this == obj) return true;
		final TaskAssignee otherObject = (TaskAssignee)obj;
		return new EqualsBuilder()
			.append(getAssigneeId(), otherObject.getAssigneeId())
			.isEquals();
	}
	
	public static enum ResponseStatus {
		@SerializedName("NA") NEEDS_ACTION, //"none"; // Synonym of needsAction
		@SerializedName("DE") DECLINED, // "refused"; // Synonym of declined
		@SerializedName("TE") TENTATIVE,
		@SerializedName("AC") ACCEPTED
	}
}

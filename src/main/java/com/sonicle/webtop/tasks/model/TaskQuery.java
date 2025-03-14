/*
 * Copyright (C) 2025 Sonicle S.r.l.
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
 * display the words "Copyright (C) 2025 Sonicle S.r.l.".
 */
package com.sonicle.webtop.tasks.model;

import com.sonicle.commons.qbuilders.properties.concrete.BooleanProperty;
import com.sonicle.commons.qbuilders.properties.concrete.InstantProperty;
import com.sonicle.commons.qbuilders.properties.concrete.IntegerProperty;
import com.sonicle.commons.qbuilders.properties.concrete.ShortProperty;
import com.sonicle.commons.qbuilders.properties.concrete.StringProperty;
import com.sonicle.webtop.core.app.sdk.QueryBuilderWithCFields;

/**
 *
 * @author malbinola
 */
public class TaskQuery extends QueryBuilderWithCFields<TaskQuery> {
	public static final String ID = "id";
	public static final String CREATED_ON = "createdOn";
	public static final String UPDATED_ON = "updatedOn";
	public static final String PRIVATE = "private";
	public static final String SUBJECT = "subject";
	public static final String LOCATION = "location";
	public static final String DESCRIPTION = "description";
	public static final String TIMEZONE = "timezone";
	public static final String START = "start";
	public static final String DUE = "due";
	public static final String COMPLETED_ON = "copletedOn";
	public static final String PROGRESS = "progress";
	public static final String STATUS = "status";
	public static final String IMPORTANCE = "importance";
	public static final String REFERENCE = "reference";
	public static final String REMINDER = "reminder";
	public static final String ORGANIZER = "organizer";
	public static final String ORGANIZER_ID = "organizerId";
	public static final String PARENT_ID = "parentId";
	//public static final String CONTACT = "contact";
	//public static final String CONTACT_ID = "contactId";
	//public static final String COMPANY = "company";
	//public static final String COMPANY_ID = "companyId";
	public static final String TAG_ID = "tagId";
	
	public StringProperty<TaskQuery> id() {
		return string(ID);
	}
	
	public InstantProperty<TaskQuery> createdOn() {
		return instant(CREATED_ON);
	}
	
	public InstantProperty<TaskQuery> updatedOn() {
		return instant(UPDATED_ON);
	}
	
	public BooleanProperty<TaskQuery> isPrivate() {
		return bool(PRIVATE);
	}
	
	public StringProperty<TaskQuery> subject() {
		return string(SUBJECT);
	}
	
	public StringProperty<TaskQuery> location() {
		return string(LOCATION);
	}
	
	public StringProperty<TaskQuery> description() {
		return string(DESCRIPTION);
	}
	
	public StringProperty<TaskQuery> timezone() {
		return string(TIMEZONE);
	}
	
	public InstantProperty<TaskQuery> start() {
		return instant(START);
	}
	
	public InstantProperty<TaskQuery> due() {
		return instant(DUE);
	}
	
	public InstantProperty<TaskQuery> completedOn() {
		return instant(COMPLETED_ON);
	}
	
	public ShortProperty<TaskQuery> progress() {
		return shortNum(PROGRESS);
	}
	
	public StringProperty<TaskQuery> status() {
		return string(STATUS);
	}
	
	public ShortProperty<TaskQuery> importance() {
		return shortNum(IMPORTANCE);
	}
	
	public StringProperty<TaskQuery> reference() {
		return string(REFERENCE);
	}
	
	public IntegerProperty<TaskQuery> reminder() {
		return intNum(REMINDER);
	}
	
	public StringProperty<TaskQuery> organizer() {
		return string(ORGANIZER);
	}
	
	public StringProperty<TaskQuery> organizerId() {
		return string(ORGANIZER_ID);
	}
	
	public StringProperty<TaskQuery>parentId() {
		return string(PARENT_ID);
	}
	
	/*
	public StringProperty<TaskQueryNEW> contact() {
		return string(CONTACT);
	}
	
	public StringProperty<TaskQueryNEW> contactId() {
		return string(CONTACT_ID);
	}
	
	public StringProperty<TaskQueryNEW> company() {
		return string(COMPANY);
	}
	
	public StringProperty<TaskQueryNEW> companyId() {
		return string(COMPANY_ID);
	}
	*/
	
	public StringProperty<TaskQuery> tagId() {
		return string(TAG_ID);
	}
}

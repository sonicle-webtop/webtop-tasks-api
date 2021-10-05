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

import com.sonicle.commons.LangUtils;
import com.sonicle.commons.time.DateTimeUtils;
import com.sonicle.commons.web.json.CId;
import net.sf.qualitycheck.Check;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/**
 *
 * @author malbinola
 */
public class TaskInstanceId extends CId {
	public static final String MASTER_INSTANCE_ID = "00000000";
	
	public TaskInstanceId(String id) {
		super(".", id, 2);
		if (!StringUtils.contains(id, ".")) throw new IllegalArgumentException("Not a valid instance ID");
	}
	
	protected TaskInstanceId(AbstractBuilder builder) {
		super(builder);
	}
	
	public String getTaskId() {
		return getToken(0);
	}
	
	public String getInstance() {
		return getToken(1);
	}

	public LocalDate getInstanceAsDate() {
		if (hasNoInstance()) {
			return null;
		} else {
			return DateTimeUtils.parseDateTime(DateTimeUtils.createFormatter("yyyyMMdd", DateTimeZone.UTC), getInstance()).toLocalDate();
		}
	}

	public boolean hasNoInstance() {
		return MASTER_INSTANCE_ID.equals(getInstance());
	}

	public static TaskInstanceId parse(final String s) {
		try {
			return new TaskInstanceId(s);
		} catch(IllegalArgumentException ex) {
			return null;
		}
	}
	
	public static TaskInstanceId build(final String taskId, final DateTime instance, final DateTimeZone timezone) {
		return build(taskId, DateTimeUtils.print(DateTimeUtils.createFormatter("yyyyMMdd", timezone), instance));
	}
	
	/* Avoid UTC usage
	public static TaskInstanceId build(final String taskId, final DateTime instance) {
		return build(taskId, DateTimeUtils.print(DateTimeUtils.createFormatter("yyyyMMdd", DateTimeZone.UTC), instance));
	}
	*/
	
	public static TaskInstanceId buildMaster(final String taskId) {
		return build(taskId, MASTER_INSTANCE_ID);
	}
	
	public static TaskInstanceId build(final String taskId, final String instance) {
		return new Builder()
			.withSeparator(".")
			.withTokens(Check.notNull(taskId, "taskId"), StringUtils.defaultIfBlank(instance, MASTER_INSTANCE_ID))
			.build();
	}

	public static TaskInstanceId build(final String taskId, final String seriesTaskId, final String seriesInstance) {
		return build(LangUtils.coalesceStrings(seriesTaskId, taskId), seriesInstance);
	}

	private static class Builder extends CId.AbstractBuilder<Builder, TaskInstanceId> {
		@Override
		public TaskInstanceId build() {
			return new TaskInstanceId(this);
		}
	}
}

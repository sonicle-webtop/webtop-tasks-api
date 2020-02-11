/*
 * Copyright (C) 2019 Sonicle S.r.l.
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
 * display the words "Copyright (C) 2019 Sonicle S.r.l.".
 */
package com.sonicle.webtop.tasks.model;

import com.github.rutledgepaulv.qbuilders.builders.QBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.properties.concrete.InstantProperty;
import com.github.rutledgepaulv.qbuilders.properties.concrete.StringProperty;
import com.sonicle.commons.time.DateTimeUtils;
import com.sonicle.commons.web.json.bean.QueryObj;
import com.sonicle.webtop.core.app.sdk.WTUnsupportedOperationException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

/**
 *
 * @author Inis
 */
public class TaskQuery extends QBuilder<TaskQuery> {

	public StringProperty<TaskQuery> subject() {
		return string("subject");
	}

	public StringProperty<TaskQuery> description() {
		return string("description");
	}

	public InstantProperty<TaskQuery> after() {
		return instant("after");
	}

	public InstantProperty<TaskQuery> before() {
		return instant("before");
	}

	public StringProperty<TaskQuery> isPrivate() {
		return string("private");
	}

	public StringProperty<TaskQuery> isDone() {
		return string("done");
	}

	public StringProperty<TaskQuery> any() {
		return string("any");
	}
	
	public StringProperty<TaskQuery> tag() {
		return string("tag");
	}

	public static Condition<TaskQuery> toCondition(String pattern) {
		if (!StringUtils.isBlank(pattern)) {
			return new TaskQuery().any().eq(StringUtils.replace(pattern, "%", "*"));
		} else {
			return null;
		}
	}

	public static Condition<TaskQuery> toCondition(QueryObj query, DateTimeZone timezone) {
		Condition<TaskQuery> result = null;
		for (QueryObj.Condition queryCondition : query.conditions) {
			TaskQuery q = (result == null) ? new TaskQuery() : result.and();
			switch (queryCondition.keyword) {
				case "subject":
					result = q.subject().eq(queryCondition.value);
					break;
				case "description":
					result = q.description().eq(queryCondition.value);
					break;
				case "after":
					String after = StringUtils.replace(queryCondition.value, "/", "-");
					result = q.after().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(after), DateTimeUtils.toZoneId(timezone)));
					break;
				case "before":
					String before = StringUtils.replace(queryCondition.value, "/", "-");
					result = q.before().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(before), DateTimeUtils.toZoneId(timezone)));
					break;
				case "is":
					switch (queryCondition.value) {
						case "private":
							result = q.isPrivate().eq("true");
							break;
						case "done":
							result = q.isDone().eq("true");
							break;
						default:
							throw new UnsupportedOperationException(queryCondition.keyword + ":" + queryCondition.value);
					}
					break;
				case "tag":
					result = q.tag().eq(queryCondition.value);
					break;
				default:
					throw new WTUnsupportedOperationException("Unsupported keyword '{}'", queryCondition.keyword);
			}
		}

		if (!StringUtils.isBlank(query.allText)) {
			TaskQuery q = (result == null) ? new TaskQuery() : result.and();
			result = q.any().eq(query.allText);
		}

		return result;
	}
}

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

import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.properties.concrete.BooleanProperty;
import com.github.rutledgepaulv.qbuilders.properties.concrete.InstantProperty;
import com.github.rutledgepaulv.qbuilders.properties.concrete.StringProperty;
import com.sonicle.commons.time.DateTimeUtils;
import com.sonicle.commons.web.json.CompId;
import com.sonicle.commons.web.json.bean.QueryObj;
import com.sonicle.webtop.core.app.sdk.QueryBuilderWithCValues;
import com.sonicle.webtop.core.app.sdk.WTUnsupportedOperationException;
import com.sonicle.webtop.core.model.CustomField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

/**
 *
 * @author Inis
 */
public class TaskQuery extends QueryBuilderWithCValues<TaskQuery> {

	public TaskQuery() {
		super(true);
	}

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

	public BooleanProperty<TaskQuery> isPrivate() {
		return bool("private");
	}

	public BooleanProperty<TaskQuery> isDone() {
		return bool("done");
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
	
	public static Condition<TaskQuery> toCondition(QueryObj query, Map<String, CustomField.Type> customFieldTypeMapping, DateTimeZone timezone) {
		Condition<TaskQuery> result = null;
		
		for (Map.Entry<String, Collection<QueryObj.Condition>> entry : query.getConditionsMap().entrySet()) {
			TaskQuery q = (result == null) ? new TaskQuery() : result.and();
			
			ArrayList<Condition<TaskQuery>> cndts = new ArrayList<>();
			for (QueryObj.Condition queryCondition : entry.getValue()) {
				if ("subject".equals(queryCondition.keyword)) {
					cndts.add(new TaskQuery().subject().eq(q.asSmartStringValue(queryCondition.value)));
					
				} else if ("description".equals(queryCondition.keyword)) {
					cndts.add(new TaskQuery().description().eq(q.asSmartStringValue(queryCondition.value)));
					
				} else if ("after".equals(queryCondition.keyword)) {
					String after = StringUtils.replace(queryCondition.value, "/", "-");
					cndts.add(new TaskQuery().after().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(after), DateTimeUtils.toZoneId(timezone))));
					
				} else if ("before".equals(queryCondition.keyword)) {
					String before = StringUtils.replace(queryCondition.value, "/", "-");
					cndts.add(new TaskQuery().before().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(before), DateTimeUtils.toZoneId(timezone))));
					
				} else if ("is".equals(queryCondition.keyword)) {
					switch (queryCondition.value) {
						case "private":
							cndts.add(new TaskQuery().isPrivate().isTrue());
							break;
						case "done":
							cndts.add(new TaskQuery().isDone().isTrue());
							break;
						default:
							throw new UnsupportedOperationException(queryCondition.keyword + ":" + queryCondition.value);
					}
					
				} else if ("tag".equals(queryCondition.keyword)) {
					cndts.add(new TaskQuery().tag().eq(queryCondition.value));
					
				} else if (StringUtils.startsWith(queryCondition.keyword, "cfield")) {
					Condition<TaskQuery> cond = null;
					CompId cf = new CompId(2).parse(queryCondition.keyword, false);
					if (!cf.isTokenEmpty(1)) {
						String cfId = cf.getToken(1);
						if (customFieldTypeMapping.containsKey(cfId)) {
							cond = new TaskQuery().customValueCondition(cfId, customFieldTypeMapping.get(cfId), queryCondition.value, queryCondition.negated, timezone);
						}
					}
					if (cond != null) cndts.add(cond);					
					
				} else {
					throw new WTUnsupportedOperationException("Unsupported keyword '{}'", queryCondition.keyword);
				}
			}
			result = q.or(cndts);
		}
		
		if (!StringUtils.isBlank(query.allText)) {
			TaskQuery q = (result == null) ? new TaskQuery() : result.and();
			result = q.any().eq(q.asSmartStringValue(query.allText));
		}
		
		return result;
	}
}

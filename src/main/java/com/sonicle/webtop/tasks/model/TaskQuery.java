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

import com.sonicle.commons.EnumUtils;
import com.sonicle.commons.qbuilders.conditions.Condition;
import com.sonicle.commons.qbuilders.properties.concrete.BooleanProperty;
import com.sonicle.commons.qbuilders.properties.concrete.InstantProperty;
import com.sonicle.commons.qbuilders.properties.concrete.StringProperty;
import com.sonicle.commons.time.DateTimeUtils;
import com.sonicle.commons.web.json.CId;
import com.sonicle.commons.web.json.bean.QueryObj;
import com.sonicle.webtop.core.app.sdk.QueryBuilderWithCValues;
import com.sonicle.webtop.core.app.sdk.WTUnsupportedOperationException;
import com.sonicle.webtop.core.model.CustomField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public class TaskQuery extends QueryBuilderWithCValues<TaskQuery> {

	public StringProperty<TaskQuery> subject() {
		return string("subject");
	}
	
	public StringProperty<TaskQuery> location() {
		return string("location");
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
	
	public StringProperty<TaskQuery> status() {
		return string("status");
	}
	
	public BooleanProperty<TaskQuery> isDone() {
		return bool("done");
	}
	
	/*
	public BooleanProperty<TaskQuery> isStarted() {
		return bool("started");
	}
	
	public BooleanProperty<TaskQuery> isLate() {
		return bool("late");
	}
	*/
	
	public StringProperty<TaskQuery> document() {
		return string("document");
	}

	public StringProperty<TaskQuery> any() {
		return string("any");
	}
	
	public StringProperty<TaskQuery> tag() {
		return string("tag");
	}
	
	public StringProperty<TaskQuery> parent() {
		return string("parent");
	}

	public static Condition<TaskQuery> createCondition(String pattern) {
		if (!StringUtils.isBlank(pattern)) {
			return new TaskQuery().any().eq(StringUtils.replace(pattern, "%", "*"));
		} else {
			return null;
		}
	}
	
	public static Condition<TaskQuery> createCondition(QueryObj query, Map<String, CustomField.Type> customFieldTypeMapping, DateTimeZone timezone) {
		boolean smartStringComparison = true;
		
		Condition<TaskQuery> last = new TaskQuery().trueCondition();
		for (Map.Entry<QueryObj.Condition, List<String>> entry : query.groupConditions(Arrays.asList("is")).entrySet()) {
			final QueryObj.Condition key = entry.getKey();
			final List<String> values = entry.getValue();
			
			if (values.isEmpty() || values.size() == 1) {
				last = new TaskQuery().and(last, createCondition(key, values.isEmpty() ? null : values.get(0), customFieldTypeMapping, timezone, smartStringComparison));
			} else {
				List<Condition<TaskQuery>> conds = new ArrayList<>();
				for (String value : entry.getValue()) {
					conds.add(createCondition(key, value, customFieldTypeMapping, timezone, smartStringComparison));
				}
				last = new TaskQuery().and(last, new TaskQuery().or(conds));
			}
		}
		
		if (!StringUtils.isBlank(query.getAllText())) {
			return new TaskQuery().and(last, new TaskQuery().any().eq(asStringValue(query.getAllText(), smartStringComparison)));
		} else {
			return last;
		}
	}
	
	private static Condition<TaskQuery> createCondition(QueryObj.Condition condition, String value, Map<String, CustomField.Type> customFieldTypeMapping, DateTimeZone timezone, boolean smartStringComparison) {
		if ("subject".equals(condition.keyword)) {
			return new TaskQuery().subject().eq(asStringValue(value, smartStringComparison));

		} else if ("location".equals(condition.keyword)) {
			return new TaskQuery().location().eq(asStringValue(value, smartStringComparison));

		} else if ("description".equals(condition.keyword)) {
			return new TaskQuery().description().eq(asStringValue(value, smartStringComparison));

		} else if ("after".equals(condition.keyword)) {
			String after = StringUtils.replace(value, "/", "-");
			return new TaskQuery().after().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(after), DateTimeUtils.toZoneId(timezone)));

		} else if ("before".equals(condition.keyword)) {
			String before = StringUtils.replace(value, "/", "-");
			return new TaskQuery().before().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(before), DateTimeUtils.toZoneId(timezone)));

		} else if ("status".equals(condition.keyword)) {
			if (EnumUtils.forSerializedName(value, TaskBase.Status.class)== null) {
				throw new UnsupportedOperationException(condition.keyword + ":" + value);
			}
			return new TaskQuery().status().eq(value);

		} else if ("private".equals(condition.keyword)) {
			return condition.negated ? new TaskQuery().isPrivate().isFalse() : new TaskQuery().isPrivate().isTrue();

		} else if ("done".equals(condition.keyword)) {
			return condition.negated ? new TaskQuery().isDone().isFalse() : new TaskQuery().isDone().isTrue();

		} else if ("tag".equals(condition.keyword)) {
			return new TaskQuery().tag().eq(value);

		} else if ("parent".equals(condition.keyword)) {
			return new TaskQuery().parent().eq(value);

		} else if ("doc".equals(condition.keyword)) {
			return new TaskQuery().document().eq(asStringValue(value, smartStringComparison));

		} else if (StringUtils.startsWith(condition.keyword, "cfield")) {
			CId cf = new CId(condition.keyword, 2);
			if (!cf.isTokenEmpty(1)) {
				String cfId = cf.getToken(1);
				if (customFieldTypeMapping.containsKey(cfId)) {
					return new TaskQuery().customValueCondition(cfId, customFieldTypeMapping.get(cfId), value, condition.negated, smartStringComparison, timezone);
				}
			}
		}
		
		throw new WTUnsupportedOperationException("Unsupported keyword '{}'", condition.keyword);
	}
}

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

import com.sonicle.commons.EnumUtils;
import com.sonicle.commons.qbuilders.conditions.Condition;
import com.sonicle.commons.qbuilders.properties.concrete.BooleanProperty;
import com.sonicle.commons.qbuilders.properties.concrete.InstantProperty;
import com.sonicle.commons.qbuilders.properties.concrete.StringProperty;
import com.sonicle.commons.time.DateTimeUtils;
import com.sonicle.commons.web.json.CId;
import com.sonicle.commons.web.json.bean.QueryObj;
import com.sonicle.webtop.core.app.sdk.QueryBuilder;
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
public class TaskQueryUI extends TaskQuery {
	public static final String AFTER = "after";
	public static final String BEFORE = "before";
	public static final String DONE = "done";
	//public static final String STARTED = "started";
	//public static final String LATE = "late";
	public static final String ANY = "any";
	//public static final String ANY_COMPANY = "anyCompany";
	
	public InstantProperty<TaskQuery> after() {
		return instant(AFTER);
	}

	public InstantProperty<TaskQuery> before() {
		return instant(BEFORE);
	}
	
	public BooleanProperty<TaskQuery> done() {
		return bool(DONE);
	}
	
	/*
	public BooleanProperty<TaskQueryNEW> isStarted() {
		return bool(STARTED);
	}
	
	public BooleanProperty<TaskQueryNEW> isLate() {
		return bool(LATE);
	}
	*/
	
	public StringProperty<TaskQuery> any() {
		return string(ANY);
	}
	
	public static Condition<TaskQuery> build(String pattern) {
		if (!StringUtils.isBlank(pattern)) {
			return new TaskQueryUI().any().eq(StringUtils.replace(pattern, "%", "*"));
		} else {
			return null;
		}
	}
	
	public static Condition<TaskQuery> build(QueryObj query, Map<String, CustomField.Type> customFieldTypeMapping, DateTimeZone timezone) {
		Condition<TaskQuery> last = new TaskQueryUI().selfCondition();
		for (Map.Entry<QueryObj.Condition, List<String>> entry : query.groupConditions(Arrays.asList("is")).entrySet()) {
			final QueryObj.Condition key = entry.getKey();
			final List<String> values = entry.getValue();
			
			if (values.isEmpty() || values.size() == 1) {
				last = new TaskQueryUI().and(last, createCondition(key, values.isEmpty() ? null : values.get(0), customFieldTypeMapping, timezone));
			} else {
				List<Condition<TaskQuery>> conds = new ArrayList<>();
				for (String value : values) {
					conds.add(createCondition(key, value, customFieldTypeMapping, timezone));
				}
				last = new TaskQueryUI().and(last, new TaskQuery().or(conds));
			}
		}
		
		if (!StringUtils.isBlank(query.getAllText())) {
			String[] values = asStringValues(query.getAllText(), true);
			return new TaskQueryUI().and(last, combineFieldValuesAsCondition(values, v -> new TaskQueryUI().any().eq(v)));
		} else {
			return last;
		}
	}
	
	private static Condition<TaskQuery> combineFieldValuesAsCondition(final String[] values, final QueryBuilder.QueryFieldConditionCreatorMethod<TaskQuery, String> creator) {
		if (values.length == 1) {
			return creator.create(values[0]);
		} else {
			List<Condition<TaskQuery>> conds = new ArrayList<>(values.length);
			for (String value : values) {
				conds.add(creator.create(value));
			}
			return new TaskQueryUI().or(conds);
		}
	}
	
	private static Condition<TaskQuery> createCondition(QueryObj.Condition condition, String value, Map<String, CustomField.Type> customFieldTypeMapping, DateTimeZone timezone) {
		if ("subject".equals(condition.keyword)) {
			return new TaskQueryUI().subject().like(asStringValue(value, true));

		} else if ("location".equals(condition.keyword)) {
			return new TaskQueryUI().location().like(asStringValue(value, true));

		} else if ("description".equals(condition.keyword)) {
			return new TaskQueryUI().description().like(asStringValue(value, true));

		} else if ("after".equals(condition.keyword)) {
			String after = StringUtils.replace(value, "/", "-");
			return new TaskQueryUI().after().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(after), DateTimeUtils.toZoneId(timezone)));

		} else if ("before".equals(condition.keyword)) {
			String before = StringUtils.replace(value, "/", "-");
			return new TaskQueryUI().before().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(before), DateTimeUtils.toZoneId(timezone)));

		} else if ("status".equals(condition.keyword)) {
			if (EnumUtils.forSerializedName(value, TaskBase.Status.class) == null) {
				throw new UnsupportedOperationException(condition.keyword + ":" + value);
			}
			return new TaskQueryUI().status().eq(value);

		} else if ("private".equals(condition.keyword)) {
			return condition.negated ? new TaskQueryUI().isPrivate().isFalse() : new TaskQueryUI().isPrivate().isTrue();

		} else if ("done".equals(condition.keyword)) {
			return condition.negated ? new TaskQueryUI().done().isFalse() : new TaskQueryUI().done().isTrue();

		} else if ("tag".equals(condition.keyword)) {
			return new TaskQueryUI().tagId().eq(value);

		} else if ("parent".equals(condition.keyword)) {
			return new TaskQueryUI().parentId().eq(value);

		} else if ("doc".equals(condition.keyword)) {
			return new TaskQueryUI().reference().like(asStringValue(value, true));

		} else if (StringUtils.startsWith(condition.keyword, "cfield")) {
			CId cf = new CId(condition.keyword, 2);
			if (!cf.isTokenEmpty(1)) {
				String cfId = cf.getToken(1);
				if (customFieldTypeMapping.containsKey(cfId)) {
					return new TaskQueryUI().customFieldCondition(cfId, customFieldTypeMapping.get(cfId), value, condition.negated, true, timezone);
				}
			}
		}
		
		throw new WTUnsupportedOperationException("Unsupported keyword '{}'", condition.keyword);
	}
}

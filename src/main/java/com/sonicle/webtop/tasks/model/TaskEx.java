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

import com.rits.cloning.Cloner;
import com.sonicle.webtop.core.model.CustomFieldValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.sf.qualitycheck.Check;

/**
 *
 * @author malbinola
 */
public class TaskEx extends TaskBase {
	protected TaskInstanceId parentInstanceId;
	protected Set<String> tags;
	protected TaskRecurrence recurrence;
	protected List<TaskAssignee> assignees;
	protected List<TaskAttachment> attachments;
	protected Map<String, CustomFieldValue> customValues;
	
	public TaskEx() {
		super();
	}
	
	public TaskEx(TaskBase task) {
		super();
		Cloner.standard().copyPropertiesOfInheritedClass(task, this);
	}
	
	public TaskInstanceId getParentInstanceId() {
		return parentInstanceId;
	}

	public void setParentInstanceId(TaskInstanceId parentInstanceId) {
		this.parentInstanceId = parentInstanceId;
	}
	
	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public TaskRecurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(TaskRecurrence recurrence) {
		this.recurrence = recurrence;
	}

	public List<TaskAssignee> getAssignees() {
		return assignees;
	}

	public void setAssignees(List<TaskAssignee> assignees) {
		this.assignees = assignees;
	}
	
	public List<TaskAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<TaskAttachment> attachments) {
		this.attachments = attachments;
	}
	
	public Map<String, CustomFieldValue> getCustomValues() {
		return customValues;
	}
	
	public void setCustomValues(Map<String, CustomFieldValue> customValues) {
		this.customValues = customValues;
	}
	
	public Set<String> getTagsOrEmpty() {
		return this.tags != null ? tags : new LinkedHashSet<>(0);
	}
	
	public TaskEx addTag(String tagId) {
		if (this.tags == null) this.tags = new LinkedHashSet<>();
		this.tags.add(Check.notNull(tagId, "tagId"));
		return this;
	}
	
	public TaskEx removeTag(String tagId) {
		if (this.tags != null) {
			this.tags.remove(Check.notNull(tagId, "tagId"));
		}
		return this;
	}
	
	public List<TaskAssignee> getAssigneesOrEmpty() {
		return this.assignees != null ? assignees : new ArrayList<>(0);
	}
	
	public List<TaskAttachment> getAttachmentsOrEmpty() {
		return this.attachments != null ? attachments : new ArrayList<>(0);
	}
	
	public TaskEx addAttachment(TaskAttachment attachment) {
		if (this.attachments == null) this.attachments = new ArrayList<>();
		this.attachments.add(Check.notNull(attachment, "attachment"));
		return this;
	}
	
	public void setCustomValues(Collection<CustomFieldValue> customValues) {
		this.customValues = customValues.stream()
			.filter(item -> item.getFieldId() != null)
			.collect(Collectors.toMap(item -> item.getFieldId(), item -> item, (ov, nv) -> nv, LinkedHashMap::new));
	}
	
	public boolean hasTags() {
		return this.tags != null;
	}
	
	public boolean hasRecurrence() {
		return this.recurrence != null;
	}
	
	public boolean hasAssignees() {
		return this.assignees != null;
	}
	
	public boolean hasAttachments() {
		return this.attachments != null;
	}
	
	public boolean hasCustomValues() {
		return this.customValues != null;
	}
	
	public boolean isChild() {
		return getParentInstanceId() != null;
	}
}

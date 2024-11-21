/* 
 * Copyright (C) 2014 Sonicle S.r.l.
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
 * display the words "Copyright (C) 2014 Sonicle S.r.l.".
 */
package com.sonicle.webtop.tasks;

import com.google.gson.annotations.SerializedName;
import com.sonicle.commons.BitFlag;
import com.sonicle.commons.BitFlagEnum;
import com.sonicle.webtop.tasks.model.TaskQuery;
import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.tasks.model.Category;
import com.sonicle.webtop.tasks.model.CategoryPropSet;
import com.sonicle.webtop.tasks.model.Task;
import com.sonicle.webtop.tasks.model.TaskAttachmentWithBytes;
import com.sonicle.webtop.tasks.model.TaskObject;
import com.sonicle.webtop.tasks.model.TaskObjectChanged;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import com.sonicle.commons.qbuilders.conditions.Condition;
import com.sonicle.commons.beans.SortInfo;
import com.sonicle.commons.flags.BitFlags;
import com.sonicle.commons.flags.BitFlagsEnum;
import com.sonicle.commons.time.InstantRange;
import com.sonicle.webtop.core.app.model.FolderSharing;
import com.sonicle.webtop.core.model.CustomFieldValue;
import com.sonicle.webtop.core.sdk.UserProfileId;
import com.sonicle.webtop.tasks.model.CategoryBase;
import com.sonicle.webtop.tasks.model.CategoryFSFolder;
import com.sonicle.webtop.tasks.model.CategoryFSOrigin;
import com.sonicle.webtop.tasks.model.TaskEx;
import com.sonicle.webtop.tasks.model.TaskInstance;
import com.sonicle.webtop.tasks.model.TaskInstanceId;
import com.sonicle.webtop.tasks.model.TaskLookupInstance;
import java.util.Set;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public interface ITasksManager {
	
	public Set<FolderSharing.SubjectConfiguration> getFolderShareConfigurations(final UserProfileId originProfileId, final FolderSharing.Scope scope) throws WTException;
	public void updateFolderShareConfigurations(final UserProfileId originProfileId, final FolderSharing.Scope scope, final Set<FolderSharing.SubjectConfiguration> configurations) throws WTException;
	public Map<UserProfileId, CategoryFSOrigin> listIncomingCategoryOrigins() throws WTException;
	public CategoryFSOrigin getIncomingCategoryOriginByFolderId(final int calendarId) throws WTException;
	public Map<Integer, CategoryFSFolder> listIncomingCategoryFolders(final CategoryFSOrigin origin) throws WTException;
	public Map<Integer, CategoryFSFolder> listIncomingCategoryFolders(final UserProfileId originProfileId) throws WTException;
	public Set<Integer> listMyCategoryIds() throws WTException;
	public Set<Integer> listIncomingCategoryIds() throws WTException;
	public Set<Integer> listIncomingCategoryIds(final UserProfileId owner) throws WTException;
	public Set<Integer> listAllCategoryIds() throws WTException;
	public Integer getDefaultCategoryId() throws WTException;
	public Integer getBuiltInCategoryId() throws WTException;
	public Map<Integer, Category> listMyCategories() throws WTException;
	public Map<Integer, Category> listIncomingCategories() throws WTException;
	public Map<Integer, Category> listIncomingCategories(final UserProfileId owner) throws WTException;
	public Map<Integer, DateTime> getCategoriesLastRevision(final Collection<Integer> categoryIds) throws WTException;
	public UserProfileId getCategoryOwner(final int categoryId) throws WTException;
	public boolean existCategory(final int categoryId) throws WTException;
	public Category getCategory(final int categoryId) throws WTException;
	public Category getBuiltInCategory() throws WTException;
	public Category addCategory(final CategoryBase category) throws WTException;
	public Category addBuiltInCategory() throws WTException;
	public void updateCategory(final int categoryId, final CategoryBase category) throws Exception;
	public boolean deleteCategory(final int categoryId) throws WTException;
	public CategoryPropSet getCategoryCustomProps(final int categoryId) throws WTException;
	public Map<Integer, CategoryPropSet> getCategoriesCustomProps(final Collection<Integer> categoryIds) throws WTException;
	public CategoryPropSet updateCategoryCustomProps(final int categoryId, CategoryPropSet propertySet) throws WTException;
	public List<TaskLookupInstance> listTaskInstances(final Collection<Integer> categoryIds, final TaskListView view, final DateTimeZone targetTimezone) throws WTException;
	public List<TaskLookupInstance> listTaskInstances(final Collection<Integer> categoryIds, final Condition<TaskQuery> conditionPredicate, final SortInfo sortInfo, final DateTimeZone targetTimezone) throws WTException;
	public List<TaskLookupInstance> listTaskInstances(final Collection<Integer> categoryIds, final TaskListView view, final InstantRange viewRange, final Condition<TaskQuery> conditionPredicate, final SortInfo sortInfo, final DateTimeZone targetTimezone) throws WTException;
	public TaskInstance getTaskInstance(final TaskInstanceId instanceId) throws WTException;
	public TaskInstance getTaskInstance(final TaskInstanceId instanceId, final BitFlags<TaskGetOption> options) throws WTException;
	public TaskAttachmentWithBytes getTaskInstanceAttachment(final TaskInstanceId instanceId, final String attachmentId) throws WTException;
	public Map<String, CustomFieldValue> getTaskInstanceCustomValues(final TaskInstanceId instanceId) throws WTException;
	public Task addTask(final TaskEx task) throws WTException;
	public void updateTaskInstance(final TaskInstanceId instanceId, final TaskEx task) throws WTException;
	public void updateTaskInstance(final TaskInstanceId instanceId, final TaskEx task, final BitFlag<TaskUpdateOptions> options) throws WTException;
	public void updateQuickTaskInstance(final TaskInstanceId instanceId, final Boolean completed, final Short progress, final Short importance) throws WTException;
	public void updateQuickTaskInstance(final Collection<TaskInstanceId> instanceIds, final Boolean completed, final Short progress, final Short importance) throws WTException;
	public void deleteTaskInstance(final TaskInstanceId instanceId) throws WTException;
	public void deleteTaskInstance(final Collection<TaskInstanceId> instanceIds) throws WTException;
	public void moveTaskInstance(final MoveCopyMode copyMode, final TaskInstanceId instanceId, final int targetCategoryId) throws WTException;
	public void moveTaskInstance(final MoveCopyMode copyMode, final Collection<TaskInstanceId> instanceIds, final int targetCategoryId) throws WTException;
	public void updateTaskInstanceTags(final UpdateTagsOperation operation, final Collection<TaskInstanceId> instanceIds, final Set<String> tagIds) throws WTException;
	public void updateTaskCategoryTags(final UpdateTagsOperation operation, final int categoryId, final Set<String> tagIds) throws WTException;
	public List<TaskObject> listTaskObjects(int categoryId, TaskObjectOutputType outputType) throws WTException;
	public LangUtils.CollectionChangeSet<TaskObjectChanged> listTaskObjectsChanges(int categoryId, DateTime since, Integer limit) throws WTException;
	public List<TaskObject> getTaskObjects(final int categoryId, final Collection<String> hrefs, final TaskObjectOutputType outputType) throws WTException;
	public TaskObject getTaskObject(final int categoryId, final String href, final TaskObjectOutputType outputType) throws WTException;
	//public TaskObjectWithICalendar getTaskObjectWithICalendar(int categoryId, String href) throws WTException;
	//public List<TaskObjectWithICalendar> getTaskObjectsWithICalendar(int categoryId, Collection<String> hrefs) throws WTException;
	public TaskObject getTaskObject(final TaskInstanceId instanceId, final TaskObjectOutputType outputType) throws WTException;
	public void addTaskObject(final int categoryId, final String href, final net.fortuna.ical4j.model.Calendar iCalendar) throws WTException;
	public void deleteTaskObject(final int categoryId, final String href) throws WTException;
	
	public static enum TaskListView {
		@SerializedName("all") ALL,
		@SerializedName("today") TODAY,
		@SerializedName("next7") NEXT_7,
		@SerializedName("notStarted") NOT_STARTED,
		@SerializedName("late") LATE,
		@SerializedName("completed") COMPLETED,
		@SerializedName("notCompleted") NOT_COMPLETED,
		@SerializedName("upcoming") UPCOMING
	}
	
	public static enum ImportMode {
		@SerializedName("copy") COPY,
		@SerializedName("append") APPEND
	}
	
	public static enum MoveCopyMode {
		@SerializedName("none") NONE,
		@SerializedName("root") ROOT,
		@SerializedName("tree") TREE
	}
	
	public static enum TaskGetOption implements BitFlagsEnum<TaskGetOption> {
		ATTACHMENTS(1 << 0), TAGS(1 << 1), CUSTOM_VALUES(1 << 2);
		
		private int mask = 0;
		private TaskGetOption(int mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
	}
	
	public static enum TaskUpdateOptions implements BitFlagEnum {
		ASSIGNEES(1), ATTACHMENTS(2), TAGS(4), CUSTOM_VALUES(8), CONTACT_REF(16), DOCUMENT_REF(32);
		
		private int value = 0;
		private TaskUpdateOptions(int value) { this.value = value; }
		@Override
		public int value() { return this.value; }
	}
}

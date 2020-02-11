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

import com.sonicle.webtop.tasks.model.TaskQuery;
import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.tasks.model.Category;
import com.sonicle.webtop.tasks.model.ShareFolderCategory;
import com.sonicle.webtop.tasks.model.CategoryPropSet;
import com.sonicle.webtop.tasks.model.ShareRootCategory;
import com.sonicle.webtop.tasks.model.ListTasksResult;
import com.sonicle.webtop.tasks.model.Task;
import com.sonicle.webtop.tasks.model.TaskAttachmentWithBytes;
import com.sonicle.webtop.tasks.model.TaskLookup;
import com.sonicle.webtop.tasks.model.TaskObject;
import com.sonicle.webtop.tasks.model.TaskObjectChanged;
import com.sonicle.webtop.tasks.model.TaskObjectWithICalendar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import java.util.Set;

/**
 *
 * @author malbinola
 */
public interface ITasksManager {
	
	public List<ShareRootCategory> listIncomingCategoryRoots() throws WTException;
	public Map<Integer, ShareFolderCategory> listIncomingCategoryFolders(String rootShareId) throws WTException;
	public Set<Integer> listCategoryIds() throws WTException;
	public Set<Integer> listIncomingCategoryIds() throws WTException;
	public Set<Integer> listAllCategoryIds() throws WTException;
	public Map<Integer, Category> listCategories() throws WTException;
	public Map<Integer, DateTime> getCategoriesLastRevision(Collection<Integer> categoryIds) throws WTException;
	public Category getCategory(int categoryId) throws WTException;
	public Category getBuiltInCategory() throws WTException;
	public Category addCategory(Category cat) throws WTException;
	public Category addBuiltInCategory() throws WTException;
	public void updateCategory(Category cat) throws Exception;
	public boolean deleteCategory(int categoryId) throws WTException;
	public CategoryPropSet getCategoryCustomProps(int categoryId) throws WTException;
	public Map<Integer, CategoryPropSet> getCategoriesCustomProps(Collection<Integer> categoryIds) throws WTException;
	public CategoryPropSet updateCategoryCustomProps(int categoryId, CategoryPropSet propertySet) throws WTException;
	public List<TaskObject> listTaskObjects(int categoryId, TaskObjectOutputType outputType) throws WTException;
	public LangUtils.CollectionChangeSet<TaskObjectChanged> listTaskObjectsChanges(int categoryId, DateTime since, Integer limit) throws WTException;
	public TaskObjectWithICalendar getTaskObjectWithICalendar(int categoryId, String href) throws WTException;
	public List<TaskObjectWithICalendar> getTaskObjectsWithICalendar(int categoryId, Collection<String> hrefs) throws WTException;
	public TaskObject getTaskObject(int categoryId, int taskId, TaskObjectOutputType outputType) throws WTException;
	public ListTasksResult listTasks(Collection<Integer> categoryIds, Condition<TaskQuery> conditionPredicate) throws WTException;
	public ListTasksResult listTasks(Collection<Integer> categoryIds, Condition<TaskQuery> conditionPredicate, int page, int limit, boolean returnFullCount) throws WTException;
	public List<TaskLookup> listUpcomingTasks(Collection<Integer> categoryIds) throws WTException;
	public List<TaskLookup> listUpcomingTasks(Collection<Integer> categoryIds, String pattern) throws WTException;
	public Task getTask(int taskId) throws WTException;
	public Task getTask(int taskId, boolean processAttachments, boolean processTags) throws WTException;
	public TaskAttachmentWithBytes getTaskAttachment(int taskId, String attachmentId) throws WTException;
	public Task addTask(Task task) throws WTException;
	public void updateTask(Task task) throws WTException;
	public void updateTask(Task task, boolean processAttachments) throws WTException;
	public void deleteTask(int taskId) throws WTException;
	public void deleteTask(Collection<Integer> taskIds) throws WTException;
	public void moveTask(boolean copy, int taskId, int targetCategoryId) throws WTException;
	public void moveTask(boolean copy, Collection<Integer> taskIds, int targetCategoryId) throws WTException;
	public int deleteCategoryTasks(int categoryId) throws WTException;
	public void updateTaskTags(final UpdateTagsOperation operation, final int categoryId, final Set<String> tagIds) throws WTException;
	public void updateTaskTags(final UpdateTagsOperation operation, final Collection<Integer> taskIds, final Set<String> tagIds) throws WTException;
}

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

import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.tasks.model.Category;
import com.sonicle.webtop.tasks.model.CategoryFolder;
import com.sonicle.webtop.tasks.model.CategoryPropSet;
import com.sonicle.webtop.tasks.model.CategoryRoot;
import com.sonicle.webtop.tasks.model.FolderTasks;
import com.sonicle.webtop.tasks.model.Task;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author malbinola
 */
public interface ITasksManager {
	
	public List<CategoryRoot> listIncomingCategoryRoots() throws WTException;
	public HashMap<Integer, CategoryFolder> listIncomingCategoryFolders(String rootShareId) throws WTException;
	public List<Category> listCategories() throws WTException;
	public List<Integer> listCategoryIds() throws WTException;
	public List<Integer> listIncomingCategoryIds() throws WTException;
	public Category getCategory(int categoryId) throws WTException;
	public Category getBuiltInCategory() throws WTException;
	public Category addCategory(Category cat) throws WTException;
	public Category addBuiltInCategory() throws WTException;
	public Category updateCategory(Category cat) throws Exception;
	public boolean deleteCategory(int categoryId) throws WTException;
	public CategoryPropSet getCategoryCustomProps(int categoryId) throws WTException;
	public Map<Integer, CategoryPropSet> getCategoryCustomProps(Collection<Integer> categoryIds) throws WTException;
	public CategoryPropSet updateCategoryCustomProps(int categoryId, CategoryPropSet propertySet) throws WTException;	
	public List<FolderTasks> listFolderTasks(Collection<Integer> categoryIds, String pattern) throws WTException;
	public Task getTask(int taskId) throws WTException;
	public void addTask(Task task) throws WTException;
	public void updateTask(Task task) throws WTException;
	public void deleteTask(int taskId) throws WTException;
	public void deleteTask(ArrayList<Integer> taskIds) throws WTException;
	public int deleteAllTasks(int categoryId) throws WTException;
	public void moveTask(boolean copy, int taskId, int targetCategoryId) throws WTException;
}

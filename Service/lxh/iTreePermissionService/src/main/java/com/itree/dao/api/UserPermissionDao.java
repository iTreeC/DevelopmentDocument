/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.UserPermission;

public interface UserPermissionDao {

	/**
	 * 功能：增加用户权限
	 * 
	 * @param uid
	 *            用户ID
	 * @param pid
	 *            权限ID
	 * @return true/false
	 */
	public boolean add(int uid, List<Integer> pid);

	/**
	 * 功能：通过用户ID删除用户权限
	 * 
	 * @param uid
	 *            用户ID
	 * @return true/false
	 */
	public boolean deleteByUserID(int uid);

	/**
	 * 功能：更新用户权限
	 * 
	 * @param uid
	 *            用户ID
	 * @param pid
	 *            权限ID
	 * @return true/false
	 */
	public boolean update(int uid, List<Integer> pid);

	/**
	 * 功能：通过ID查看用户权限
	 * 
	 * @param uid
	 *            用户ID
	 * @return List<UserPermission>
	 */
	public List<UserPermission> findListByUserID(int uid);

	/**
	 * 功能：通过ID查看用户权限
	 * 
	 * @param uid
	 * @return List<Integer>
	 */
	public List<Integer> findUserPermissionID(int uid);
}

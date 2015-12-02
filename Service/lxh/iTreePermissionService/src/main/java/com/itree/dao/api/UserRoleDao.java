/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.UserRole;

public interface UserRoleDao {

	/**
	 * 功能：增加用户角色
	 * 
	 * @param uid
	 *            用户ID
	 * @param rid
	 *            角色ID
	 * @return true/false
	 */
	public boolean add(int uid, List<Integer> rid);

	/**
	 * 功能：通过用户ID删除用户角色
	 * 
	 * @param uid
	 *            角色ID
	 * @return true/false
	 */
	public boolean deleteByUserID(int uid);

	/**
	 * 功能：更新用户角色
	 * 
	 * @param uid
	 *            用户ID
	 * @param rid
	 *            角色ID
	 * @return true/false
	 */
	public boolean update(int uid, List<Integer> rid);

	/**
	 * 功能：通过用户ID查看用户角色
	 * 
	 * @param uid
	 *            用户ID
	 * @return List<UserRole>
	 */
	public List<UserRole> findListByUserID(int uid);

	/**
	 * 功能：通过用户ID查看用户角色ID
	 * 
	 * @param uid
	 *            用户ID
	 * @return List<Integer>
	 */
	public List<Integer> findRoleIDByUserID(int uid);

	/**
	 * 功能：
	 * 
	 * @return
	 */
	/* public List<UserRole> findAll(); */
}

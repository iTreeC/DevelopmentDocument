/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface UserRoleEngineAPI extends Engine {
	/**
	 * 功能：增加用户角色
	 * 
	 * @param uid
	 *            User ID
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public Boolean add(int uid, List<Integer> rid);

	/**
	 * 功能：删除用户角色
	 * 
	 * @param uid
	 *            User ID
	 * @return true/false
	 */
	public Boolean delete(int uid);

	/**
	 * 功能：更新用户角色
	 * 
	 * @param uid
	 *            User ID
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public Boolean update(int uid, List<Integer> rid);

	/**
	 * 功能：通过UserID查看用户角色ID
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer>
	 */
	public List<Integer> getRIds(int uid);

	/**
	 * 功能：用户-角色匹配
	 * 
	 * @param uid
	 *            User ID
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public Boolean cando(int uid, int rid);

	/**
	 * 功能：通过UserID查看用户角色
	 * 
	 * @param uid
	 * @return
	 */
	/* public List<UserRole> getRoleByUserID(int uid); */
}

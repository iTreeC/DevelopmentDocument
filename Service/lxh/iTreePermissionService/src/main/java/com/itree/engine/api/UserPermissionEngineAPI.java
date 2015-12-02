/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface UserPermissionEngineAPI extends Engine {

	/**
	 * 功能：增加用户权限
	 * 
	 * @param uid
	 *            User ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean add(int uid, List<Integer> pid);

	/**
	 * 功能：删除用户权限
	 * 
	 * @param uid
	 *            User ID
	 * @return true/false
	 */
	public Boolean delete(int uid);

	/**
	 * 功能：更新用户权限
	 * 
	 * @param uid
	 *            User ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean update(int uid, List<Integer> pid);

	/**
	 * 功能：通过UserID得到PermissionID
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer>
	 */
	public List<Integer> getPermissionIDByUserID(int uid);

	/**
	 * 功能：用户-权限匹配
	 * 
	 * @param uid
	 *            User ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean cando(int uid, int pid);
	/* public List<UserPermission> getPermission(int uid); */

}

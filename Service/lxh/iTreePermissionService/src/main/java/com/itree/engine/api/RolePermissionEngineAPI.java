/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface RolePermissionEngineAPI extends Engine {

	/**
	 * 功能：增加角色权限
	 * 
	 * @param rid
	 *            role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean add(int rid, List<Integer> pid);

	/**
	 * 功能：删除角色权限
	 * 
	 * @param id
	 *            role ID
	 * @return true/false
	 */
	public Boolean delete(int rid);

	/**
	 * 功能：更新角色权限
	 * 
	 * @param rid
	 *            role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean update(int rid, List<Integer> pid);
	
	/**
	 * 功能：查看某角色的权限ID
	 * 
	 * @param rid
	 *            Role ID
	 * @return List<Integer>
	 */
	public List<Integer> getRolePermissionID(int rid);

	/**
	 * 功能：角色-权限匹配
	 * 
	 * @param rid
	 *            role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean cando(int rid, int pid);

	/* public List<Integer> getPermissionID(int rid); */
}

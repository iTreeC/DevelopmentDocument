/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

import com.itree.entity.Role;

public interface Engine {
	
	/**
	 * 功能：增加角色并赋权
	 * 
	 * @param name
	 *            Role name
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean addRoleAndPermission(String name, List<Integer> pid);
	/**
	 * 功能：查看某角色的权限ID
	 * 
	 * @param rid
	 *            Role ID
	 * @return List<Integer>
	 */
	public List<Integer> getRolePermissionID(int rid);

	/**
	 * 功能：查看某用户角色信息
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Role>
	 */
	public List<Role> getUserRole(int uid);

	/**
	 * 查看用户的角色的权限ID（经过role）
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer>
	 */
	public List<Integer> getUserRolePermissionID(int uid);

	/**
	 * 功能：查看用户的权限ID（不经过role）
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer>
	 */
	public List<Integer> getUserPermissionID(int uid);

	/**
	 * 功能：去除重复数据
	 * 
	 * @param ids
	 *            待去重的数组List<Integer>
	 * @return List<Integer>
	 */
	public List<Integer> dereplication(List<Integer> ids);
	/* public Boolean upcando(int uid, int pid); */
}

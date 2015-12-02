/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.RolePermission;

public interface RolePermissionDao {

	/**
	 * 功能：增加
	 * 
	 * @param rid
	 *            Role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public boolean add(int rid, List<Integer> pid);

	/**
	 * 功能：通过ID删除
	 * 
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public boolean deleteByRoleID(int rid);

	/**
	 * 功能：更新权限
	 * 
	 * @param rid
	 *            Role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public boolean update(int rid, List<Integer> pid);

	/**
	 * 功能：通过ID查看角色-权限关系集合
	 * 
	 * @param rid
	 *            Role ID
	 * @return List<RolePermission>
	 */
	public List<RolePermission> findListByRoleID(int rid);

	/**
	 * 功能： 通过ID查看对应角色的权限ID
	 * 
	 * @param rid
	 * @return List<Integer>
	 */
	public List<Integer> findPermissionIDByRoleID(int rid);

	/**
	 * 功能：查看所有
	 * 
	 * @return List<RolePermission>
	 */
	/* public List<RolePermission> findAll(); */
}

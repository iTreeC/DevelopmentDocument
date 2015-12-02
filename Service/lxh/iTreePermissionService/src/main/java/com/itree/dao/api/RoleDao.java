/**
 * @info 
 * @author 李晓欢
 * @time  2015.11.09
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.Role;

public interface RoleDao {

	/**
	 * 功能：增加角色
	 * 
	 * @param role
	 * @return
	 */
	public boolean add(Role role);

	/**
	 * 功能：通过ID删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteByID(int id);

	/**
	 * 功能：更新
	 * 
	 * @param role
	 * @return
	 */
	public boolean update(Role role);

	/**
	 * 功能 ：查看所有角色
	 * 
	 * @return
	 */
	public List<Role> getAll();

	/**
	 * 功能：通过ID查看一条角色
	 * 
	 * @param id
	 * @return
	 */
	public Role getOneByID(int id);

	/**
	 * 功能：通过角色值查看一条权限
	 * 
	 * @param name
	 * @return
	 */
	public Role getOneByName(String name);

}

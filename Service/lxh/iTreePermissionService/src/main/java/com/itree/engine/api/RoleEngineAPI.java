/**
 * @info 
 * @author 李晓欢
 * @time 2015.11.09
 */
package com.itree.engine.api;

import java.util.List;

import com.itree.entity.Role;

public interface RoleEngineAPI extends Engine {

	/**
	 * 功能:增加角色
	 * 
	 * @param name
	 *            Role name
	 * @return true/false
	 */
	public Boolean add(String name);

	/**
	 * 功能：删除角色
	 * 
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public Boolean delete(int rid);

	/**
	 * 功能：更新角色
	 * 
	 * @param rid
	 *            Role ID
	 * @param name
	 *            Role name
	 * @return true/false
	 */
	public Boolean update(int rid, String name);

	/**
	 * 功能：查看所有角色信息
	 * 
	 * @return List<Role>
	 */
	public List<Role> getAll();

	/**
	 * 功能：通过角色ID查看一条角色信息
	 * 
	 * @param rid
	 *            Role ID
	 * @return Role
	 */
	public Role getOneByID(int rid);

	/* public List<String> getAllName(); */
	/* public String getNameByID(int id); */
}

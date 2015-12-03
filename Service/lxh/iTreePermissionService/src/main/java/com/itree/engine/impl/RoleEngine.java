/**
 * @info 
 * @author 李晓欢
 * @time 2015.11.09
 */
package com.itree.engine.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.RoleEngineAPI;
import com.itree.entity.Role;

public class RoleEngine extends EngineImpl implements RoleEngineAPI {

	private static Logger logger = Logger
			.getLogger(UserPermissionEngine.class);

	public Boolean add(String name) {

		if (name.equals(null)) {
			logger.error("角色不能为空！！！");
			return false;
		}
		if (super.rdao.getOneByName(name) != null) {
			logger.error("该角色已存在.");
			return false;
		}
		Role role = new Role();
		role.setName(name);
		return super.rdao.add(role);
	}

	
	
	public Boolean delete(int id) {
		if (id == 0) {
			logger.error("角色ID不能为空！！！");
			return false;
		}
		return super.rdao.deleteByID(id);

	}

	public Boolean update(int rid, String name) {
		if (rid == 0 || name == null) {
			logger.error("角色ID不能为空！！！");
			return false;
		}
		Role role = new Role();
		role.setId(rid);
		role.setName(name);
		return super.rdao.update(role);

	}

	public List<Role> getAll() {
		return super.rdao.getAll();
	}

	public Role getOneByID(int id) {
		if (id == 0) {
			logger.error("角色ID不能为空！！！");
			return null;
		}
		return super.rdao.getOneByID(id);
	}
	public Role getOneByName(String name) {
		if (name.equals(null)) {
			logger.error("--");
			return null;
		}
		return super.rdao.getOneByName(name);
	}
	/**
	 * 功能：查所有name
	 * 
	 * @return name列表
	 */
	/*
	 * public List<String> getAllName() { return super.rdao.getAllName(); }
	 */

	/**
	 * 功能：通过id查name
	 * 
	 * @param id
	 * @return name
	 */
	/*
	 * public String getNameByID(int id) { if (id == 0) {
	 * logger.error("角色ID不能为空！！！"); return null; } Role r =
	 * super.rdao.getOneById(id); if (r != null) { return r.getName(); } else {
	 * return null; } }
	 */

}

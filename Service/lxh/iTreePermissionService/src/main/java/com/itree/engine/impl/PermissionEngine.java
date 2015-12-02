/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import org.apache.log4j.Logger;
import com.itree.engine.api.PermissionEngineAPI;
import com.itree.entity.Perm;

public class PermissionEngine extends EngineImpl implements PermissionEngineAPI {

	private static Logger logger = Logger
			.getLogger(UserPermissionEngine.class);

	public Boolean add(int id) {

		if (id == 0) {
			logger.error("ID不能为空！！！");
			return false;
		}
		if (super.pdao.getOneByClientID(id) != null) {
			logger.error("该权限已存在.");
			return false;
		}
		Perm perm = new Perm();
		perm.setClientPermissionID(id);
		return super.pdao.add(perm);
	}

	public Boolean delete(int id) {
		if (id == 0) {
			logger.error("权限ID不能为空！！！");
			return false;
		}
		return super.pdao.deleteByID(id);

	}

	/**
	 * 功能：更新权限
	 * 
	 * @param permission
	 *            权限
	 * @return true/false
	 */
	/*
	 * public Boolean update(Perm permission) { if (permission.equals(null)) {
	 * logger.error("权限ID不能为空！！！"); return false; } return
	 * super.pdao.update(permission);
	 * 
	 * }
	 */

	/**
	 * 功能：通过id查一条权限
	 * 
	 * @param id
	 * @return permission
	 */
	/*
	 * public Perm getOneByID(int id) { if (id == 0) {
	 * logger.error("权限ID不能为空！！！"); return null; } return
	 * super.pdao.getOneByID(id); }
	 */

	/**
	 * 功能：通过id查name
	 * 
	 * @param id
	 * @return
	 */
	/*
	 * public int getClientIDByID(int id) { if (id == 0) {
	 * logger.error("权限ID不能为空！！！"); return 0; } return
	 * super.pdao.getNameByID(id); Perm p = super.pdao.getOneByID(id); if (p !=
	 * null) { return p.getClientPermissionID(); } else { return 0; } }
	 */

	/**
	 * 功能：查看所有权限列表
	 * 
	 * @return 权限列表
	 */
	/*
	 * public List<Perm> getAll() { return super.pdao.getAll(); }
	 */

}

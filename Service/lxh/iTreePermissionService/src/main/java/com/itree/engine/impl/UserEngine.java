/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import org.apache.log4j.Logger;
import com.itree.engine.api.UserEngineAPI;
import com.itree.entity.User;

public class UserEngine extends EngineImpl implements UserEngineAPI {

	private static Logger logger = Logger
			.getLogger(UserPermissionEngine.class);

	public Boolean add(int id) {

		if (id == 0) {
			logger.error("ID不能为空！！！");
			return false;
		}
		if (super.udao.getOneByClientUserID(id) != null) {
			logger.error("该权限已存在.");
			return false;
		}
		User User = new User();
		User.setClientUserID(id);
		return super.udao.add(User);
	}

	public Boolean delete(int id) {
		if (id == 0) {
			logger.error("权限ID不能为空！！！");
			return false;
		}
		return super.udao.deleteByClientUserId(id);

	}

	/**
	 * 功能：更新权限
	 * 
	 * @param user
	 *            权限
	 * @return true/false
	 */
	/*
	 * public Boolean update(User user) { if (user.equals(null)) {
	 * logger.error("权限ID不能为空！！！"); return false; } return
	 * super.udao.update(user);
	 * 
	 * }
	 */

	/**
	 * 功能：查看所有权限列表
	 * 
	 * @return 权限列表
	 */
	/*
	 * public List<User> getAll() { return super.udao.getAll(); }
	 */

	/**
	 * 功能：通过id查一条权限
	 * 
	 * @param id
	 * @return user
	 */
	/*
	 * public User getOneByClientUserID(int cid) { if (cid == 0) {
	 * logger.error("权限ID不能为空！！！"); return null; } return
	 * super.udao.getOneByClientUserID(cid); }
	 */

	/**
	 * 功能：通过id查name
	 * 
	 * @param id
	 * @return
	 */
	/*
	 * public int getClientIDByID(int id) { if (id == 0) {
	 * logger.error("权限ID不能为空！！！"); return 0; } User p =
	 * super.udao.getOneByID(id); if (p != null) { return p.getClientUserID(); }
	 * else { return 0; } }
	 */

}

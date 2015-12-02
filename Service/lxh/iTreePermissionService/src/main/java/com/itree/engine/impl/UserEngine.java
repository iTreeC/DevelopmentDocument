/**
 * @info 
 * @author ������
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
			logger.error("ID����Ϊ�գ�����");
			return false;
		}
		if (super.udao.getOneByClientUserID(id) != null) {
			logger.error("��Ȩ���Ѵ���.");
			return false;
		}
		User User = new User();
		User.setClientUserID(id);
		return super.udao.add(User);
	}

	public Boolean delete(int id) {
		if (id == 0) {
			logger.error("Ȩ��ID����Ϊ�գ�����");
			return false;
		}
		return super.udao.deleteByClientUserId(id);

	}

	/**
	 * ���ܣ�����Ȩ��
	 * 
	 * @param user
	 *            Ȩ��
	 * @return true/false
	 */
	/*
	 * public Boolean update(User user) { if (user.equals(null)) {
	 * logger.error("Ȩ��ID����Ϊ�գ�����"); return false; } return
	 * super.udao.update(user);
	 * 
	 * }
	 */

	/**
	 * ���ܣ��鿴����Ȩ���б�
	 * 
	 * @return Ȩ���б�
	 */
	/*
	 * public List<User> getAll() { return super.udao.getAll(); }
	 */

	/**
	 * ���ܣ�ͨ��id��һ��Ȩ��
	 * 
	 * @param id
	 * @return user
	 */
	/*
	 * public User getOneByClientUserID(int cid) { if (cid == 0) {
	 * logger.error("Ȩ��ID����Ϊ�գ�����"); return null; } return
	 * super.udao.getOneByClientUserID(cid); }
	 */

	/**
	 * ���ܣ�ͨ��id��name
	 * 
	 * @param id
	 * @return
	 */
	/*
	 * public int getClientIDByID(int id) { if (id == 0) {
	 * logger.error("Ȩ��ID����Ϊ�գ�����"); return 0; } User p =
	 * super.udao.getOneByID(id); if (p != null) { return p.getClientUserID(); }
	 * else { return 0; } }
	 */

}

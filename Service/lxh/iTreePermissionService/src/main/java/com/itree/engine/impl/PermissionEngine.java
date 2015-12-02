/**
 * @info 
 * @author ������
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
			logger.error("ID����Ϊ�գ�����");
			return false;
		}
		if (super.pdao.getOneByClientID(id) != null) {
			logger.error("��Ȩ���Ѵ���.");
			return false;
		}
		Perm perm = new Perm();
		perm.setClientPermissionID(id);
		return super.pdao.add(perm);
	}

	public Boolean delete(int id) {
		if (id == 0) {
			logger.error("Ȩ��ID����Ϊ�գ�����");
			return false;
		}
		return super.pdao.deleteByID(id);

	}

	/**
	 * ���ܣ�����Ȩ��
	 * 
	 * @param permission
	 *            Ȩ��
	 * @return true/false
	 */
	/*
	 * public Boolean update(Perm permission) { if (permission.equals(null)) {
	 * logger.error("Ȩ��ID����Ϊ�գ�����"); return false; } return
	 * super.pdao.update(permission);
	 * 
	 * }
	 */

	/**
	 * ���ܣ�ͨ��id��һ��Ȩ��
	 * 
	 * @param id
	 * @return permission
	 */
	/*
	 * public Perm getOneByID(int id) { if (id == 0) {
	 * logger.error("Ȩ��ID����Ϊ�գ�����"); return null; } return
	 * super.pdao.getOneByID(id); }
	 */

	/**
	 * ���ܣ�ͨ��id��name
	 * 
	 * @param id
	 * @return
	 */
	/*
	 * public int getClientIDByID(int id) { if (id == 0) {
	 * logger.error("Ȩ��ID����Ϊ�գ�����"); return 0; } return
	 * super.pdao.getNameByID(id); Perm p = super.pdao.getOneByID(id); if (p !=
	 * null) { return p.getClientPermissionID(); } else { return 0; } }
	 */

	/**
	 * ���ܣ��鿴����Ȩ���б�
	 * 
	 * @return Ȩ���б�
	 */
	/*
	 * public List<Perm> getAll() { return super.pdao.getAll(); }
	 */

}

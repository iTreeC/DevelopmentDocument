/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.entity.UserRole;

public class UserRoleEngine extends EngineImpl implements UserRoleEngineAPI {

	private static Logger logger = Logger
			.getLogger(UserPermissionEngine.class);

	private List<Integer> rids = new ArrayList<Integer>();
	private List<Integer> rid2 = new ArrayList<Integer>();

	public Boolean add(int uid, List<Integer> rid) {

		if (uid == 0 || rid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		// ȥ���ظ�����
		rids = this.dereplication(rid);

		// �����ݿ��е����ݱȶ�
		rid2 = super.urdao.findRoleIDByUserID(uid);
		if (rid2 != null)
			rids.removeAll(rid2);

		if (rids.size() == 0) {
			logger.info("��Ȩ���Ѿ����ڣ������ظ����");
			return false;
		}
		return super.urdao.add(uid, rids);
	}

	public Boolean delete(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		return super.urdao.deleteByUserID(uid);
	}

	public Boolean update(int uid, List<Integer> rid) {
		if (uid == 0 || rid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		// ȥ���ظ�����
		rids = this.dereplication(rid);

		return super.urdao.update(uid, rids);
	}

	public List<Integer> getRIds(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		rids = super.urdao.findRoleIDByUserID(uid);

		if (rids != null) {
			logger.info("�����û�Ȩ�޳ɹ�");
			return rids;
		}
		logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ");
		return null;
	}

	public Boolean cando(int uid, int rid) {

		if (uid == 0 || rid == 0) {
			logger.error("��������Ϊ��ֵ������");
			return false;
		}

		List<UserRole> userrole = super.urdao.findListByUserID(uid);
		if (userrole != null) {
			for (int i = 0; i < userrole.size(); i++) {
				if (userrole.get(i).getRole().getId() == rid) {
					logger.info("Ȩ��ƥ��ɹ�");
					return true;
				}
			}
		} else
			logger.error("���޴�Ȩ��");
		logger.error("Ȩ��ƥ��ʧ��");
		return false;
	}

	/*
	 * public List<UserRole> getRoleByUserID(int uid) {
	 * 
	 * if (uid == 0) { logger.error("�û���Ϊ�գ�����"); return null; }
	 * 
	 * List<UserRole> user = super.urdao.findByUId(uid);
	 * 
	 * if (user != null) { logger.info("�����û�Ȩ�޳ɹ�"); return user; }
	 * logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ"); return null;
	 * 
	 * }
	 */
}

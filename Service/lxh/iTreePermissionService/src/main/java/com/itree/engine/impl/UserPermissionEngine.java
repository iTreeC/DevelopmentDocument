/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.UserPermissionEngineAPI;
import com.itree.entity.UserPermission;

public class UserPermissionEngine extends EngineImpl implements
		UserPermissionEngineAPI {

	private static Logger logger = Logger
			.getLogger(UserPermissionEngine.class);

	private  List<Integer> pids = new ArrayList<Integer>();
	private  List<Integer> pid2 = new ArrayList<Integer>();
	public Boolean add(int uid, List<Integer> pid) {

		if (uid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		// ȥ���ظ�����
		pids = this.dereplication(pid);

		// �����ݿ��е�Ȩ��ֵ�ȶ�
		pid2 = super.updao.findUserPermissionID(uid);
		if (pid2 != null)
			pids.removeAll(pid2);

		// ���Ȩ��
		if (pids.size() == 0) {
			logger.error("��Ȩ���Ѿ����ڣ������ظ����");
			return false;
		}
		return super.updao.add(uid, pids);
	}

	public Boolean delete(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		return super.updao.deleteByUserID(uid);
	}

	public Boolean update(int uid, List<Integer> pid) {
		if (uid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		// ȥ���ظ�����
		pids = this.dereplication(pid);

		return super.updao.update(uid, pids);
	}

	public List<Integer> getPermissionIDByUserID(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		pids = super.updao.findUserPermissionID(uid);

		if (pids != null) {
			logger.info("�����û�Ȩ�޳ɹ�");
			return pids;
		}
		logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ");
		return null;
	}

	public Boolean cando(int uid, int pid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return false;
		}
		List<UserPermission> userpermission = super.updao.findListByUserID(uid);
		if (userpermission != null) {
			for (int i = 0; i < userpermission.size(); i++) {
				if (userpermission.get(i).getPerm().getClientPermissionID() == pid) {
					logger.info("Ȩ��ƥ��ɹ�");
					return true;
				}
			}
		} else
			logger.info("���޴�Ȩ��");
		logger.error("Ȩ��ƥ��ʧ��");
		return false;
	}
	/**
	 * ���ܣ��鿴
	 * 
	 * @param uid
	 *            �û�ID
	 * @return �û�-Ȩ�޹�ϵ���б�
	 */
	/*
	 * public List<UserPermission> getPermission(int uid) { if (uid == 0) {
	 * logger.error("�û���Ϊ�գ�����"); return null; } super.updao.findUserPIds(uid);
	 * List<UserPermission> user = super.updao.findByUId(uid);
	 * 
	 * if (user != null) { logger.info("�����û�Ȩ�޳ɹ�"); return user; } else {
	 * logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ"); return null; } }
	 */
}

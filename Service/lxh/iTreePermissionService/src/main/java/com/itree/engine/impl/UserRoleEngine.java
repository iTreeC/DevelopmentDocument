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
import com.itree.entity.Role;

public class UserRoleEngine extends EngineImpl implements UserRoleEngineAPI {

	private static Logger logger = Logger.getLogger(UserPermissionEngine.class);

	private List<Integer> tid = new ArrayList<Integer>();
	private List<Integer> rid = new ArrayList<Integer>();

	public Boolean add(int uid, List<Integer> rid) {
		// �жϲ����Ƿ�Ϸ�
		if (uid == 0 || rid.size() == 0) {
			logger.info("��ɫ���ɫID����Ϊ��ֵ������");
			return false;
		}
		// �õ��û��Ĵ洢ID,���жϴ洢ID�Ƿ����
		uid = udao.getIDByClientID(uid);
		if (uid == 0)
			return false;
		// ȥ����ɫID���ظ�����
		rid = this.dereplication(rid);

		// �õ����ݿ��д洢�Ľ�ɫID,��ȥ�����������е��ظ�ֵ
		tid = super.urdao.findRoleIDByUserID(uid);
		if (tid != null)
			rid.removeAll(tid);

		if (rid.size() == 0) {
			logger.info("�ý�ɫ�Ѿ����ڣ������ظ����");
			return false;
		}
		// ִ����Ӳ���
		return super.urdao.add(uid, rid);
	}

	public Boolean delete(int uid) {
		// �жϲ����Ƿ�Ϸ�
		if (uid == 0) {
			logger.info("�û���Ϊ�գ�����");
			return null;
		}
		// �õ��û��Ĵ洢ID,���жϴ洢ID�Ƿ����
		uid = udao.getIDByClientID(uid);
		if (uid == 0)
			return false;
		// ִ��ɾ���û�-��ɫ��ϵ����
		return super.urdao.deleteByUserID(uid);
	}

	public Boolean update(int uid, List<Integer> rid) {
		// �жϲ����Ƿ�Ϸ�
		if (uid == 0 || rid.size() == 0) {
			logger.info("��ɫ���ɫID����Ϊ��ֵ������");
			return false;
		}
		// �õ��û��Ĵ洢ID,���жϴ洢ID�Ƿ����
		uid = udao.getIDByClientID(uid);
		if (uid == 0)
			return false;
		// ִ�и��²���
		return super.urdao.update(uid, dereplication(rid));
	}

	public List<Integer> getRIds(int uid) {
		// �жϲ����Ƿ�Ϸ�
		if (uid == 0) {
			logger.info("�û���Ϊ�գ�����");
			return null;
		}
		// �õ��û��Ĵ洢ID,���жϴ洢ID�Ƿ����
		uid = udao.getIDByClientID(uid);
		if (uid == 0)
			return null;
		// ͨ���û��洢ID�鿴�û���ɫID
		return urdao.findRoleIDByUserID(uid);
	}

	public List<Role> getUserRole(int uid) {

		// ��������Ϸ����õ�Ҫ���ҵĽ�ɫID
		if (uid == 0)
			return null;
		tid = urdao.findRoleIDByUserID(uid);
		// ��������Ϸ������ؽ�ɫ�б�
		if (tid.size() == 0)
			return null;
		return rdao.getRoleByID(tid);
	}

	public List<Integer> getUserRolePermissionID(int uid) {
		// ��������Ϸ����õ���ɫID
		if (uid == 0)
			return null;
		rid = urdao.findRoleIDByUserID(uid);
		// ��������Ϸ����õ�Ȩ�޴洢ID
		if (rid.size() == 0)
			return null;
		tid.clear();
		for (int i = 0; i < rid.size(); i++)
			tid.addAll(rpdao.findPermissionIDByRoleID(rid.get(i)));
		// ��������Ϸ����õ�Ȩ����ʵID
		if (tid.size() == 0)
			return null;
		return pdao.getClientIDByID(dereplication(tid));

	}

	public Boolean candoRole(int uid, int rid) {

		// �жϲ����Ƿ�Ϸ�
		if (uid == 0 || rid == 0) {
			logger.info("��������Ϊ��ֵ������");
			return false;
		}
		// �õ��û�ID�Ĵ洢ID
		uid = udao.getIDByClientID(uid);
		// �õ���ɫID�����ж��Ƿ�Ϸ�
		tid = super.urdao.findRoleIDByUserID(uid);
		if (tid.size() == 0)
			return false;
		// ������ɫID�����н�ɫƥ��
		for (int i = 0; i < tid.size(); i++) {
			if (tid.get(i) == rid) {
				logger.info("��ɫƥ��ɹ�");
				return true;
			}
		}
		logger.info("��ɫƥ��ʧ��");
		return false;
	}

	public Boolean candoPermission(int uid, int pid) {

		// �жϲ����Ƿ�Ϸ�
		if (uid == 0 || pid == 0) {
			logger.info("��������Ϊ��ֵ������");
			return false;
		}
		// �õ��û���Ȩ�޵Ĵ洢ID�����ж��Ƿ�Ϸ�
		uid = udao.getIDByClientID(uid);
		pid = pdao.getIDByClientID(pid);
		if (uid == 0 || pid == 0)
			return false;
		// �õ���ɫID,���ж��Ƿ�Ϸ�
		rid = super.urdao.findRoleIDByUserID(uid);
		if (rid == null)
			return false;
		// ͨ����ɫID�õ�Ȩ�޵Ĵ洢ID�����ж��Ƿ�Ϸ�
		tid.clear();
		for (int i = 0; i < rid.size(); i++)
			tid = super.rpdao.findPermissionIDByRoleID(rid.get(i));
		if (tid.size() == 0)
			return null;
		// ����Ȩ��ID�Ĵ洢ID������Ȩ��ƥ��
		for (int i = 0; i < tid.size(); i++) {
			if (tid.get(i) == pid) {
				logger.info("Ȩ��ƥ��ɹ�");
				return true;
			}
		}
		logger.info("Ȩ��ƥ��ʧ��");
		return false;
	}

	/*
	 * public List<UserRole> getRoleByUserID(int uid) {
	 * 
	 * if (uid == 0) { logger.info("�û���Ϊ�գ�����"); return null; }
	 * 
	 * List<UserRole> user = super.urdao.findByUId(uid);
	 * 
	 * if (user != null) { logger.info("�����û���ɫ�ɹ�"); return user; }
	 * logger.info("���޴��û���ɫ����ȷ���û�ID�Ƿ���ȷ"); return null;
	 * 
	 * }
	 */
}

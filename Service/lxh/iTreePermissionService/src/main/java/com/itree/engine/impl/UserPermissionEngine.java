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

public class UserPermissionEngine extends EngineImpl implements
		UserPermissionEngineAPI {

	private static Logger logger = Logger.getLogger(UserPermissionEngine.class);

	private List<Integer> tid = new ArrayList<Integer>();

	public Boolean add(int uid, List<Integer> pid) {

		// ��������Ϸ����õ��û���Ȩ�޵Ĵ洢ID
		if (uid == 0 || pid.size() == 0) {
			logger.info("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		uid = udao.getIDByClientID(uid);
		pid = pdao.getIDByClientID(dereplication(pid));
		// ��������Ϸ��������ݿ��е�Ȩ��ֵ�ȶԣ���ȥ���ظ�����
		if (uid == 0 || pid.size() == 0)
			return false;
		tid = super.updao.findUserPermissionID(uid);
		if (tid != null)
			pid.removeAll(tid);
		// ��������Ϸ������Ȩ��
		if (pid.size() == 0) {
			logger.info("��Ȩ���Ѿ����ڣ������ظ����");
			return false;
		}
		return super.updao.add(uid, pid);
	}

	public Boolean delete(int uid) {

		// ��������Ϸ����õ��û��Ĵ洢ID
		if (uid == 0) {
			logger.info("�û���Ϊ�գ�����");
			return null;
		}
		uid = udao.getIDByClientID(uid);
		// ��������Ϸ���ɾ��Ȩ��
		if (uid == 0)
			return false;
		return super.updao.deleteByUserID(uid);
	}

	public Boolean update(int uid, List<Integer> pid) {

		// ��������Ϸ����õ��û���Ȩ�޵Ĵ洢ID
		if (uid == 0 || pid.equals(null)) {
			logger.info("��������Ϊ��ֵ������");
			return false;
		}
		uid = udao.getIDByClientID(uid);
		pid = pdao.getIDByClientID(dereplication(pid));
		// ��������Ϸ��������û�-Ȩ�����й�ϵ
		if (uid == 0 || pid.size() == 0)
			return false;
		return super.updao.update(uid, pid);
	}

	public List<Integer> getPermissionIDByUserID(int uid) {
		// ��������Ϸ����õ��û��Ĵ洢ID
		if (uid == 0) {
			logger.info("�û���Ϊ�գ�����");
			return null;
		}
		uid = udao.getIDByClientID(uid);
		// ��������Ϸ����õ��û�Ȩ�޵Ĵ洢ID
		if (uid == 0)
			return null;
		tid = super.updao.findUserPermissionID(uid);
		// ��������Ϸ����õ��û�Ȩ�޵���ʵID
		if (tid == null) {
			logger.info("--");
			return null;
		}
		tid = pdao.getClientIDByID(tid);

		// ���������null������Чֵ�������ظ��ͻ��ˡ�
		logger.info("�����û�Ȩ�޳ɹ�");
		return tid;
	}
	
	public List<Integer> getUserPermissionID(int uid) {
		// ��������Ϸ����õ�Ȩ�޴洢ID
		if (uid == 0)
			return null;
		tid = updao.findUserPermissionID(uid);
		// ��������Ϸ���������ʵȨ��ID
		if (tid.size() == 0)
			return null;
		return pdao.getClientIDByID(tid);

	}

	public Boolean cando(int uid, int pid) {

		// ��������Ϸ����õ�Ҫƥ����û���Ȩ�޵Ĵ洢ID
		if (uid == 0 || pid == 0) {
			logger.info("�û���Ϊ�գ�����");
			return false;
		}
		uid = udao.getIDByClientID(uid);
		pid = pdao.getIDByClientID(pid);

		// ��������Ϸ����õ�������û�Ȩ�޵Ĵ洢ID
		if (uid == 0 || pid == 0)
			return false;
		List<Integer> tids = super.updao.findUserPermissionID(uid);
		// ��������Ϸ�������������û�Ȩ�޵Ĵ洢ID������Ȩ��ƥ��
		if (tids.size() == 0)
			return false;
		for (int i = 0; i < tids.size(); i++) {
			if (tids.get(i) == pid) {
				logger.info("Ȩ��ƥ��ɹ�");
				return true;
			}
		}
		logger.info("Ȩ��ƥ��ʧ��");
		return false;
	}

}

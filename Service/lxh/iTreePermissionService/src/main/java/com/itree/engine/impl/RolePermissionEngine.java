/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.RolePermissionEngineAPI;

public class RolePermissionEngine extends EngineImpl implements
		RolePermissionEngineAPI {

	private static Logger logger = Logger.getLogger(RolePermissionEngine.class);

	private List<Integer> tid = new ArrayList<Integer>();

	public Boolean add(int rid, List<Integer> pid) {
		// ��������Ϸ���ͨ��Ȩ��ʵ��ID�õ��洢ID
		if (rid == 0 || pid.size() == 0) {
			logger.info("��������Ϊ��ֵ������");
			return false;
		}
		pid = pdao.getIDByClientID(dereplication(pid));
		// ��������Ϸ���ͨ����ɫID�õ�Ȩ�޵Ĵ洢ID
		if (pid.size() == 0) {
			logger.info("��ЩȨ��û�������ݿ��д洢�����Ƚ��д洢��");
			return false;
		}
		tid = rpdao.findPermissionIDByRoleID(rid);
		// ȥ���Ѿ������˵�Ȩ��ID
		if (tid != null & pid.size() != 0)
			pid.removeAll(tid);

		// ��������ǿգ�ִ�����Ȩ��
		if (pid.size() == 0)
			return false;
		return rpdao.add(rid, pid);
	}

	public Boolean delete(int rid) {
		// ��������Ϸ�
		if (rid == 0) {
			logger.info("��ɫΪ��ֵ������");
			return false;
		}
		// ִ��ɾ��Ȩ��
		rpdao.deleteByRoleID(rid);
		return true;
	}

	public Boolean update(int rid, List<Integer> pid) {
		// ��������Ϸ����õ�Ȩ�޵Ĵ洢ID
		if (rid == 0 || pid.equals(null)) {
			logger.info("��������Ϊ��ֵ������");
			return false;
		}
		pid = pdao.getIDByClientID(dereplication(pid));
		// ִ�и��²���
		return rpdao.update(rid, pid);
	}

	public Boolean cando(int rid, int pid) {
		// �жϲ����Ƿ�Ϸ�
		if (rid == 0 || pid == 0) {
			logger.info("��ɫΪ��ֵ������");
			return false;
		}
		// ͨ��Ȩ��ʵ��ID�õ��洢ID
		pid = pdao.getIDByClientID(pid);
		// ͨ����ɫID�õ��洢ID
		tid = rpdao.findPermissionIDByRoleID(rid);
		// ��������Ϸ�,ѭ��ִ��ƥ��
		if (pid == 0 || tid.size() == 0)
			return false;
		for (int i = 0; i < tid.size(); i++) {
			if (tid.get(i) == pid) {
				logger.info("Ȩ��ƥ��ɹ�");
				return true;
			}
		}
		// ѭ��������ƥ��ʧ��
		logger.info("Ȩ��ƥ��ʧ��");
		return false;
	}
	/*
	 * public List<Integer> getPermissionID(int rid) { // ��������Ϸ����õ�Ȩ�޵Ĵ洢ID if
	 * (rid == 0) { logger.info("��ɫΪ��ֵ������"); return null; } tid =
	 * rpdao.findPermissionIDByRoleID(rid); // ��������Ϸ������ؽ��-Ȩ����ʵID if (tid !=
	 * null) { logger.info("���ҽ�ɫȨ�޳ɹ�"); return pdao.getClientIDByID(tid); }
	 * logger.info("���޴˽�ɫ��Ȩ�ޣ���ȷ�Ͻ�ɫID�Ƿ���ȷ��"); return null;
	 * 
	 * }
	 */

}

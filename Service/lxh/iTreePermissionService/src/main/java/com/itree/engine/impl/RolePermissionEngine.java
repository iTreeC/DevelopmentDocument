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
import com.itree.entity.RolePermission;

public class RolePermissionEngine extends EngineImpl implements
		RolePermissionEngineAPI {

	private static Logger logger = Logger.getLogger(RolePermissionEngine.class);

	private List<Integer> pid = new ArrayList<Integer>();
	private int tempt;

	public Boolean add(int rid, List<Integer> cpid) {
		// ��ֵ
		if (rid == 0 || cpid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		// �õ�pid
		cpid = super.dereplication(cpid);
		cpid = pdao.getIDByClientID(cpid);
		// �����ݿ��е�Ȩ��ֵ�ȶ�
		pid = rpdao.findPermissionIDByRoleID(rid);
		if (pid != null & cpid != null)
			cpid.removeAll(pid);

		// ���Ȩ��
		if (cpid.size() == 0) {
			logger.info("��Ȩ���Ѿ����ڣ������ظ����");
			return false;

		}
		return rpdao.add(rid, cpid);
	}

	public Boolean delete(int rid) {
		// ��ֵ
		if (rid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return false;
		}
		// ɾ��Ȩ��
		rpdao.deleteByRoleID(rid);
		logger.info("ɾ��Ȩ�޳ɹ�");
		return true;
	}

	public Boolean update(int rid, List<Integer> cpid) {
		// ��ֵ
		if (rid == 0 || cpid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		// �õ�pid
		cpid = super.dereplication(cpid);
		cpid = pdao.getIDByClientID(cpid);

		// ����
		return rpdao.update(rid, cpid);
	}

	public List<Integer> getPermissionID(int rid) {
		// ��ֵ
		if (rid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return null;
		}
		// ����
		pid = rpdao.findPermissionIDByRoleID(rid);

		if (pid != null) {
			logger.info("���ҽ�ɫȨ�޳ɹ�");
			return pid;
		}
		logger.error("���޴˽�ɫ��Ȩ�ޣ���ȷ�Ͻ�ɫID�Ƿ���ȷ��");
		return null;

	}

	public Boolean cando(int rid, int cpid) {
		// ��ֵ
		if (rid == 0 || cpid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return false;
		}

		// �õ�pid
		tempt = pdao.getOneByClientID(cpid).getId();

		// ƥ��
		List<RolePermission> role = rpdao.findListByRoleID(rid);
		if (role != null) {
			for (int i = 0; i < role.size(); i++) {
				if (role.get(i).getPerm().getId() == tempt) {
					logger.info("Ȩ��ƥ��ɹ�");
					return true;
				}
			}
		} else
			logger.info("���޴˽�ɫ");

		logger.info("Ȩ��ƥ��ʧ��");
		return false;
	}

}

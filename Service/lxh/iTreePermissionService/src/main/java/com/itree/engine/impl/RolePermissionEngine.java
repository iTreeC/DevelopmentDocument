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

	private static Logger logger = Logger
			.getLogger(RolePermissionEngine.class);

	private List<Integer> pids = new ArrayList<Integer>();
	private List<Integer> pid2 = new ArrayList<Integer>();

	public Boolean add(int rid, List<Integer> pid) {
		// ��ֵ
		if (rid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		// ȥ���ظ�����
		pids = this.dereplication(pid);

		// �����ݿ��е�Ȩ��ֵ�ȶ�
		pid2 = super.rpdao.findPermissionIDByRoleID(rid);
		if (pid2 != null)
			pids.removeAll(pid2);

		// ���Ȩ��
		if (pids.size() == 0) {
			logger.info("��Ȩ���Ѿ����ڣ������ظ����");
			return false;

		}
		return super.rpdao.add(rid, pids);
	}

	public Boolean delete(int rid) {
		// ��ֵ
		if (rid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return false;
		}
		// ɾ��Ȩ��
		super.rpdao.deleteByRoleID(rid);
		logger.info("ɾ��Ȩ�޳ɹ�");
		return true;
	}

	public Boolean update(int rid, List<Integer> pid) {
		// ��ֵ
		if (rid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		// ȥ���ظ�����
		pids = this.dereplication(pid);

		// ����
		return super.rpdao.update(rid, pids);
	}

	public List<Integer> getPermissionID(int rid) {
		// ��ֵ
		if (rid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return null;
		}
		// ����
		pids = super.rpdao.findPermissionIDByRoleID(rid);

		if (pids != null) {
			logger.info("���ҽ�ɫȨ�޳ɹ�");
			return pids;
		}
		logger.error("���޴˽�ɫ��Ȩ�ޣ���ȷ�Ͻ�ɫID�Ƿ���ȷ��");
		return null;

	}

	public Boolean cando(int rid, int pid) {
		// ��ֵ
		if (rid == 0 || pid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return false;
		}
		// ƥ��
		List<RolePermission> role = super.rpdao.findListByRoleID(rid);
		if (role != null) {
			for (int i = 0; i < role.size(); i++) {
				if (role.get(i).getPerm().getId() == pid) {
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

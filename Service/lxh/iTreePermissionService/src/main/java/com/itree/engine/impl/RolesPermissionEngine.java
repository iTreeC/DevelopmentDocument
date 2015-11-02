/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.itree.dao.api.RolePermissionDao;
import com.itree.engine.api.RolesPermissionEngineAPI;
import com.itree.entity.RolePermission;

public class RolesPermissionEngine implements RolesPermissionEngineAPI {

	private static Logger logger = Logger
			.getLogger(RolesPermissionEngine.class);

	RolePermissionDao rpdao;

	@Resource  @Required
	public void setRpdao(RolePermissionDao rpdao) {
		this.rpdao = rpdao;
	}

	RolePermission role;

	public Boolean add(int rid, List<Integer> pid) {
		// ��ֵ
		if (rid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		// ȥ���ظ�����
		List<Integer> pids = new ArrayList<Integer>();
		for (Integer i : pid) {
			if (!pids.contains(i)) {
				pids.add(i);
			}
		}

		// �����ݿ��е�Ȩ��ֵ�ȶ�
		List<Integer> pid2 = rpdao.findRolePIds(rid);
		if (pid2 != null)
			pids.removeAll(pid2);

		// ���Ȩ��
		if (pids.size() == 0) {
			logger.info("��Ȩ���Ѿ����ڣ������ظ����");
			return false;

		}
		return rpdao.add(rid, pids);
	}

	public Boolean delete(int rid) {
		// ��ֵ
		if (rid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return false;
		}
		// ɾ��Ȩ��
		rpdao.deleteByRId(rid);
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
		List<Integer> pids = new ArrayList<Integer>();
		for (Integer i : pid) {
			if (!pids.contains(i)) {
				pids.add(i);
			}
		}
		// ����
		return rpdao.updatePermission(rid, pids);
	}

	public List<Integer> getPermissionID(int rid) {
		// ��ֵ
		if (rid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return null;
		}
		// ����
		List<Integer> pids = rpdao.findRolePIds(rid);
		if (pids != null) {
			logger.info("���ҽ�ɫȨ�޳ɹ�");
			return pids;
		} else {
			logger.error("���޴˽�ɫ��Ȩ�ޣ���ȷ�Ͻ�ɫID�Ƿ���ȷ��");
			return null;
		}
	}

	public Boolean cando(int rid, int pid) {
		// ��ֵ
		if (rid == 0 || pid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return false;
		}
		// ƥ��
		List<RolePermission> role = rpdao.findByRId(rid);
		if (role != null) {
			for (int i = 0; i < role.size(); i++) {
				if (role.get(i).getPid() == pid) {
					logger.info("Ȩ��ƥ��ɹ�");
					return true;
				}
			}
		} else {
			logger.info("���޴˽�ɫ");
		}
		logger.info("Ȩ��ƥ��ʧ��");
		return false;
	}

}

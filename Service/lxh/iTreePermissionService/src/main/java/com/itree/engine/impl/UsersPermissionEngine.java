/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.itree.dao.api.UserPermissionDao;
import com.itree.engine.api.UsersPermissionEngineAPI;
import com.itree.entity.UserPermission;

public class UsersPermissionEngine implements UsersPermissionEngineAPI {

	private static Logger logger = Logger
			.getLogger(UsersPermissionEngine.class);

	UserPermissionDao updao;

	@Resource  @Required
	public void setUpdao(UserPermissionDao updao) {
		this.updao = updao;
	}

	public Boolean add(int uid, List<Integer> pids) {

		if (uid == 0 || pids.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		// ȥ���ظ�����
		HashSet<Integer> id = new HashSet<Integer>(pids);
		pids.clear();
		pids.addAll(id);

		List<Integer> pid2 = updao.findUserPIds(uid);
		if (pid2 != null)
			pids.removeAll(pid2);

		if (pids.size() == 0) {
			logger.error("��Ȩ���Ѿ����ڣ������ظ����");
			return false;
		}
		return updao.add(uid, pids);
	}

	public Boolean delete(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		return updao.deleteByUId(uid);
	}

	public Boolean update(int uid, List<Integer> pid) {
		if (uid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		// ȥ���ظ�����
		HashSet<Integer> pid2 = new HashSet<Integer>(pid);
		pid.clear();
		pid.addAll(pid2);

		return updao.updatePermission(uid, pid);
	}

	public List<UserPermission> getPermission(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		updao.findUserPIds(uid);
		updao.findAll();
		List<UserPermission> user = updao.findByUId(uid);

		if (user != null) {
			logger.info("�����û�Ȩ�޳ɹ�");
			return user;
		} else {
			logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ");
			return null;
		}
	}

	public List<Integer> getPermissionID(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		List<Integer> pids = updao.findUserPIds(uid);

		if (pids != null) {
			logger.info("�����û�Ȩ�޳ɹ�");
			return pids;
		} else {
			logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ");
			return null;
		}
	}

	public Boolean cando(int uid, int pid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return false;
		}
		List<UserPermission> user = updao.findByUId(uid);
		if (user != null) {
			for (int i = 0; i < user.size(); i++) {
				if (user.get(i).getPid() == pid) {
					logger.info("Ȩ��ƥ��ɹ�");
					return true;
				}
			}
		} else {
			logger.info("���޴�Ȩ��");
		}
		logger.error("Ȩ��ƥ��ʧ��");
		return false;
	}

}

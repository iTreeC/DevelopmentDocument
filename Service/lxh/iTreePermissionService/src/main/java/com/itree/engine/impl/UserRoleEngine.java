package com.itree.engine.impl;

import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.itree.dao.api.UserRoleDao;
import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.entity.UserRole;

public class UserRoleEngine implements UserRoleEngineAPI {

	private static Logger logger = Logger
			.getLogger(UsersPermissionEngine.class);

	UserRoleDao urdao;

	public void setUrdao(UserRoleDao urdao) {
		this.urdao = urdao;
	}

	public Boolean add(int uid, List<Integer> rids) {

		if (uid == 0 || rids.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		// ȥ���ظ�����
		HashSet<Integer> id = new HashSet<Integer>(rids);
		rids.clear();
		rids.addAll(id);

		// �����ݿ��е����ݱȶ�
		List<Integer> rid2 = urdao.findUserRIds(uid);
		if (rid2 != null)
			rids.removeAll(rid2);

		if (rids.size() == 0) {
			logger.info("��Ȩ���Ѿ����ڣ������ظ����");
			return false;
		}
		return urdao.add(uid, rids);
	}

	public Boolean delete(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		return urdao.deleteByUId(uid);
	}

	public Boolean update(int uid, List<Integer> rid) {
		if (uid == 0 || rid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		// ȥ���ظ�����
		HashSet<Integer> rid2 = new HashSet<Integer>(rid);
		rid.clear();
		rid.addAll(rid2);

		return urdao.update(uid, rid);
	}

	public List<UserRole> getRoleByUser(int uid) {

		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}

		List<UserRole> user = urdao.findByUId(uid);

		if (user != null) {
			logger.info("�����û�Ȩ�޳ɹ�");
			return user;
		} else {
			logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ");
			return null;
		}
	}

	public List<Integer> getRIds(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		List<Integer> rids = urdao.findUserRIds(uid);

		if (rids != null) {
			logger.info("�����û�Ȩ�޳ɹ�");
			return rids;
		} else {
			logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ");
			return null;
		}
	}

	public Boolean cando(int uid, int rid) {

		if (uid == 0 || rid == 0) {
			logger.error("��������Ϊ��ֵ������");
			return false;
		}
		List<Integer> id = urdao.findUserRIds(uid);
		if (id != null) {
			for (int i = 0; i < id.size(); i++) {
				if (id.get(i) == rid) {
					logger.info("Ȩ��ƥ��ɹ�");
					return true;
				}
			}
		} else {
			logger.error("���޴�Ȩ��");
			return false;
		}
		logger.error("Ȩ��ƥ��ʧ��");
		return false;
	}
}

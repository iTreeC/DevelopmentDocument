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

import com.itree.dao.api.UserRoleDao;
import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.entity.UserRole;

public class UserRoleEngine implements UserRoleEngineAPI {

	private static Logger logger = Logger
			.getLogger(UsersPermissionEngine.class);

	UserRoleDao urdao;

	@Resource
	@Required
	public void setUrdao(UserRoleDao urdao) {
		this.urdao = urdao;
	}

	/**
	 * ���ܣ����
	 * 
	 * @param uid
	 *            �û�ID
	 * @param rid
	 *            ��ɫID
	 * @return true/false
	 */
	public Boolean add(int uid, List<Integer> rid) {

		if (uid == 0 || rid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		// ȥ���ظ�����
		HashSet<Integer> id = new HashSet<Integer>(rid);
		rid.clear();
		rid.addAll(id);

		// �����ݿ��е����ݱȶ�
		List<Integer> rid2 = urdao.findUserRIds(uid);
		if (rid2 != null)
			rid.removeAll(rid2);

		if (rid.size() == 0) {
			logger.info("��Ȩ���Ѿ����ڣ������ظ����");
			return false;
		}
		return urdao.add(uid, rid);
	}

	/**
	 * ���ܣ�ɾ��
	 *
	 * @param uid
	 *            �û�ID
	 * @return true/false
	 */
	public Boolean delete(int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		return urdao.deleteByUId(uid);
	}

	/**
	 * ���ܣ�����
	 * 
	 * @param uid
	 *            �û�ID
	 * @param rid
	 *            ��ɫID
	 * @return true/false
	 */
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

	/**
	 * ���ܣ�ͨ���û�ID��ѯ
	 * 
	 * @param uid
	 *            �û�ID
	 * @return �û�-��ɫ�б�
	 */
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

	/**
	 * ���ܣ�ͨ���û�ID��ѯ
	 * 
	 * @param uid
	 *            �û�ID
	 * @return ��ɫID�ļ���
	 */
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

	/**
	 * ���ܣ��û�-��ɫ ƥ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @param rid
	 *            ��ɫID
	 * @return true/false
	 */
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

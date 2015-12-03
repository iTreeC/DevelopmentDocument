/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.itree.dao.api.UserPermissionDao;
import com.itree.entity.UserPermission;
import com.itree.utils.SessionUtils;

@Component
public class UserPermissionDaoImpl implements UserPermissionDao {

	private static Logger logger = Logger
			.getLogger(RolePermissionDaoImpl.class);

	Session session;
	Transaction transaction;
	List<UserPermission> users;
	UserPermission user;

	/**
	 * ���ܣ����
	 * 
	 * @param uid
	 *            �û�ID
	 * @param pid
	 *            Ȩ��ID
	 * @return true/false *
	 */
	public boolean add(int uid, List<Integer> pid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			for (int i = 0; i < pid.size(); i++) {
				user = new UserPermission();
				user.setUid(uid);
				user.setPid(pid.get(i));
				session.save(user);
			}
			transaction.commit();
			logger.error("����" + user + "��Ȩ�޳ɹ�");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * ����:ͨ���û�IDɾ�� ����ɾ�������û�����Ȩ�ޣ�
	 * 
	 * @param uid
	 *            �û�ID
	 * @return true/false
	 */
	public boolean deleteByUId(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "delete from UserPermission where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			transaction.commit();
			logger.error("ɾ��" + uid + "��Ȩ�޳ɹ�");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * ���ܣ������û�Ȩ�ޣ���ɾ�������û�����Ȩ�޹�ϵ��Ȼ������µ�Ȩ�޹�ϵ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @param pid
	 *            Ȩ��ID
	 * @return true/false
	 */
	public boolean updatePermission(int uid, List<Integer> pid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();

		try {
			// ɾ��ԭ������
			String hql = "delete from UserPermission where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			// ������������
			for (int i = 0; i < pid.size(); i++) {
				user = new UserPermission();
				user.setUid(uid);
				user.setPid(pid.get(i));
				session.save(user);
			}
			transaction.commit();
			logger.error("����" + user + "��Ȩ�޳ɹ�");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * ���ܣ��鿴�����û�Ȩ���б�
	 * 
	 * @return Ȩ���б�
	 */
	public List<UserPermission> findAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			users = session.createQuery("from UserPermission").list();
			logger.info("�鿴����Ȩ�޳ɹ�");
			transaction.commit();
			return users;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * ���ܣ�ͨ���û�ID�鿴�û�Ȩ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @return �û�Ȩ���б�
	 */
	public List<UserPermission> findByUId(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "from UserPermission where uid =? ";
			users = session.createQuery(hql).setParameter(0, uid).list();
			logger.info("findByUId �鿴" + uid + "��Ȩ�޳ɹ�");
			transaction.commit();
			return users;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * ���ܣ�ͨ���û�ID�鿴�û�Ȩ��ID
	 * 
	 * @param uid
	 *            �û�ID
	 * @return Ȩ��ID����
	 */
	public List<Integer> findUserPIds(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String sql = "select pid from UserPermission where uid = ?";
			List<Integer> pids = (List<Integer>) session.createQuery(sql)
					.setParameter(0, uid).list();
			transaction.commit();
			logger.info("findUserPIds �鿴" + uid + "��Ȩ�޳ɹ�");
			return pids;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

}

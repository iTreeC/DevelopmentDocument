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

import com.itree.dao.api.UserRoleDao;
import com.itree.entity.UserRole;
import com.itree.utils.SessionUtils;

@Component
public class UserRoleDaoImpl implements UserRoleDao {

	private static Logger logger = Logger.getLogger(UserRoleDaoImpl.class);

	Session session;
	Transaction transaction;
	List<UserRole> userroles;
	UserRole userrole;

	/**
	 * ���� �����
	 * 
	 * @param uid
	 *            �û�ID
	 * @param rid
	 *            ��ɫID
	 * @return true/false
	 */
	public boolean add(int uid, List<Integer> rid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			for (int i = 0; i < rid.size(); i++) {
				userrole = new UserRole();
				userrole.setUid(uid);
				userrole.setRid(rid.get(i));
				session.save(userrole);
			}
			transaction.commit();
			logger.info("�ɹ�����IDΪ ��" + uid + "�Ľ�ɫID");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	/**
	 * ���ܣ�ͨ���û�IDɾ������ɾ�������û������еĽ�ɫ��ϵ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @return true/false
	 */
	public boolean deleteByUId(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "delete from UserRole where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			transaction.commit();
			logger.info("�ɹ�ɾ��IDΪ��" + uid + " �Ľ�ɫID");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * ���ܣ����£���ɾ���û����н�ɫ��ϵ���ڲ����µĽ�ɫ��ϵ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @param rid
	 *            ��ɫID
	 * @return true/false
	 */
	public boolean update(int uid, List<Integer> rid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			// ɾ��ԭ������
			String hql = "delete from UserRole where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			// ������������
			for (int i = 0; i < rid.size(); i++) {
				userrole = new UserRole();
				userrole.setUid(uid);
				userrole.setRid(rid.get(i));
				session.save(userrole);
			}
			transaction.commit();
			logger.info("�ɹ�����IDΪ " + uid + "�Ľ�ɫID ");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * ���ܣ��鿴�û�-��ɫ�б�
	 * 
	 * @return �û�-��ɫ�б�
	 */
	public List<UserRole> findAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			userroles = session.createQuery("from UserRole").list();
			logger.info("�鿴�����û���ɫ��ϵ�ɹ�");
			transaction.commit();
			return userroles;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * ���ܣ�ͨ���û�ID�鿴 �û�-��ɫ �б�
	 * 
	 * @param uid
	 *            �û�ID
	 * @return �û�-��ɫ�б�
	 */
	public List<UserRole> findByUId(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "from UserRole where uid =? ";

			userroles = session.createQuery(hql).setParameter(0, uid).list();
			logger.info("�ɹ��鿴���� ID Ϊ��" + uid + " �Ľ�ɫID");
			transaction.commit();
			return userroles;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * ���ܣ�ͨ���û�ID�鿴�û���ɫID����
	 * 
	 * @param uid
	 *            �û�ID
	 * @return �û���ɫID�ļ���
	 */
	public List<Integer> findUserRIds(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "select rid from UserRole where uid = ?";
			List<Integer> rids = (List<Integer>) session.createQuery(hql)
					.setParameter(0, uid).list();
			transaction.commit();
			logger.info("�ɹ��鿴ID Ϊ��" + uid + "�Ľ�ɫID");
			return rids;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

}

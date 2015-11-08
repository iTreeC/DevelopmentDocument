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

import com.itree.dao.api.PermissionDao;
import com.itree.entity.Perm;
import com.itree.utils.SessionUtils;
@Component
public class PermissionDaoImpl implements PermissionDao {

	private static Logger logger = Logger.getLogger(PermissionDaoImpl.class);

	Session session = SessionUtils.getInstance().getSession();
	Transaction transaction = null;

	Perm perm;

	/**
	 * ���ܣ����
	 * @param perm 
	 * @return true/false
	 */
	public boolean add(Perm perm) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			session.save(perm);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * ���ܣ�ͨ��IDɾ��
	 * @param id
	 * @return true/false
	 */
	public boolean deleteByRId(int id) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "delete from Perm where id=?";
			session.createQuery(hql).setParameter(0, id).executeUpdate();
			transaction.commit();
			logger.info("�ɹ�ɾ��IDΪ" + id + " ��Ȩ��");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	/**
	 * ���ܣ�����
	 * @param perm
	 * @return true/false
	 */
	public boolean update(Perm perm) {

		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			session.update(perm);
			transaction.commit();
			logger.info("�ɹ�����IDΪ��" + perm.getId() + " ��Ȩ��");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	/**
	 * ���ܣ��鿴�б�
	 * @return Ȩ���б�
	 */
	public List<Perm> getAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			@SuppressWarnings("unchecked")
			List<Perm> perms = session.createQuery("from Perm ").list();

			transaction.commit();
			logger.info("�ɹ��鿴����Ȩ���б�");
			return perms;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * ���ܣ��鿴����Ȩ��ֵ����
	 * @return Ȩ��ֵ����
	 */
	public List<String> getAllName() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String sql = "select name from tb_permission";
			@SuppressWarnings("unchecked")
			List<String> names = session.createSQLQuery(sql).list();
			transaction.commit();
			logger.info("�ɹ��鿴����Ȩ��ֵ��");
			return names;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * ���ܣ�ͨ��id�鿴һ��Ȩ��
	 * @param id
	 * @return һ��Ȩ��
	 */
	public Perm getOneById(int id) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			perm = (Perm) session.get(Perm.class, id);
			transaction.commit();
			logger.info("�ɹ��鿴IDΪ�� " + id + " ��Ȩ�ޡ�");
			return perm;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * ���ܣ�ͨ��name�鿴һ��Ȩ����Ϣ
	 * @param name
	 * @return һ��Ȩ����Ϣ
	 */
	public Perm getOneByName(String name) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			perm = (Perm) session.createQuery("from Perm where name=? ")
					.setParameter(0, name).uniqueResult();
			transaction.commit();
			logger.info("�ɹ��鿴����Ȩ�ޡ�");
			return perm;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

}

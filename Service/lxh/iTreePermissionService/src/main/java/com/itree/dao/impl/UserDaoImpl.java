/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.itree.dao.api.UserDao;
import com.itree.entity.User;
import com.itree.utils.SessionUtils;

@Component
public class UserDaoImpl implements UserDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	Session session = SessionUtils.getInstance().getSession();
	Transaction transaction = null;

	User user;

	public boolean add(User user) {
		if (user == null) {
			logger.error("��������");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			session.save(user);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean deleteByClientUserId(int id) {
		if (id == 0) {
			logger.error("��������");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String sql = "from User where ClientUserID=? ";
			User u = (User) session.createQuery(sql).setParameter(0, id)
					.uniqueResult();
			if (u != null) {
				String hql = "delete from UserPermission where uid=?";
				session.createQuery(hql).setParameter(0, u.getId())
						.executeUpdate();

				hql = "delete from UserRole where uid=?";
				session.createQuery(hql).setParameter(0, u.getId())
						.executeUpdate();

				hql = "delete from User where id=?";
				session.createQuery(hql).setParameter(0, u.getId())
						.executeUpdate();
			}
			transaction.commit();
			logger.info("�ɹ�ɾ��IDΪ" + id + " ��Ȩ��");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean update(User user) {
		if (user == null) {
			logger.error("��������");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			session.update(user);
			transaction.commit();
			logger.info("�ɹ�����IDΪ��" + user.getId() + " ��Ȩ��");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public List<User> getAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			List<User> perms = session.createQuery("from User ").list();

			transaction.commit();
			logger.info("�ɹ��鿴����Ȩ���б�");
			return perms;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public User getOneByID(int id) {
		if (id == 0) {
			logger.error("��������");
			return null;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			user = (User) session.get(User.class, id);
			transaction.commit();
			logger.info("�ɹ��鿴IDΪ�� " + id + " ��Ȩ�ޡ�");
			return user;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public User getOneByClientUserID(int id) {
		if (id == 0) {
			logger.error("��������");
			return null;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			user = (User) session
					.createQuery("from User where ClientUserID=? ")
					.setParameter(0, id).uniqueResult();
			transaction.commit();
			logger.info("�ɹ��鿴ClientUserIDΪ�� " + id + " ��Ȩ�ޡ�");
			return user;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public List<Integer> getIDByClientID(List<Integer> id) {
		if (id.size() == 0) {
			logger.error("��������");
			return null;
		}
		List<Integer> ids = new ArrayList<Integer>();
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			for (int i = 0; i < id.size(); i++) {
				user = (User) session
						.createQuery("from User where ClientUserID=? ")
						.setParameter(0, id).uniqueResult();
				ids.add(user.getId());
			}
			transaction.commit();
			logger.info("�ɹ��鿴ClientUserIDΪ�� " + id + " ��Ȩ�ޡ�");
			return ids;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public int getIDByClientID(int id) {
		if (id == 0) {
			logger.error("��������");
			return 0;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			user = (User) session
					.createQuery("from User where ClientUserID=? ")
					.setParameter(0, id).uniqueResult();
			transaction.commit();
			logger.info("�ɹ��鿴ClientUserIDΪ�� " + id + " ��Ȩ�ޡ�");
			return user.getId();
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return 0;
		}
	}
}

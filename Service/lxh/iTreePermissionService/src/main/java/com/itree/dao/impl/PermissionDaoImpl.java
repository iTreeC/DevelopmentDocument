/**
 * @info 
 * @author 李晓欢
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

	public boolean deleteByID(int id) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "from Permission where ClientPermissionID=? ";
			Perm p = (Perm) session.createQuery(hql).setParameter(0, id)
					.uniqueResult();
			if (p != null) {
				hql = " delete from RolePermission where pid=? ";
				session.createQuery(hql).setParameter(0, p.getId())
						.executeUpdate();

				hql = "delete from UserPermission where pid=? ";
				session.createQuery(hql).setParameter(0, p.getId())
						.executeUpdate();

				hql = "delete from Perm where id=?";
				session.createQuery(hql).setParameter(0, p.getId())
						.executeUpdate();
			}

			transaction.commit();
			logger.info("成功删除");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean update(Perm perm) {

		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			session.update(perm);
			transaction.commit();
			logger.info("成功更新ID为：" + perm.getId() + " 的权限");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public List<Perm> getAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			@SuppressWarnings("unchecked")
			List<Perm> perms = session.createQuery("from Perm ").list();

			transaction.commit();
			logger.info("成功查看所有权限列表。");
			return perms;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public Perm getOneByID(int id) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			perm = (Perm) session.get(Perm.class, id);
			transaction.commit();
			logger.info("成功查看ID为： " + id + " 的权限。");
			return perm;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public int getClientIDByID(int id) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			perm = (Perm) session.createQuery("from Perm where id=? ")
					.setParameter(0, id).uniqueResult();
			transaction.commit();
			logger.info("成功查看ID为： " + id + " 的权限。");
			return perm.getClientPermissionID();
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return 0;
		}
	}

	public Perm getOneByClientID(int id) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			perm = (Perm) session
					.createQuery("from Perm where ClientPermissionID=? ")
					.setParameter(0, id).uniqueResult();
			transaction.commit();
			logger.info("成功查看ClientPermissionID为： " + id + " 的权限。");
			return perm;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

}

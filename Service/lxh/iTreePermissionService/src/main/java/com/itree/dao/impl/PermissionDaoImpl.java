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

	/**
	 * 功能：添加
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
	 * 功能：通过ID删除
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
			logger.info("成功删除ID为" + id + " 的权限");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	/**
	 * 功能：更新
	 * @param perm
	 * @return true/false
	 */
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

	/**
	 * 功能：查看列表
	 * @return 权限列表
	 */
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

	/**
	 * 功能：查看所有权限值集合
	 * @return 权限值集合
	 */
	public List<String> getAllName() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String sql = "select name from tb_permission";
			@SuppressWarnings("unchecked")
			List<String> names = session.createSQLQuery(sql).list();
			transaction.commit();
			logger.info("成功查看所有权限值。");
			return names;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 功能：通过id查看一条权限
	 * @param id
	 * @return 一条权限
	 */
	public Perm getOneById(int id) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			perm = (Perm) session.get(Perm.class, id);
			transaction.commit();
			logger.info("成功查看ID为： " + id + " 的权限。");
			return perm;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 功能：通过name查看一条权限信息
	 * @param name
	 * @return 一条权限信息
	 */
	public Perm getOneByName(String name) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			perm = (Perm) session.createQuery("from Perm where name=? ")
					.setParameter(0, name).uniqueResult();
			transaction.commit();
			logger.info("成功查看所有权限。");
			return perm;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

}

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
	 * 功能：添加
	 * 
	 * @param uid
	 *            用户ID
	 * @param pid
	 *            权限ID
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
			logger.error("保存" + user + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 功能:通过用户ID删除 （将删除整个用户所有权限）
	 * 
	 * @param uid
	 *            用户ID
	 * @return true/false
	 */
	public boolean deleteByUId(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "delete from UserPermission where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			transaction.commit();
			logger.error("删除" + uid + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 功能：更新用户权限（将删除整个用户所有权限关系，然后插入新的权限关系）
	 * 
	 * @param uid
	 *            用户ID
	 * @param pid
	 *            权限ID
	 * @return true/false
	 */
	public boolean updatePermission(int uid, List<Integer> pid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();

		try {
			// 删除原有数据
			String hql = "delete from UserPermission where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			// 加入新增数据
			for (int i = 0; i < pid.size(); i++) {
				user = new UserPermission();
				user.setUid(uid);
				user.setPid(pid.get(i));
				session.save(user);
			}
			transaction.commit();
			logger.error("更新" + user + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 功能：查看所有用户权限列表
	 * 
	 * @return 权限列表
	 */
	public List<UserPermission> findAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			users = session.createQuery("from UserPermission").list();
			logger.info("查看所有权限成功");
			transaction.commit();
			return users;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 功能：通过用户ID查看用户权限
	 * 
	 * @param uid
	 *            用户ID
	 * @return 用户权限列表
	 */
	public List<UserPermission> findByUId(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "from UserPermission where uid =? ";
			users = session.createQuery(hql).setParameter(0, uid).list();
			logger.info("findByUId 查看" + uid + "的权限成功");
			transaction.commit();
			return users;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 功能：通过用户ID查看用户权限ID
	 * 
	 * @param uid
	 *            用户ID
	 * @return 权限ID集合
	 */
	public List<Integer> findUserPIds(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String sql = "select pid from UserPermission where uid = ?";
			List<Integer> pids = (List<Integer>) session.createQuery(sql)
					.setParameter(0, uid).list();
			transaction.commit();
			logger.info("findUserPIds 查看" + uid + "的权限成功");
			return pids;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

}

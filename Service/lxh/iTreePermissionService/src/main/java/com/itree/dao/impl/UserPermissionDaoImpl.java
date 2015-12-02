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
			.getLogger(UserPermissionDaoImpl.class);

	Session session;
	Transaction transaction;
	List<UserPermission> users;
	UserPermission user;

	public boolean add(int uid, List<Integer> pid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			for (int i = 0; i < pid.size(); i++) {
				/*user = new UserPermission();
				user.setUser(uid);
				user.setPerm(pid.get(i));
				session.save(user);*/
				
				String sql = "insert into tb_User_Permission(uid,pid)values(?,?)";
				session.createSQLQuery(sql).setParameter(0, uid).setParameter(1, pid.get(i)).executeUpdate();
				
			}
			transaction.commit();
			logger.info("保存" + user + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}


	public boolean deleteByUserID(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "delete from UserPermission where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			transaction.commit();
			logger.info("删除" + uid + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	
	public boolean update(int uid, List<Integer> pid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();

		try {
			// 删除原有数据
			String hql = "delete from UserPermission where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			// 加入新增数据
			for (int i = 0; i < pid.size(); i++) {
				/*user = new UserPermission();
				user.setUid(uid);
				user.setPerm(pid.get(i));
				session.save(user);*/
				String sql = "insert into tb_User_Permission(uid,pid)values(?,?)";
				session.createSQLQuery(sql).setParameter(0, uid).setParameter(1, pid.get(i)).executeUpdate();
				
			}
			transaction.commit();
			logger.info("更新" + user + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	
	/*public List<UserPermission> findAll() {
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
	}*/


	public List<UserPermission> findListByUserID(int uid) {
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


	public List<Integer> findUserPermissionID(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String sql = "select pid from tb_User_Permission where uid = ?";
			List<Integer> pids = (List<Integer>) session.createSQLQuery(sql)
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

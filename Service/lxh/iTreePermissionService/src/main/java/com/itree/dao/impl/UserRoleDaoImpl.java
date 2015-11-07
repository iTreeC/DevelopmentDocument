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
	 * 功能 ：添加
	 * 
	 * @param uid
	 *            用户ID
	 * @param rid
	 *            角色ID
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
			logger.info("成功保存ID为 ：" + uid + "的角色ID");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	/**
	 * 功能：通过用户ID删除（将删除整个用户所具有的角色关系）
	 * 
	 * @param uid
	 *            用户ID
	 * @return true/false
	 */
	public boolean deleteByUId(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "delete from UserRole where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			transaction.commit();
			logger.info("成功删除ID为：" + uid + " 的角色ID");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 功能：更新（将删除用户所有角色关系后，在插入新的角色关系）
	 * 
	 * @param uid
	 *            用户ID
	 * @param rid
	 *            角色ID
	 * @return true/false
	 */
	public boolean update(int uid, List<Integer> rid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			// 删除原有数据
			String hql = "delete from UserRole where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			// 加入新增数据
			for (int i = 0; i < rid.size(); i++) {
				userrole = new UserRole();
				userrole.setUid(uid);
				userrole.setRid(rid.get(i));
				session.save(userrole);
			}
			transaction.commit();
			logger.info("成功更新ID为 " + uid + "的角色ID ");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 功能：查看用户-角色列表
	 * 
	 * @return 用户-角色列表
	 */
	public List<UserRole> findAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			userroles = session.createQuery("from UserRole").list();
			logger.info("查看所有用户角色关系成功");
			transaction.commit();
			return userroles;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 功能：通过用户ID查看 用户-角色 列表
	 * 
	 * @param uid
	 *            用户ID
	 * @return 用户-角色列表
	 */
	public List<UserRole> findByUId(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "from UserRole where uid =? ";

			userroles = session.createQuery(hql).setParameter(0, uid).list();
			logger.info("成功查看所有 ID 为：" + uid + " 的角色ID");
			transaction.commit();
			return userroles;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 功能：通过用户ID查看用户角色ID集合
	 * 
	 * @param uid
	 *            用户ID
	 * @return 用户角色ID的集合
	 */
	public List<Integer> findUserRIds(int uid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "select rid from UserRole where uid = ?";
			List<Integer> rids = (List<Integer>) session.createQuery(hql)
					.setParameter(0, uid).list();
			transaction.commit();
			logger.info("成功查看ID 为：" + uid + "的角色ID");
			return rids;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

}

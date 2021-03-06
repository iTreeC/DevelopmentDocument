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

import com.itree.dao.api.RolePermissionDao;
import com.itree.entity.RolePermission;
import com.itree.utils.SessionUtils;

@Component
public class RolePermissionDaoImpl implements RolePermissionDao {

	private static Logger logger = Logger
			.getLogger(RolePermissionDaoImpl.class);

	Session session = SessionUtils.getInstance().getSession();
	Transaction transaction;
	RolePermission role;

	/**
	 * 功能：添加
	 * 
	 * @param rid
	 *            角色id
	 * @param pid
	 *            权限id
	 * @return true/false
	 */
	public boolean add(int rid, List<Integer> pid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			for (int i = 0; i < pid.size(); i++) {
				role = new RolePermission();
				role.setRid(rid);
				role.setPid(pid.get(i));
				session.save(role);
			}
			transaction.commit();
			logger.info("添加" + role + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	/**
	 * 功能：通过角色ID删除（将删除整个角色所有权限）
	 * 
	 * @param rid
	 *            角色id
	 * @return true/false
	 */
	public boolean deleteByRId(int rid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "delete from RolePermission where rid=?";
			session.createQuery(hql).setParameter(0, rid).executeUpdate();
			transaction.commit();
			logger.info("删除" + rid + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 功能：更新权限（先删掉旧的，再加入新的）
	 * 
	 * @param rid
	 *            角色ID
	 * @param pid
	 *            权限ID
	 * @return true/false
	 */
	public boolean updatePermission(int rid, List<Integer> pid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			// 删除原来数据
			String hql = "delete from RolePermission where rid=?";
			session.createQuery(hql).setParameter(0, rid).executeUpdate();

			// 插入新数据
			for (int i = 0; i < pid.size(); i++) {
				role = new RolePermission();
				role.setRid(rid);
				role.setPid(pid.get(i));
				session.save(role);
			}

			transaction.commit();
			logger.info("更新" + role + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 功能：查看所有角色-列表
	 * 
	 * @return 角色-权限列表
	 */
	public List<RolePermission> findAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			List<RolePermission> role = session.createQuery(
					"from RolePermission").list();
			transaction.commit();
			logger.info("查看所有用户权限成功");
			return role;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 功能：通过角色ID查看角色-权限列表
	 * 
	 * @param rid
	 *            角色ID
	 * @return 角色-权限列表
	 */
	public List<RolePermission> findByRId(int rid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "from RolePermission where rid =? ";
			List<RolePermission> role = session.createQuery(hql)
					.setParameter(0, rid).list();
			transaction.commit();
			logger.info("查看 " + rid + " 的权限成功");
			return role;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}

	}

	/**
	 * 功能：通过角色id查看权限id集合
	 * 
	 * @param rid
	 *            角色ID
	 * @return 权限id集合
	 */
	public List<Integer> findRolePIds(int rid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String sql = "select pid from RolePermission where rid = ?";
			List<Integer> pids = (List<Integer>) session.createQuery(sql)
					.setParameter(0, rid).list();
			transaction.commit();
			logger.info("findRolePIds 查看" + rid + "的权限成功");
			return pids;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}
}

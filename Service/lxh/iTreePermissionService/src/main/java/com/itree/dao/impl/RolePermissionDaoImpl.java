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

	public boolean add(int rid, List<Integer> pid) {
		if (rid == 0 || pid.size() == 0) {
			logger.error("参数错误");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			System.out.print(pid.size());
			for (int i = 0; i < pid.size(); i++) {
				String sql = "insert into tb_Role_Permission(rid,pid)values(?,?)";
				session.createSQLQuery(sql).setParameter(0, rid)
						.setParameter(1, pid.get(i)).executeUpdate();

			}
			transaction.commit();
			logger.info("添加" + rid + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean deleteByRoleID(int rid) {
		if (rid == 0) {
			logger.error("参数错误");
			return false;
		}
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

	public boolean update(int rid, List<Integer> pid) {
		if (rid == 0 || pid.size() == 0) {
			logger.error("参数错误");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			// 删除原来数据
			String hql = "delete from RolePermission where rid=?";
			session.createQuery(hql).setParameter(0, rid).executeUpdate();

			// 插入新数据
			for (int i = 0; i < pid.size(); i++) {
				String sql = "insert into tb_Role_Permission(rid,pid)values(?,?)";
				session.createSQLQuery(sql).setParameter(0, rid)
						.setParameter(1, pid.get(i)).executeUpdate();

			}
			transaction.commit();
			logger.info("更新" + rid + "的权限成功");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	public List<Integer> findPermissionIDByRoleID(int rid) {
		if (rid == 0) {
			logger.error("参数错误");
			return null;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();

		try {
			String sql = "select pid from tb_Role_Permission  where rid = ?";
			List<Integer> pids = (List<Integer>) session.createSQLQuery(sql)
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

	public List<RolePermission> findListByRoleID(int rid) {
		if (rid == 0) {
			logger.error("参数错误");
			return null;
		}
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

}

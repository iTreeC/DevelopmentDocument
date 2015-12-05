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
			logger.error("��������");
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
			logger.info("���" + rid + "��Ȩ�޳ɹ�");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean deleteByRoleID(int rid) {
		if (rid == 0) {
			logger.error("��������");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "delete from RolePermission where rid=?";
			session.createQuery(hql).setParameter(0, rid).executeUpdate();
			transaction.commit();
			logger.info("ɾ��" + rid + "��Ȩ�޳ɹ�");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean update(int rid, List<Integer> pid) {
		if (rid == 0 || pid.size() == 0) {
			logger.error("��������");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			// ɾ��ԭ������
			String hql = "delete from RolePermission where rid=?";
			session.createQuery(hql).setParameter(0, rid).executeUpdate();

			// ����������
			for (int i = 0; i < pid.size(); i++) {
				String sql = "insert into tb_Role_Permission(rid,pid)values(?,?)";
				session.createSQLQuery(sql).setParameter(0, rid)
						.setParameter(1, pid.get(i)).executeUpdate();

			}
			transaction.commit();
			logger.info("����" + rid + "��Ȩ�޳ɹ�");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	public List<Integer> findPermissionIDByRoleID(int rid) {
		if (rid == 0) {
			logger.error("��������");
			return null;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();

		try {
			String sql = "select pid from tb_Role_Permission  where rid = ?";
			List<Integer> pids = (List<Integer>) session.createSQLQuery(sql)
					.setParameter(0, rid).list();
			transaction.commit();
			logger.info("findRolePIds �鿴" + rid + "��Ȩ�޳ɹ�");
			return pids;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public List<RolePermission> findListByRoleID(int rid) {
		if (rid == 0) {
			logger.error("��������");
			return null;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String hql = "from RolePermission where rid =? ";
			List<RolePermission> role = session.createQuery(hql)
					.setParameter(0, rid).list();
			transaction.commit();
			logger.info("�鿴 " + rid + " ��Ȩ�޳ɹ�");
			return role;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}

	}

}

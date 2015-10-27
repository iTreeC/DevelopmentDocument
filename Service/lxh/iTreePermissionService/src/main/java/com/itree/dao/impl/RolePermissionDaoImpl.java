package com.itree.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itree.dao.api.RolePermissionDao;
import com.itree.entity.RolePermission;
import com.itree.utils.SessionUtils;

@Service
@Component("rpdao")
public class RolePermissionDaoImpl implements RolePermissionDao {

	private static Logger logger = Logger
			.getLogger(RolePermissionDaoImpl.class);

	Session session = SessionUtils.getInstance().getSession();
	Transaction transaction;
	RolePermission role;

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
			logger.info("���" + role + "��Ȩ�޳ɹ�");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean deleteByRId(int rid) {
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

	public boolean updatePermission(int rid, List<Integer> pid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			// ɾ��ԭ������
			String hql = "delete from RolePermission where rid=?";
			session.createQuery(hql).setParameter(0, rid).executeUpdate();

			// ����������
			for (int i = 0; i < pid.size(); i++) {
				role = new RolePermission();
				role.setRid(rid);
				role.setPid(pid.get(i));
				session.save(role);
			}

			transaction.commit();
			logger.info("����" + role + "��Ȩ�޳ɹ�");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	public List<RolePermission> findAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			List<RolePermission> role = session.createQuery(
					"from RolePermission").list();
			transaction.commit();
			logger.info("�鿴�����û�Ȩ�޳ɹ�");
			return role;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public List<RolePermission> findByRId(int rid) {
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

	public List<Integer> findRolePIds(int rid) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			String sql = "select pid from RolePermission where rid = ?";
			List<Integer> pids = (List<Integer>) session.createQuery(sql)
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
}

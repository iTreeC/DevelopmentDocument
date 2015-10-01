package com.itree.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itree.dao.RoleDao;
import com.itree.entity.Role;
import com.itree.utils.SessionUtils;

@Service
@Component("roledao")
public class RoleDaoImpl implements RoleDao {

	private static Logger logger = Logger.getLogger(RoleDaoImpl.class);

	Session session;
	Transaction transaction;
	Role role;

	public void add(int rid, List<Integer> pid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			for (int i = 0; i < pid.size(); i++) {
				role = new Role();
				role.setRid(rid);
				role.setPid(pid.get(i));
				session.save(role);
			}
			transaction.commit();
			logger.info("����" + role + "��Ȩ�޳ɹ�");
		} catch (Exception e) {
			logger.error("����" + role + "��Ȩ��ʧ��");
		}

	}

	public void deleteByRId(int rid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "delete from Role where rid=?";
			session.createQuery(hql).setParameter(0, rid).executeUpdate();
			transaction.commit();
			logger.info("ɾ��" + rid + "��Ȩ�޳ɹ�");
		} catch (Exception e) {
			logger.error("ɾ��" + rid + "��Ȩ��ʧ��");
		}
	}

	public void updatePermission(int rid, List<Integer> pid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			this.deleteByRId(rid);
			for (int i = 0; i < pid.size(); i++) {
				role = new Role();
				role.setRid(rid);
				role.setPid(pid.get(i));
				session.save(role);
			}
			transaction.commit();
			logger.info("����" + role + "��Ȩ��ʧ��");
		} catch (Exception e) {
			logger.error("����" + role + "��Ȩ��ʧ��");
		}
	}

	public List<Role> findAll() {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			List<Role> role = session.createQuery("from Role").list();
			transaction.commit();
			logger.info("�鿴�����û�Ȩ�޳ɹ�");
			return role;
		} catch (Exception e) {
			logger.error("�鿴�����û�Ȩ��ʧ��");
			return null;
		}
	}
	
	public List<Role> findByRId(int rid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "from Role where rid =? ";
			List<Role> role = session.createQuery(hql).setParameter(0, rid)
					.list();
			transaction.commit();
			logger.info("�鿴" + rid + "��Ȩ�޳ɹ�");
			return role;
		} catch (Exception e) {
			logger.error("�鿴" + rid + "��Ȩ��ʧ��");
			return null;
		}

	}
	
	public List<Integer> findRolePIds(int rid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String sql = "select pid from Role where rid = ?";
			List<Integer> pids = (List<Integer>) session.createQuery(sql).setParameter(0, rid)
					.list();
			transaction.commit();
			logger.info("findRolePIds �鿴" + rid + "��Ȩ�޳ɹ�");
			return pids;
		} catch (Exception e) {
			e.getMessage();
			logger.error("findRolePIds �鿴" + rid + "��Ȩ��ʧ��");
			return null;
		}
	}
}

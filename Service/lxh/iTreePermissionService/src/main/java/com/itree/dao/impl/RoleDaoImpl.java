/**
 * @info 
 * @author ������
 * @time  2015.11.09
 */
package com.itree.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.itree.dao.api.RoleDao;
import com.itree.entity.Role;
import com.itree.utils.SessionUtils;

@Component
public class RoleDaoImpl implements RoleDao {

	private static Logger logger = Logger.getLogger(RoleDaoImpl.class);

	Session session = SessionUtils.getInstance().getSession();
	Transaction transaction = null;

	List<Role> roles = new ArrayList<Role>();
	Role role;
	String hql;

	public boolean add(String name) {
		if (name == null) {
			logger.error("��������");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			role = new Role();
			role.setName(name);
			session.save(role);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean deleteByID(int id) {

		if (id == 0) {
			logger.error("��������");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			hql = "delete from RolePermission where rid=?";
			session.createQuery(hql).setParameter(0, id).executeUpdate();

			hql = "delete from UserRole where rid=?";
			session.createQuery(hql).setParameter(0, id).executeUpdate();

			hql = "delete from Role where id=?";
			session.createQuery(hql).setParameter(0, id).executeUpdate();

			transaction.commit();
			logger.info("�ɹ�ɾ��IDΪ" + id + " ��Ȩ��");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean update(Role role) {

		if (role == null) {
			logger.error("��������");
			return false;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			session.update(role);
			transaction.commit();
			logger.info("�ɹ�����IDΪ��" + role.getId() + " ��Ȩ��");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return false;
		}

	}

	public List<Role> getAll() {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			roles = session.createQuery("from Role ").list();

			transaction.commit();
			logger.info("�ɹ��鿴����Ȩ���б���");
			return roles;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public List<Role> getRoleByID(List<Integer> id) {
		if (id.size() == 0) {
			logger.error("��������");
			return null;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {

			for (int i = 0; i < id.size(); i++) {
				role = (Role) session.get(Role.class, id.get(i));
				if (role != null)
					roles.add(role);
			}
			transaction.commit();
			logger.info("�ɹ��鿴IDΪ�� " + id + " ��Ȩ�ޡ�");
			return roles;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public Role getOneByID(int id) {
		if (id == 0) {
			logger.error("��������");
			return null;
		}
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			role = (Role) session.get(Role.class, id);
			transaction.commit();
			logger.info("�ɹ��鿴IDΪ�� " + id + " ��Ȩ�ޡ�");
			return role;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

	public Role getOneByName(String name) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		try {
			role = (Role) session.createQuery("from Role where name=? ")
					.setParameter(0, name).uniqueResult();
			transaction.commit();
			logger.info("�ɹ��鿴����Ȩ�ޡ�");
			return role;
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage());
			return null;
		}
	}

}
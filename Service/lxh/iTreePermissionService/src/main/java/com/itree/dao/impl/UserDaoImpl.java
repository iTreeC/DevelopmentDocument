package com.itree.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itree.dao.UserDao;
import com.itree.entity.User;
import com.itree.utils.SessionUtils;

@Service
@Component("userdao")
public class UserDaoImpl implements UserDao {

	private static Logger logger = Logger.getLogger(RoleDaoImpl.class);

	Session session ;
	Transaction transaction ;
	List<User> users;
	User user;

	public void add(int uid,List<Integer> pid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			this.deleteByUId(uid);
			for (int i = 0; i < pid.size(); i++) {
				user = new User();
				user.setUid(uid);
				user.setPid(pid.get(i));
				session.save(user);
			}
			transaction.commit();
			logger.error("����" + user + "��Ȩ�޳ɹ�");
		} catch (Exception e) {
			logger.error("����" + user + "��Ȩ��ʧ��");
		}
	}

	public void deleteByUId(int uid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "delete from User where uid=?";
			session.createQuery(hql).setParameter(0, uid).executeUpdate();
			transaction.commit();
			logger.error("ɾ��" + uid + "��Ȩ�޳ɹ�");
		} catch (Exception e) {
			logger.error("ɾ��" + uid + "��Ȩ��ʧ��");
		}
	}

	public void updatePermission(int uid,List<Integer> pid) {
		
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			this.deleteByUId(uid);
			for (int i = 0; i < pid.size(); i++) {
				user = new User();
				user.setUid(uid);
				user.setPid(pid.get(i));
				session.save(user);
			}
			transaction.commit();
			logger.error("����" + user + "��Ȩ�޳ɹ�");
		} catch (Exception e) {
			logger.error("����" + user + "��Ȩ��ʧ��");
		}
	}

	public List<User> findAll() {
		try {
			session = SessionUtils.getInstance().getSession();
			users = session.createQuery("from User").list();
			logger.info("�鿴����Ȩ�޳ɹ�");
			return users;
		} catch (Exception e) {
			logger.error("�鿴����Ȩ��ʧ��");
			return null;
		}
	}

	public List<User> findByUId(int uid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
	    	String hql = "from User where uid =? ";
			users = session.createQuery(hql).setParameter(0, uid).list();
			logger.info("findByUId �鿴" + uid + "��Ȩ�޳ɹ�");
			transaction.commit();
			return users;
		} catch (Exception e) {
			e.getMessage();
			logger.error("findByUId �鿴" + uid + "��Ȩ��ʧ��");
			return null;
		}
	}

	public List<Integer> findUserPIds(int uid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String sql = "select pid from User where uid = ?";
			List<Integer> pids = (List<Integer>) session.createQuery(sql).setParameter(0, uid)
					.list();
			transaction.commit();
			logger.info("findUserPIds �鿴" + uid + "��Ȩ�޳ɹ�");
			return pids;
		} catch (Exception e) {
			e.getMessage();
			logger.error("findUserPIds �鿴" + uid + "��Ȩ��ʧ��");
			return null;
		}
	}

}

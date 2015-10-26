package com.itree.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itree.dao.UserDao;
import com.itree.entity.User;
import com.itree.utils.SessionUtils;
public class UserDaoImpl implements UserDao {

	Session session;
	Transaction transaction;

	public List<User> findAll() {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			List<User> users = session.createQuery("from Role").list();
			transaction.commit();

			return users;
		} catch (Exception e) {

			return null;
		}

	}
}

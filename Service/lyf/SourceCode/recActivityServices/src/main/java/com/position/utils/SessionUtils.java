package com.position.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * session工具类
 * 
 */
@Repository
public class SessionUtils {
	@Autowired
	private SessionFactory sessionFactory;

	/*public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}

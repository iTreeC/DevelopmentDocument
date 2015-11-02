/**
 * @info hibernate µÄ session
 * @author ÀîÏþ»¶
 * @time 2015.10.28
 */

package com.itree.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionUtils {
	
	private SessionUtils(){
		
	}
	private static SessionUtils instance =new SessionUtils();
	
	public static SessionUtils getInstance(){
		return instance;
	}
	

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		
		if(sessionFactory == null){
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
							.applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		
		return sessionFactory;
		
	}
	
	public Session getSession(){
		return getSessionFactory().getCurrentSession();
		
	}
}

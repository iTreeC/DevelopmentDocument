package com.itree.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import com.itree.dao.NewsDao;
import com.itree.entity.PageModel;
import com.itree.entity.TNews;
import com.itree.utils.SessionUtils;


public class NewsDaoimpl extends BaseDaoimpl implements NewsDao {
	Session session;
	Transaction transaction;
	
	
     public List<TNews> getAll() {
    	 System.out.println("1");
    	 try{
    	 session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			List<TNews> news = session.createQuery("from TNews").list();
			transaction.commit();
			System.out.println(news);
			return news;		
    	 }catch (Exception e){
    		 e.printStackTrace(); 
    		 System.out.println(e.getMessage()); 
    		 return null;
    		 }
    	 }
    public void  saveOrUpdate(TNews news){
    	session =SessionUtils.getInstance().getSession();
    	transaction = session.beginTransaction();
		 transaction.commit();
    	session.saveOrUpdate(news);
    }
	public TNews get(Integer id) {
		
		 session = SessionUtils.getInstance().getSession();
		 transaction = session.beginTransaction();
		 transaction.commit();
		  return (TNews) session.get(TNews.class, id);
	}

	/*public void saveOrUpdate(TNews news) {
		try{
		session =SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		session.saveOrUpdate(news);
		transaction.commit();
		System.out.println(news);
		}catch (Exception e){
   		 e.printStackTrace(); 
   		 System.out.println(e.getMessage()); 
   		
   		 }
   	 }*/
		
	}

		
	
	
	

	



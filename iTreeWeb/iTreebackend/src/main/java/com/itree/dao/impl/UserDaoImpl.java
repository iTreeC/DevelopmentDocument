package com.itree.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itree.dao.UserDao;
import com.itree.entity.TLogin;
import com.itree.utils.SessionUtils;
public class UserDaoimpl implements UserDao {

	Session session;
	Transaction transaction;

	public List<TLogin> getAll(){
		System.out.println("UserDaoimpl的getAll（）方法"); 
		try{
	    	 session = SessionUtils.getInstance().getSession();
				transaction = session.beginTransaction();
				List<TLogin> user = session.createQuery("from TLogin").list();
				transaction.commit();
				System.out.println(user);
				return user;		
	    	 
		 }catch (Exception e){
	    		 e.printStackTrace(); 
	    		 System.out.println(e.getMessage()); 
	    		 return null;
	    		 }
	    	 }
	}
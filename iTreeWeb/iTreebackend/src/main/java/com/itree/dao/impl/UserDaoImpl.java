package com.itree.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itree.dao.UserDao;
import com.itree.entity.TLogin;
import com.itree.utils.SessionUtils;
public class UserDaoimpl implements UserDao {

	Session session;
	Transaction transaction;
	
	//获取用户列表
	@SuppressWarnings("unchecked")
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
	//根据ID，删除用户信息。
	public void delete(int id){
		try{
			System.out.println("UserDaoImpl的delete方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			TLogin login = (TLogin) session.load(TLogin.class, id);
			/*TUser user = (TUser)session.load(TUser.class, id);*/
			session.delete(login);
			transaction.commit();
			
		}catch(Exception e){
			e.printStackTrace(); 
   		 	System.out.println(e.getMessage()); 
   		 	return;
		}
	}
	public void stop(int id){
		try{
			System.out.println("UserDaoImpl的stop方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			TLogin login = (TLogin)session.get(TLogin.class, id);
			Query query = session.createQuery("update TUser u set u.userStatus = 0 where id ='"+login.getId()+"'");
			query.executeUpdate();
			transaction.commit();
			
		}catch(Exception e){
			e.printStackTrace(); 
   		 	System.out.println(e.getMessage()); 
   		 	return;
		}
	}
	public void start(int id){
		try{
			System.out.println("UserDaoImpl的stop方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			TLogin login = (TLogin)session.get(TLogin.class, id);
			Query query = session.createQuery("update TUser u set u.userStatus = 1 where id ='"+login.getId()+"'");
			query.executeUpdate();
			transaction.commit();
			
		}catch(Exception e){
			e.printStackTrace(); 
   		 	System.out.println(e.getMessage()); 
   		 	return;
		}
	}
	}
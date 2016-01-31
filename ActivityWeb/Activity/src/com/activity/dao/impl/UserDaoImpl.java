package com.activity.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.activity.actions.MD5;
import com.activity.dao.UserDao;
import com.activity.model.User;

@Component("userDao")
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;
	private Session session=null;
	
	MD5 md5 = new MD5();
	/**
	 * 注册用户方法，这里面对用户是否注册进行了判断
	 * @return 返回false，说明未能注册成功，返回true，说明注册成功
	 */
	@Override
	public Boolean addUser(User user) {
		// TODO Auto-generated method stub
		Boolean adduserreturn = null;
		if (logined(user)==true) {
			System.out.println("用户已注册，不能进行二次注册");
			adduserreturn=false;
		}else{
			System.out.println("用户未注册，可以注册");
			sessionFactory.openSession().save(user);
			adduserreturn = true;
		}
		return adduserreturn;
				
	}
	/**
	 * 功能： 检测用户是否已注册
	 * @param user
	 * @return 返回false时，说明为次手机号未注册，返回true时，说明此手机号已注册
	 */
	public Boolean logined(User user){
		Boolean logined = null;
		try {
			session = sessionFactory.getCurrentSession();
			String hql = "select u from User u where u.name like ?";
			Query query =session.createQuery(hql);
			query.setParameter(0, user.getName());
			User user2 = (User)query.uniqueResult();
			
			//查询结果不为空，说明此手机号已注册
			if(user2.equals(null)==false){
				System.out.println("用户已注册");
				logined=true;
			}
		} catch (Exception e) {
			//当抛错时，即未查到此手机号，说明未注册
			System.out.println("用户未注册");
			logined=false;
		}
		return logined; 
	}
	
	/**
	 * 用户登录方法
	 * @return 返回false说明登录失败，返回true说明登陆成功
	 */
	@Override
	public Boolean userLogin(User user) {
		// TODO Auto-generated method stub
		Boolean userloginreturn = null;
		if(logined(user)==false){
			System.out.println("用户未注册");
			userloginreturn = false;
		}else{
			System.out.println("用户已注册");
			//查询用户的密码 
			session = sessionFactory.getCurrentSession();
			String hql = "select u from User u where u.name like ?";
			Query query =session.createQuery(hql);
			query.setParameter(0, user.getName());
			User user2 = (User)query.uniqueResult();
			
			if(user2.getPasswd().equals(user.getPasswd())){
				System.out.println("密码输入正确");
				userloginreturn = true;
			}else {
				System.out.println("密码输入不正确");
				userloginreturn = false;
			}
		}
		return userloginreturn;
	}
	/**
	 * 功能：在登录以后进行更改密码
	 * @param 将当前登录中的用户名，与密码 ，已经输入的要更改的密码
	 * @return 当更改成功的时候返回true ，更改错误返回false
	 */
	@Override
	public Boolean updatepwd(User user,String updatepwd) {
		// TODO Auto-generated method stub
		Boolean updatemsg = null;
		//查询用户的密码 
		session = sessionFactory.getCurrentSession();
		String hql = "select u from User u where u.name like ?";
		Query query =session.createQuery(hql);
		query.setParameter(0, user.getName());
		User user2 = (User)query.uniqueResult();
		if(user2.getPasswd().equals(user.getPasswd())){
			System.out.println("密码输入正确");
			//进行密码更改
			user2.setPasswd(updatepwd);
			session.save(user2);
			System.out.println("更改密码成功");
			updatemsg = true;
		}else {
			System.out.println("密码输入不正确");
			updatemsg = false;
		}
		return updatemsg;
	}

}

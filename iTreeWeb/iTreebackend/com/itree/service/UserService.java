package com.itree.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.dao.UserDao;
import com.itree.entity.User;

public class UserService {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	UserDao userdao = (UserDao) ctx.getBean("userdao");
	
	public List<User> List(){
		
		List<User> users= userdao.findAll();
		System.out.println(users);
		return users;
	}
}

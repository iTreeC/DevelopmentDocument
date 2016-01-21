package com.itree.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.dao.UserDao;
import com.itree.entity.User;
import com.itree.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	UserService uservice  = new UserService();
	
	
	public String List(){
		
		List<User> users= uservice.List();
		return SUCCESS;
	}
}

package com.activity.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.activity.dao.UserDao;
import com.activity.model.User;
import com.activity.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	//注入式
	@Resource
	private UserDao userDao;
	
	

	@Override
	public Boolean addUser(User user) {
		// TODO Auto-generated method stub
		Boolean adduser = null;
		adduser =userDao.addUser(user);
		return adduser;
	}



	@Override
	public Boolean userLogin(User user) {
		// TODO Auto-generated method stub
		Boolean userlogin = null;
		userlogin = userDao.userLogin(user);
		return userlogin;
	}



	@Override
	public Boolean logined(User user) {
		// TODO Auto-generated method stub
		Boolean logined = null;
		logined = userDao.logined(user);
		return logined;
	}


	/**
	 * 登陆后的密码更改
	 */
	@Override
	public Boolean updatepwd(User user, String updatepwd) {
		// TODO Auto-generated method stub
		Boolean updatemsg = null;
		updatemsg = userDao.updatepwd(user, updatepwd);
		return updatemsg;
	}

}

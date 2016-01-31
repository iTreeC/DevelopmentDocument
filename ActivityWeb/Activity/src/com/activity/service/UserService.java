package com.activity.service;

import com.activity.model.User;

public interface UserService {
	//验证用户是否已注册
	public Boolean logined(User user);
	//用户注册功能
	public Boolean addUser(User user);
	//用户登录
	public Boolean userLogin(User user);
	//登陆后的密码更改
	public Boolean updatepwd(User user,String updatepwd);

}

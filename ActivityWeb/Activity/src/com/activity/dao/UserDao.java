package com.activity.dao;

import com.activity.model.User;



/**
 * 功能：数据操作接口类
 * @author 马大洲 
 * @time 2015/12/21
 *
 */
public interface UserDao {
	//查询用户是否注册
	public Boolean logined(User user);
	//增加用户信息（手机号，加密后的密码）
	public Boolean addUser(User user);
	//登录方法
	public Boolean userLogin(User user);
	//当登录后进行更改密码
	public Boolean updatepwd(User user,String updatepwd);
}

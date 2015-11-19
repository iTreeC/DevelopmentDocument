package com.itree.service;

import java.util.List;

import com.itree.entity.AddUserInfo;
import com.itree.entity.TLogin;


public interface UserService{
	public List<TLogin> getAll();//获取用户列表
	public void delete(int d);//根据id删除用户
	public void stop(int id);//更改用户标识，停用
	public void start(int id);//更改用户标识，启用
	public void save(AddUserInfo aui);//增加用户
	public TLogin get(int id);//根据id返回用户实体
	public void doUpdate(AddUserInfo aui,int id);//更新用户信息
}
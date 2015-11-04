package com.itree.service;

import java.util.List;

import com.itree.entity.TLogin;


public interface UserService{
	public List<TLogin> getAll();//获取用户列表
	public void delete(int d);//根据id删除用户
	public void stop(int id);//更改用户标识，停用
	public void start(int id);//更改用户标识，启用
}
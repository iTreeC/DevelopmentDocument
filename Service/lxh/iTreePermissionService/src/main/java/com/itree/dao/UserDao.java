package com.itree.dao;

import java.util.List;

import com.itree.entity.User;

public interface UserDao {

	public void add(int uid,List<Integer> pid);
	public void deleteByUId(int uid);
	public void updatePermission(int uid,List<Integer> pid);
	public List<User> findAll();
	public List<User> findByUId(int uid);
	public List<Integer> findUserPIds(int uid);
}

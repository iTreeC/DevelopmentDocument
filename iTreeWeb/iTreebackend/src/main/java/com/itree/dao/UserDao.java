package com.itree.dao;

import java.util.List;

import com.itree.entity.AddUserInfo;
import com.itree.entity.TLogin;


public interface UserDao {
	public List<TLogin> getAll();
	public void delete(int id);
	public void stop(int id);
	public void start(int id);
	public void save(AddUserInfo aui);
	public TLogin get(int id);
	public void doUpdate(AddUserInfo aui,int id);
}

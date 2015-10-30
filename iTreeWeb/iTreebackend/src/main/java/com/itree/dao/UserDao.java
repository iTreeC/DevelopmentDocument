package com.itree.dao;

import java.util.List;

import com.itree.entity.User;

public interface UserDao {
	public List<User> findAll();
}

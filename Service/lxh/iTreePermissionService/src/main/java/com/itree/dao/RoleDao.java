package com.itree.dao;

import java.util.List;

import com.itree.entity.Role;

public interface RoleDao {

	public void add(int rid,List<Integer> pid);
	public void deleteByRId(int rid);
	public void updatePermission(int rid,List<Integer> pid);
	public List<Role> findAll();
	public List<Role> findByRId(int rid);
	public List<Integer> findRolePIds(int rid);
}

package com.itree.dao.api;

import java.util.List;

import com.itree.entity.UserRole;

public interface UserRoleDao {

	public boolean add(int uid, List<Integer> rid);

	public boolean deleteByUId(int uid);

	public boolean update(int uid, List<Integer> rid);

	public List<UserRole> findAll();

	public List<UserRole> findByUId(int uid);

	public List<Integer> findUserRIds(int uid);
}

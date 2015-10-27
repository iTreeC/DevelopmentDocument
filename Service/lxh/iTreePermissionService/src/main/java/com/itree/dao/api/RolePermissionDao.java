package com.itree.dao.api;

import java.util.List;

import com.itree.entity.RolePermission;

public interface RolePermissionDao {

	public boolean add(int rid, List<Integer> pid);

	public boolean deleteByRId(int rid);

	public boolean updatePermission(int rid, List<Integer> pid);

	public List<RolePermission> findAll();

	public List<RolePermission> findByRId(int rid);

	public List<Integer> findRolePIds(int rid);
}

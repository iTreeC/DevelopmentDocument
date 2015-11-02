/**
 * @info 
 * @author ÀîÏþ»¶
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.Perm;

public interface PermissionDao {

	public boolean add(Perm perm);

	public boolean deleteByRId(int id);

	public boolean update(Perm permission);

	public List<Perm> getAll();

	public List<String> getAllName();

	public Perm getOneById(int id);
/*
	public String getNameByID(int id);*/

	public Perm getOneByName(String name);

}

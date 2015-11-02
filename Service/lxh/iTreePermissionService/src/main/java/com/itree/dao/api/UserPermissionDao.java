/**
 * @info 
 * @author ÀîÏş»¶
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.UserPermission;

public interface UserPermissionDao {

	public boolean add(int uid, List<Integer> pid);

	public boolean deleteByUId(int uid);

	public boolean updatePermission(int uid, List<Integer> pid);

	public List<UserPermission> findAll();

	public List<UserPermission> findByUId(int uid);

	public List<Integer> findUserPIds(int uid);
}

package com.itree.engine.api;

import java.util.List;

import com.itree.entity.UserPermission;

public interface UsersPermissionEngineAPI extends Engine {
	

	public Boolean update(int id, List<Integer> id2);

	public List<UserPermission> getPermission(int uid);

	public List<Integer> getPermissionID(int uid);

	public Boolean cando(int id, int id2);

}

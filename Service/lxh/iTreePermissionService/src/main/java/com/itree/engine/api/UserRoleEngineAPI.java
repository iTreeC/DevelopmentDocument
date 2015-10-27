package com.itree.engine.api;

import java.util.List;

import com.itree.entity.UserRole;

public interface UserRoleEngineAPI extends Engine {

	public Boolean update(int id, List<Integer> id2);

	public List<UserRole> getRoleByUser(int uid);

	public List<Integer> getRIds(int id);

	public Boolean cando(int id, int id2);
}

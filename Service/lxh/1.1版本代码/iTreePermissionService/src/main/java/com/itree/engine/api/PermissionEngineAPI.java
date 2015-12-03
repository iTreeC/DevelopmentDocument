/**
 * @info 
 * @author ÀîÏş»¶
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

import com.itree.entity.Perm;

public interface PermissionEngineAPI {
	public Boolean add(String name);

	public Boolean delete(int id);

	public Boolean update(Perm permission);

	public List<Perm> getAll();

	public List<String> getAllName();

	public Perm getOneByID(int id);

	public String getNameByID(int id);
}

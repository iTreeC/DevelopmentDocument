/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.api;

public interface PermissionEngineAPI extends Engine {

	/**
	 * 功能：增加权限
	 * 
	 * @param cid
	 *            ClientUserID
	 * @return true/false
	 */
	public Boolean add(int cid);

	/**
	 * 功能：删除权限
	 * 
	 * @param cid
	 *            ClientUserID
	 * @return true/false
	 */
	public Boolean delete(int cid);

	/*
	 * public Boolean update(Perm permission);
	 * 
	 * public List<Perm> getAll();
	 * 
	 * public List<String> getAllName();
	 * 
	 * public Perm getOneByID(int id);
	 * 
	 * public int getClientIDByID(int id);
	 */
}

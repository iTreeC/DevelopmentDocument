/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.api;

public interface UserEngineAPI extends Engine {

	/**
	 * 功能：增加用户
	 * 
	 * @param cid
	 *            ClientUserID
	 * @return true/false
	 */
	public Boolean add(int cid);

	/**
	 * 功能：删除用户
	 * 
	 * @param cid
	 *            ClientUserID
	 * @return true/false
	 */
	public Boolean delete(int cid);

	/*
	 * public Boolean update(User user);
	 * 
	 * public List<User> getAll();
	 * 
	 * public List<String> getAllName();
	 * 
	 * public User getOneByClientUserID(int cid);
	 * 
	 * public int getClientIDByID(int id);
	 */
}

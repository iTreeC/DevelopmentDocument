/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.api;

public interface UserEngineAPI extends Engine {

	/**
	 * ���ܣ������û�
	 * 
	 * @param cid
	 *            ClientUserID
	 * @return true/false
	 */
	public Boolean add(int cid);

	/**
	 * ���ܣ�ɾ���û�
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

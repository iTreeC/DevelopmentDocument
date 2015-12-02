/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface UserPermissionEngineAPI extends Engine {

	/**
	 * ���ܣ������û�Ȩ��
	 * 
	 * @param uid
	 *            User ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean add(int uid, List<Integer> pid);

	/**
	 * ���ܣ�ɾ���û�Ȩ��
	 * 
	 * @param uid
	 *            User ID
	 * @return true/false
	 */
	public Boolean delete(int uid);

	/**
	 * ���ܣ������û�Ȩ��
	 * 
	 * @param uid
	 *            User ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean update(int uid, List<Integer> pid);

	/**
	 * ���ܣ�ͨ��UserID�õ�PermissionID
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer>
	 */
	public List<Integer> getPermissionIDByUserID(int uid);

	/**
	 * ���ܣ��û�-Ȩ��ƥ��
	 * 
	 * @param uid
	 *            User ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean cando(int uid, int pid);
	/* public List<UserPermission> getPermission(int uid); */

}

/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface UserRoleEngineAPI extends Engine {
	/**
	 * ���ܣ������û���ɫ
	 * 
	 * @param uid
	 *            User ID
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public Boolean add(int uid, List<Integer> rid);

	/**
	 * ���ܣ�ɾ���û���ɫ
	 * 
	 * @param uid
	 *            User ID
	 * @return true/false
	 */
	public Boolean delete(int uid);

	/**
	 * ���ܣ������û���ɫ
	 * 
	 * @param uid
	 *            User ID
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public Boolean update(int uid, List<Integer> rid);

	/**
	 * ���ܣ�ͨ��UserID�鿴�û���ɫID
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer>
	 */
	public List<Integer> getRIds(int uid);

	/**
	 * ���ܣ��û�-��ɫƥ��
	 * 
	 * @param uid
	 *            User ID
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public Boolean cando(int uid, int rid);

	/**
	 * ���ܣ�ͨ��UserID�鿴�û���ɫ
	 * 
	 * @param uid
	 * @return
	 */
	/* public List<UserRole> getRoleByUserID(int uid); */
}

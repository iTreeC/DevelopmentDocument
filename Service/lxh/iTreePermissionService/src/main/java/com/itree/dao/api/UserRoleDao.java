/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.UserRole;

public interface UserRoleDao {

	/**
	 * ���ܣ������û���ɫ
	 * 
	 * @param uid
	 *            �û�ID
	 * @param rid
	 *            ��ɫID
	 * @return true/false
	 */
	public boolean add(int uid, List<Integer> rid);

	/**
	 * ���ܣ�ͨ���û�IDɾ���û���ɫ
	 * 
	 * @param uid
	 *            ��ɫID
	 * @return true/false
	 */
	public boolean deleteByUserID(int uid);

	/**
	 * ���ܣ������û���ɫ
	 * 
	 * @param uid
	 *            �û�ID
	 * @param rid
	 *            ��ɫID
	 * @return true/false
	 */
	public boolean update(int uid, List<Integer> rid);

	/**
	 * ���ܣ�ͨ���û�ID�鿴�û���ɫ
	 * 
	 * @param uid
	 *            �û�ID
	 * @return List<UserRole>
	 */
	public List<UserRole> findListByUserID(int uid);

	/**
	 * ���ܣ�ͨ���û�ID�鿴�û���ɫID
	 * 
	 * @param uid
	 *            �û�ID
	 * @return List<Integer>
	 */
	public List<Integer> findRoleIDByUserID(int uid);

	/**
	 * ���ܣ�
	 * 
	 * @return
	 */
	/* public List<UserRole> findAll(); */
}

/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.UserPermission;

public interface UserPermissionDao {

	/**
	 * ���ܣ������û�Ȩ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @param pid
	 *            Ȩ��ID
	 * @return true/false
	 */
	public boolean add(int uid, List<Integer> pid);

	/**
	 * ���ܣ�ͨ���û�IDɾ���û�Ȩ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @return true/false
	 */
	public boolean deleteByUserID(int uid);

	/**
	 * ���ܣ������û�Ȩ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @param pid
	 *            Ȩ��ID
	 * @return true/false
	 */
	public boolean update(int uid, List<Integer> pid);

	/**
	 * ���ܣ�ͨ��ID�鿴�û�Ȩ��
	 * 
	 * @param uid
	 *            �û�ID
	 * @return List<UserPermission>
	 */
	public List<UserPermission> findListByUserID(int uid);

	/**
	 * ���ܣ�ͨ��ID�鿴�û�Ȩ��
	 * 
	 * @param uid
	 * @return List<Integer>
	 */
	public List<Integer> findUserPermissionID(int uid);
}

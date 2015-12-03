/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

import com.itree.entity.Role;

public interface Engine {
	
	/**
	 * ���ܣ����ӽ�ɫ����Ȩ
	 * 
	 * @param name
	 *            Role name
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean addRoleAndPermission(String name, List<Integer> pid);
	/**
	 * ���ܣ��鿴ĳ��ɫ��Ȩ��ID
	 * 
	 * @param rid
	 *            Role ID
	 * @return List<Integer>
	 */
	public List<Integer> getRolePermissionID(int rid);

	/**
	 * ���ܣ��鿴ĳ�û���ɫ��Ϣ
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Role>
	 */
	public List<Role> getUserRole(int uid);

	/**
	 * �鿴�û��Ľ�ɫ��Ȩ��ID������role��
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer>
	 */
	public List<Integer> getUserRolePermissionID(int uid);

	/**
	 * ���ܣ��鿴�û���Ȩ��ID��������role��
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer>
	 */
	public List<Integer> getUserPermissionID(int uid);

	/**
	 * ���ܣ�ȥ���ظ�����
	 * 
	 * @param ids
	 *            ��ȥ�ص�����List<Integer>
	 * @return List<Integer>
	 */
	public List<Integer> dereplication(List<Integer> ids);
	/* public Boolean upcando(int uid, int pid); */
}

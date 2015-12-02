/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.RolePermission;

public interface RolePermissionDao {

	/**
	 * ���ܣ�����
	 * 
	 * @param rid
	 *            Role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public boolean add(int rid, List<Integer> pid);

	/**
	 * ���ܣ�ͨ��IDɾ��
	 * 
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public boolean deleteByRoleID(int rid);

	/**
	 * ���ܣ�����Ȩ��
	 * 
	 * @param rid
	 *            Role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public boolean update(int rid, List<Integer> pid);

	/**
	 * ���ܣ�ͨ��ID�鿴��ɫ-Ȩ�޹�ϵ����
	 * 
	 * @param rid
	 *            Role ID
	 * @return List<RolePermission>
	 */
	public List<RolePermission> findListByRoleID(int rid);

	/**
	 * ���ܣ� ͨ��ID�鿴��Ӧ��ɫ��Ȩ��ID
	 * 
	 * @param rid
	 * @return List<Integer>
	 */
	public List<Integer> findPermissionIDByRoleID(int rid);

	/**
	 * ���ܣ��鿴����
	 * 
	 * @return List<RolePermission>
	 */
	/* public List<RolePermission> findAll(); */
}

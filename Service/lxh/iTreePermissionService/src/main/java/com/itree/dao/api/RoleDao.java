/**
 * @info 
 * @author ������
 * @time  2015.11.09
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.Role;

public interface RoleDao {

	/**
	 * ���ܣ����ӽ�ɫ
	 * 
	 * @param role
	 * @return
	 */
	public boolean add(Role role);

	/**
	 * ���ܣ�ͨ��IDɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteByID(int id);

	/**
	 * ���ܣ�����
	 * 
	 * @param role
	 * @return
	 */
	public boolean update(Role role);

	/**
	 * ���� ���鿴���н�ɫ
	 * 
	 * @return
	 */
	public List<Role> getAll();

	/**
	 * ���ܣ�ͨ��ID�鿴һ����ɫ
	 * 
	 * @param id
	 * @return
	 */
	public Role getOneByID(int id);

	/**
	 * ���ܣ�ͨ����ɫֵ�鿴һ��Ȩ��
	 * 
	 * @param name
	 * @return
	 */
	public Role getOneByName(String name);

}

/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.dao.api;

import com.itree.entity.Perm;

public interface PermissionDao {

	/**
	 * ���ܣ�����Ȩ��
	 * 
	 * @param perm
	 * @return
	 */
	public boolean add(Perm perm);

	/**
	 * ���ܣ�ͨ��IDɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteByID(int id);

	/**
	 * ���ܣ�����Ȩ��
	 * 
	 * @param perm
	 * @return
	 */
	public boolean update(Perm permission);

	/**
	 * ���ܣ�ͨ��ID�鿴һ��Ȩ��
	 * 
	 * @param id
	 * @return
	 */
	public Perm getOneByID(int id);

	/**
	 * ���ܣ�ͨ��ID�鿴һ���ͻ���Ȩ��ID
	 * 
	 * @param id
	 * @return
	 */
	public int getClientIDByID(int id);

	/**
	 * ���ܣ�ͨ���ͻ���ID�鿴һ��Ȩ��
	 * 
	 * @param id
	 * @return
	 */
	public Perm getOneByClientID(int id);

	/**
	 * ���ܣ��鿴����Ȩ��
	 * 
	 * @return
	 */
	/* public List<Perm> getAll(); */

}

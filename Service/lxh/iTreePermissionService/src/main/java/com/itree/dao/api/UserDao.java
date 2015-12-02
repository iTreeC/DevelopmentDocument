/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.dao.api;

import com.itree.entity.User;

public interface UserDao {

	/**
	 * ���ܣ������û�
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(User user);

	/**
	 * ���ܣ�ɾ���û�
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteByClientUserId(int id);

	/**
	 * ���ܣ������û���Ϣ
	 * 
	 * @param user
	 * @return
	 */
	public boolean update(User user);

	/**
	 * ���ܣ�ͨ��ID�鿴ĳһ���û���Ϣ
	 * 
	 * @param id
	 *            �û�ID
	 * @return
	 */
	public User getOneByID(int id);

	/**
	 * ����:ͨ���ͻ���ID�鿴ĳһ���û���Ϣ
	 * 
	 * @param id
	 *            �ͻ���ID
	 * @return
	 */
	public User getOneByClientUserID(int id);

	/**
	 * ���ܣ��鿴����
	 * 
	 * @return
	 */
	/* public List<User> getAll(); */

}

/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.dao.api;

import com.itree.entity.User;

public interface UserDao {

	/**
	 * 功能：增加用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(User user);

	/**
	 * 功能：删除用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteByClientUserId(int id);

	/**
	 * 功能：更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean update(User user);

	/**
	 * 功能：通过ID查看某一条用户信息
	 * 
	 * @param id
	 *            用户ID
	 * @return
	 */
	public User getOneByID(int id);

	/**
	 * 功能:通过客户端ID查看某一条用户信息
	 * 
	 * @param id
	 *            客户端ID
	 * @return
	 */
	public User getOneByClientUserID(int id);

	/**
	 * 功能：查看所有
	 * 
	 * @return
	 */
	/* public List<User> getAll(); */

}

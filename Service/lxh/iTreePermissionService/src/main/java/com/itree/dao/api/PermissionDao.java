/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.dao.api;

import java.util.List;

import com.itree.entity.Perm;

public interface PermissionDao {

	/**
	 * 功能：增加权限
	 * 
	 * @param perm
	 * @return
	 */
	public boolean add(Perm perm);

	/**
	 * 功能：通过ID删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteByID(int id);

	/**
	 * 功能：更新权限
	 * 
	 * @param perm
	 * @return
	 */
	public boolean update(Perm permission);

	/**
	 * 功能：通过ID查看一条权限
	 * 
	 * @param id
	 * @return
	 */
	public Perm getOneByID(int id);

	/**
	 * 功能：通过ID查看一条客户端权限ID
	 * 
	 * @param id
	 * @return
	 */
	public int getClientIDByID(int id);
	
	/**
	 * 功能：通过ID查看一条客户端权限ID
	 * 
	 * @param id
	 * @return
	 */
	public List<Integer> getClientIDByID(List<Integer> id);

	/**
	 * 功能：通过ID查看客户端权限ID
	 * 
	 * @param id
	 * @return
	 */
	public int getIDByClientID(int id);
	
	/**
	 * 功能：通过ID查看客户端权限ID
	 * 
	 * @param id
	 * @return
	 */
	public List<Integer> getIDByClientID(List<Integer> id);
	
	

	/**
	 * 功能：通过客户端ID查看一条权限
	 * 
	 * @param id
	 * @return
	 */
	public Perm getOneByClientID(int id);

	/**
	 * 功能：查看所有权限
	 * 
	 * @return
	 */
	/* public List<Perm> getAll(); */

}

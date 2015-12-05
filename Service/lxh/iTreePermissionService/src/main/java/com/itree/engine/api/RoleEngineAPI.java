/**
 * @info 
 * @author ������
 * @time 2015.11.09
 */
package com.itree.engine.api;

import java.util.List;

import com.itree.entity.Role;

public interface RoleEngineAPI extends Engine {

	/**
	 * ����:���ӽ�ɫ
	 * 
	 * @param name
	 *            Role name
	 * @return true/false
	 */
	public Boolean add(String name);

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
	 * ���ܣ�ɾ����ɫ
	 * 
	 * @param rid
	 *            Role ID
	 * @return true/false
	 */
	public Boolean delete(int rid);

	/**
	 * ���ܣ����½�ɫ
	 * 
	 * @param rid
	 *            Role ID
	 * @param name
	 *            Role name
	 * @return true/false
	 */
	public Boolean update(int rid, String name);

	/**
	 * ���ܣ��鿴���н�ɫ��Ϣ
	 * 
	 * @return List<Role>
	 */
	public List<Role> getAll();

	/**
	 * ���ܣ�ͨ����ɫID�鿴һ����ɫ��Ϣ
	 * 
	 * @param rid
	 *            Role ID
	 * @return Role
	 */
	public Role getOneByID(int rid);

	/**
	 * ���ܣ�ͨ����ɫID�鿴һ����ɫ��Ϣ
	 * 
	 * @param name
	 *            Role name
	 * @return
	 */
	public Role getOneByName(String name);

	/* public List<String> getAllName(); */
	/* public String getNameByID(int id); */
}

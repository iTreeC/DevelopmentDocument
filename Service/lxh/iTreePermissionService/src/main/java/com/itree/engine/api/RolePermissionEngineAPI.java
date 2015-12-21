/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface RolePermissionEngineAPI extends Engine {

	/**
	 * ���ܣ����ӽ�ɫȨ��
	 * 
	 * @param rid
	 *            role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean add(int rid, List<Integer> pid);

	/**
	 * ���ܣ�ɾ����ɫȨ��
	 * 
	 * @param id
	 *            role ID
	 * @return true/false
	 */
	public Boolean delete(int rid);

	/**
	 * ���ܣ����½�ɫȨ��
	 * 
	 * @param rid
	 *            role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean update(int rid, List<Integer> pid);
	
	/**
	 * ���ܣ��鿴ĳ��ɫ��Ȩ��ID
	 * 
	 * @param rid
	 *            Role ID
	 * @return List<Integer>
	 */
	public List<Integer> getRolePermissionID(int rid);

	/**
	 * ���ܣ���ɫ-Ȩ��ƥ��
	 * 
	 * @param rid
	 *            role ID
	 * @param pid
	 *            Permission ID
	 * @return true/false
	 */
	public Boolean cando(int rid, int pid);

	/* public List<Integer> getPermissionID(int rid); */
}

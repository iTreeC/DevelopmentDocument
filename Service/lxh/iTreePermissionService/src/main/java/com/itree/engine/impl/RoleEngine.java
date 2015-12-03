/**
 * @info 
 * @author ������
 * @time 2015.11.09
 */
package com.itree.engine.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.RoleEngineAPI;
import com.itree.entity.Role;

public class RoleEngine extends EngineImpl implements RoleEngineAPI {

	private static Logger logger = Logger
			.getLogger(UserPermissionEngine.class);

	public Boolean add(String name) {

		if (name.equals(null)) {
			logger.error("��ɫ����Ϊ�գ�����");
			return false;
		}
		if (super.rdao.getOneByName(name) != null) {
			logger.error("�ý�ɫ�Ѵ���.");
			return false;
		}
		Role role = new Role();
		role.setName(name);
		return super.rdao.add(role);
	}

	
	
	public Boolean delete(int id) {
		if (id == 0) {
			logger.error("��ɫID����Ϊ�գ�����");
			return false;
		}
		return super.rdao.deleteByID(id);

	}

	public Boolean update(int rid, String name) {
		if (rid == 0 || name == null) {
			logger.error("��ɫID����Ϊ�գ�����");
			return false;
		}
		Role role = new Role();
		role.setId(rid);
		role.setName(name);
		return super.rdao.update(role);

	}

	public List<Role> getAll() {
		return super.rdao.getAll();
	}

	public Role getOneByID(int id) {
		if (id == 0) {
			logger.error("��ɫID����Ϊ�գ�����");
			return null;
		}
		return super.rdao.getOneByID(id);
	}
	public Role getOneByName(String name) {
		if (name.equals(null)) {
			logger.error("--");
			return null;
		}
		return super.rdao.getOneByName(name);
	}
	/**
	 * ���ܣ�������name
	 * 
	 * @return name�б�
	 */
	/*
	 * public List<String> getAllName() { return super.rdao.getAllName(); }
	 */

	/**
	 * ���ܣ�ͨ��id��name
	 * 
	 * @param id
	 * @return name
	 */
	/*
	 * public String getNameByID(int id) { if (id == 0) {
	 * logger.error("��ɫID����Ϊ�գ�����"); return null; } Role r =
	 * super.rdao.getOneById(id); if (r != null) { return r.getName(); } else {
	 * return null; } }
	 */

}

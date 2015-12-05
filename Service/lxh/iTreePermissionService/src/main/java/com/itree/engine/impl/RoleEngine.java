/**
 * @info 
 * @author ������
 * @time 2015.11.09
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.RoleEngineAPI;
import com.itree.entity.Role;

public class RoleEngine extends EngineImpl implements RoleEngineAPI {

	private static Logger logger = Logger.getLogger(UserPermissionEngine.class);

	private int tempt;
	private List<Integer> t = new ArrayList<Integer>();// ��ʱ����

	public Boolean add(String name) {
		// �жϲ����Ƿ�Ϸ�
		if (name.equals(null)) {
			logger.info("��ɫ����Ϊ�գ�����");
			return false;
		}
		// �鿴���ݿ����Ƿ���ڴ˽�ɫ
		if (super.rdao.getOneByName(name) != null) {
			logger.info("�ý�ɫ�Ѵ���.");
			return false;
		}
		// �����ݿ�����Ӵ˽�ɫ
		return super.rdao.add(name);
	}

	public Boolean addRoleAndPermission(String name, List<Integer> pid) {

		// ��������Ϸ����鿴���ݿ����Ƿ��Ѿ��洢�������ɫ
		// ���û�д洢�����ɫ����洢�����ɫ
		if (name == null || pid == null)
			return false;
		Role r = rdao.getOneByName(name);
		if (r == null) {
			rdao.add(name);
			r = rdao.getOneByName(name);
		}
		// �õ���ɫ��ID
		tempt = r.getId();
		// �õ�Ҫ����Ȩ���ڱ����ݿ��е�IDֵ
		pid = pdao.getIDByClientID(dereplication(pid));

		// ��������Ϸ����Խ������Ȩ�޺����ݿ��е�Ȩ��ֵ�ȶԺ�ȥ��
		if (pid.size() == 0 || tempt == 0)
			return false;
		t = rpdao.findPermissionIDByRoleID(tempt);
		if (t != null)
			pid.removeAll(t);
		// ִ�����Ȩ��
		return rpdao.add(tempt, pid);

	}

	public Boolean delete(int id) {
		// �жϲ����Ƿ�Ϸ�
		if (id == 0) {
			logger.info("��ɫID����Ϊ�գ�����");
			return false;
		}
		// �����ݿ���ɾ���˽�ɫ
		return super.rdao.deleteByID(id);

	}

	public Boolean update(int rid, String name) {
		// �жϲ����Ƿ�Ϸ�
		if (rid == 0 || name == null) {
			logger.info("��ɫID����Ϊ�գ�����");
			return false;
		}
		// �����ݿ��и��´˽�ɫ
		Role role = new Role();
		role.setId(rid);
		role.setName(name);
		return super.rdao.update(role);

	}

	public List<Role> getAll() {
		// �鿴���н�ɫ
		return super.rdao.getAll();
	}

	public Role getOneByID(int id) {
		// �жϲ����Ƿ�Ϸ�
		if (id == 0) {
			logger.info("��ɫID����Ϊ�գ�����");
			return null;
		}
		// ͨ��ID�鿴һ����ɫ
		return super.rdao.getOneByID(id);
	}

	public Role getOneByName(String name) {
		// �жϲ����Ƿ�Ϸ�
		if (name.equals(null)) {
			logger.info("��ɫֵ����Ϊnull");
			return null;
		}
		// ͨ��Name�鿴һ����ɫ
		return super.rdao.getOneByName(name);
	}

}

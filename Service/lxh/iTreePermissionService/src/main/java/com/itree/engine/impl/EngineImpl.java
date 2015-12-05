package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itree.dao.api.PermissionDao;
import com.itree.dao.api.RoleDao;
import com.itree.dao.api.UserDao;
import com.itree.dao.api.UserPermissionDao;
import com.itree.dao.api.UserRoleDao;
import com.itree.dao.impl.RolePermissionDaoImpl;
import com.itree.engine.api.Engine;
import com.itree.entity.Role;

public class EngineImpl implements Engine {

	@Autowired
	PermissionDao pdao;
	@Autowired
	RoleDao rdao;
	@Autowired
	RolePermissionDaoImpl rpdao;
	@Autowired
	UserDao udao;
	@Autowired
	UserRoleDao urdao;
	@Autowired
	UserPermissionDao updao;

	private List<Integer> tid = new ArrayList<Integer>();// ��ʱ����
	private List<Integer> rt = new ArrayList<Integer>();// ��ʱ����

	public List<Integer> getRolePermissionID(int rid) {
		//�жϲ����Ƿ�Ϸ�
		if (rid == 0)
			return null;
		// �õ�Ҫ���ҵ�Ȩ���ڱ����ݿ��е�ID
		tid = rpdao.findPermissionIDByRoleID(rid);
		// ���������Ϊ0��������ʵȨ��ID
		if (tid.size() == 0)
			return null;
		return pdao.getClientIDByID(tid);

	}

	public List<Integer> dereplication(List<Integer> ids) {
		// �жϲ����Ƿ�Ϸ�
		if (ids.size() == 0)
			return null;

		HashSet<Integer> id = new HashSet<Integer>(ids);
		// ��ջ���
		ids.clear();
		ids.addAll(id);

		return ids;
	}

}

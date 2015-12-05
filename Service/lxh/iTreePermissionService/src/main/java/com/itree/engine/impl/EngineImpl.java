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

	private List<Integer> tid = new ArrayList<Integer>();// 临时变量
	private List<Integer> rt = new ArrayList<Integer>();// 临时变量

	public List<Integer> getRolePermissionID(int rid) {
		//判断参数是否合法
		if (rid == 0)
			return null;
		// 得到要查找的权限在本数据库中的ID
		tid = rpdao.findPermissionIDByRoleID(rid);
		// 如果参数不为0，返回真实权限ID
		if (tid.size() == 0)
			return null;
		return pdao.getClientIDByID(tid);

	}

	public List<Integer> dereplication(List<Integer> ids) {
		// 判断参数是否合法
		if (ids.size() == 0)
			return null;

		HashSet<Integer> id = new HashSet<Integer>(ids);
		// 清空缓存
		ids.clear();
		ids.addAll(id);

		return ids;
	}

}

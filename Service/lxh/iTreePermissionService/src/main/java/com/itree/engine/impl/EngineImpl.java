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

	private List<Integer> tids = new ArrayList<Integer>();
	private List<Integer> pids = new ArrayList<Integer>();
	private List<Integer> rids = new ArrayList<Integer>();
	private List<Role> roles = new ArrayList<Role>();
	private int tempt;
	private Role role;
	
/*	public List<Integer> getIDByClientPermissionID(List<Integer> id){
		tids = this.dereplication(id);
		pids.clear();
		for (int i = 0; i < tids.size(); i++)
			pids.add(pdao.getOneByClientID(tids.get(i)).getId());
		return pids;
	}*/
	
	public Boolean addRoleAndPermission(String name, List<Integer> pid) {

		if (name == null & pid == null)
			return false;
		role = rdao.getOneByName(name);

		if (role == null) {
			Role role = new Role();
			role.setName(name);
			rdao.add(role);
			role = rdao.getOneByName(name);
		}
		
		tempt = role.getId();

		// 去除重复数据
		pid = this.dereplication(pid);
		pids=pdao.getIDByClientID(pid);
		// 和数据库中的权限值比对
		tids = rpdao.findPermissionIDByRoleID(tempt);
		if (tids != null)
			pids.removeAll(tids);

		// 添加权限
		if (pids.size() == 0) {
			return false;
		}
		return rpdao.add(tempt, pids);

	}

	public List<Integer> getRolePermissionID(int rid) {
		if (rid == 0)
			return null;

		pids = rpdao.findPermissionIDByRoleID(rid);

		if (pids == null)
			return null;

		// 清空缓存
		tids.clear();

		for (int i = 0; i < pids.size(); i++) {
			tempt = pdao.getClientIDByID(pids.get(i));
			if (tempt != 0)
				tids.add(tempt);

		}
		return tids;
	}

	public List<Role> getUserRole(int uid) {
		if (uid == 0)
			return null;

		tids = urdao.findRoleIDByUserID(uid);

		if (tids == null)
			return null;

		// 清空缓存
		roles.clear();

		for (int i = 0; i < tids.size(); i++) {
			role = rdao.getOneByID(tids.get(i));

			if (role != null)
				roles.add(role);
		}
		return roles;
	}

	public List<Integer> getUserRolePermissionID(int uid) {
		if (uid == 0)
			return null;

		rids = urdao.findRoleIDByUserID(uid);

		if (rids == null)
			return null;

		pids.clear();
		for (int i = 0; i < rids.size(); i++)
			pids.addAll(rpdao.findPermissionIDByRoleID(rids.get(i)));

		// 去除重复数据
		pids = this.dereplication(pids);
		// 清空缓存
		tids.clear();
		// 遍历找到权限ID
		for (int i = 0; i < pids.size(); i++) {
			tempt = pdao.getClientIDByID(pids.get(i));
			if (tempt != 0)
				tids.add(tempt);
		}

		return tids;
	}

	public List<Integer> getUserPermissionID(int uid) {
		if (uid == 0)
			return null;
		pids = updao.findUserPermissionID(uid);

		if (pids == null)
			return null;
		// 清空缓存
		tids.clear();
		for (int i = 0; i < pids.size(); i++) {

			tempt = pdao.getClientIDByID(pids.get(i));
			if (tempt != 0)
				tids.add(tempt);

		}
		return tids;
	}

	public List<Integer> dereplication(List<Integer> ids) {
		if (ids == null)
			return null;

		HashSet<Integer> id = new HashSet<Integer>(ids);
		// 清空缓存
		ids.clear();
		ids.addAll(id);

		return ids;
	}

}

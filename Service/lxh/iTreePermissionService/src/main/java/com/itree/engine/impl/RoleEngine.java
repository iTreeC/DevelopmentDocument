/**
 * @info 
 * @author 李晓欢
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
	private List<Integer> t = new ArrayList<Integer>();// 临时变量

	public Boolean add(String name) {
		// 判断参数是否合法
		if (name.equals(null)) {
			logger.info("角色不能为空！！！");
			return false;
		}
		// 查看数据库中是否存在此角色
		if (super.rdao.getOneByName(name) != null) {
			logger.info("该角色已存在.");
			return false;
		}
		// 在数据库中添加此角色
		return super.rdao.add(name);
	}

	public Boolean addRoleAndPermission(String name, List<Integer> pid) {

		// 如果参数合法，查看数据库中是否已经存储了这个角色
		// 如果没有存储这个角色，则存储这个角色
		if (name == null || pid == null)
			return false;
		Role r = rdao.getOneByName(name);
		if (r == null) {
			rdao.add(name);
			r = rdao.getOneByName(name);
		}
		// 得到角色的ID
		tempt = r.getId();
		// 得到要存入权限在本数据库中的ID值
		pid = pdao.getIDByClientID(dereplication(pid));

		// 如果参数合法，对将存入的权限和数据库中的权限值比对后去重
		if (pid.size() == 0 || tempt == 0)
			return false;
		t = rpdao.findPermissionIDByRoleID(tempt);
		if (t != null)
			pid.removeAll(t);
		// 执行添加权限
		return rpdao.add(tempt, pid);

	}

	public Boolean delete(int id) {
		// 判断参数是否合法
		if (id == 0) {
			logger.info("角色ID不能为空！！！");
			return false;
		}
		// 在数据库中删除此角色
		return super.rdao.deleteByID(id);

	}

	public Boolean update(int rid, String name) {
		// 判断参数是否合法
		if (rid == 0 || name == null) {
			logger.info("角色ID不能为空！！！");
			return false;
		}
		// 在数据库中更新此角色
		Role role = new Role();
		role.setId(rid);
		role.setName(name);
		return super.rdao.update(role);

	}

	public List<Role> getAll() {
		// 查看所有角色
		return super.rdao.getAll();
	}

	public Role getOneByID(int id) {
		// 判断参数是否合法
		if (id == 0) {
			logger.info("角色ID不能为空！！！");
			return null;
		}
		// 通过ID查看一条角色
		return super.rdao.getOneByID(id);
	}

	public Role getOneByName(String name) {
		// 判断参数是否合法
		if (name.equals(null)) {
			logger.info("角色值不能为null");
			return null;
		}
		// 通过Name查看一条角色
		return super.rdao.getOneByName(name);
	}

}

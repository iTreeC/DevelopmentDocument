/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.entity.Role;

public class UserRoleEngine extends EngineImpl implements UserRoleEngineAPI {

	private static Logger logger = Logger.getLogger(UserPermissionEngine.class);

	private List<Integer> tid = new ArrayList<Integer>();
	private List<Integer> rid = new ArrayList<Integer>();

	public Boolean add(int uid, List<Integer> rid) {
		// 判断参数是否合法
		if (uid == 0 || rid.size() == 0) {
			logger.info("角色或角色ID不能为空值！！！");
			return false;
		}
		// 得到用户的存储ID,并判断存储ID是否存在
		uid = udao.getIDByClientID(uid);
		if (uid == 0)
			return false;
		// 去除角色ID的重复数据
		rid = this.dereplication(rid);

		// 得到数据库中存储的角色ID,并去除待存数据中的重复值
		tid = super.urdao.findRoleIDByUserID(uid);
		if (tid != null)
			rid.removeAll(tid);

		if (rid.size() == 0) {
			logger.info("该角色已经存在，不能重复添加");
			return false;
		}
		// 执行添加操作
		return super.urdao.add(uid, rid);
	}

	public Boolean delete(int uid) {
		// 判断参数是否合法
		if (uid == 0) {
			logger.info("用户名为空！！！");
			return null;
		}
		// 得到用户的存储ID,并判断存储ID是否存在
		uid = udao.getIDByClientID(uid);
		if (uid == 0)
			return false;
		// 执行删除用户-角色关系操作
		return super.urdao.deleteByUserID(uid);
	}

	public Boolean update(int uid, List<Integer> rid) {
		// 判断参数是否合法
		if (uid == 0 || rid.size() == 0) {
			logger.info("角色或角色ID不能为空值！！！");
			return false;
		}
		// 得到用户的存储ID,并判断存储ID是否存在
		uid = udao.getIDByClientID(uid);
		if (uid == 0)
			return false;
		// 执行更新操作
		return super.urdao.update(uid, dereplication(rid));
	}

	public List<Integer> getRIds(int uid) {
		// 判断参数是否合法
		if (uid == 0) {
			logger.info("用户名为空！！！");
			return null;
		}
		// 得到用户的存储ID,并判断存储ID是否存在
		uid = udao.getIDByClientID(uid);
		if (uid == 0)
			return null;
		// 通过用户存储ID查看用户角色ID
		return urdao.findRoleIDByUserID(uid);
	}

	public List<Role> getUserRole(int uid) {

		// 如果参数合法，得到要查找的角色ID
		if (uid == 0)
			return null;
		tid = urdao.findRoleIDByUserID(uid);
		// 如果参数合法，返回角色列表
		if (tid.size() == 0)
			return null;
		return rdao.getRoleByID(tid);
	}

	public List<Integer> getUserRolePermissionID(int uid) {
		// 如果参数合法，得到角色ID
		if (uid == 0)
			return null;
		rid = urdao.findRoleIDByUserID(uid);
		// 如果参数合法，得到权限存储ID
		if (rid.size() == 0)
			return null;
		tid.clear();
		for (int i = 0; i < rid.size(); i++)
			tid.addAll(rpdao.findPermissionIDByRoleID(rid.get(i)));
		// 如果参数合法，得到权限真实ID
		if (tid.size() == 0)
			return null;
		return pdao.getClientIDByID(dereplication(tid));

	}

	public Boolean candoRole(int uid, int rid) {

		// 判断参数是否合法
		if (uid == 0 || rid == 0) {
			logger.info("参数不能为空值！！！");
			return false;
		}
		// 得到用户ID的存储ID
		uid = udao.getIDByClientID(uid);
		// 得到角色ID，并判断是否合法
		tid = super.urdao.findRoleIDByUserID(uid);
		if (tid.size() == 0)
			return false;
		// 遍历角色ID，进行角色匹配
		for (int i = 0; i < tid.size(); i++) {
			if (tid.get(i) == rid) {
				logger.info("角色匹配成功");
				return true;
			}
		}
		logger.info("角色匹配失败");
		return false;
	}

	public Boolean candoPermission(int uid, int pid) {

		// 判断参数是否合法
		if (uid == 0 || pid == 0) {
			logger.info("参数不能为空值！！！");
			return false;
		}
		// 得到用户和权限的存储ID，并判断是否合法
		uid = udao.getIDByClientID(uid);
		pid = pdao.getIDByClientID(pid);
		if (uid == 0 || pid == 0)
			return false;
		// 得到角色ID,并判断是否合法
		rid = super.urdao.findRoleIDByUserID(uid);
		if (rid == null)
			return false;
		// 通过角色ID得到权限的存储ID，并判断是否合法
		tid.clear();
		for (int i = 0; i < rid.size(); i++)
			tid = super.rpdao.findPermissionIDByRoleID(rid.get(i));
		if (tid.size() == 0)
			return null;
		// 遍历权限ID的存储ID，进行权限匹配
		for (int i = 0; i < tid.size(); i++) {
			if (tid.get(i) == pid) {
				logger.info("权限匹配成功");
				return true;
			}
		}
		logger.info("权限匹配失败");
		return false;
	}

	/*
	 * public List<UserRole> getRoleByUserID(int uid) {
	 * 
	 * if (uid == 0) { logger.info("用户名为空！！！"); return null; }
	 * 
	 * List<UserRole> user = super.urdao.findByUId(uid);
	 * 
	 * if (user != null) { logger.info("查找用户角色成功"); return user; }
	 * logger.info("查无此用户角色，请确认用户ID是否正确"); return null;
	 * 
	 * }
	 */
}

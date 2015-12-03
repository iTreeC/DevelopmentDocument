/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.UserPermissionEngineAPI;
import com.itree.entity.UserPermission;

public class UserPermissionEngine extends EngineImpl implements
		UserPermissionEngineAPI {

	private static Logger logger = Logger.getLogger(UserPermissionEngine.class);

	private List<Integer> pids = new ArrayList<Integer>();
	private List<Integer> pid2 = new ArrayList<Integer>();
	private int temptp;
	private int temptu;

	public Boolean add(int uid, List<Integer> pid) {

		if (uid == 0 || pid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		// 去除重复数据
		pid = super.dereplication(pid);

		temptu = udao.getIDByClientID(uid);
		pids = pdao.getIDByClientID(pid);

		// 和数据库中的权限值比对
		pid2 = super.updao.findUserPermissionID(temptu);
		if (pid2 != null)
			pids.removeAll(pid2);

		// 添加权限
		if (pids.size() == 0) {
			logger.error("该权限已经存在，不能重复添加");
			return false;
		}
		return super.updao.add(temptu, pids);
	}

	public Boolean delete(int uid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}
		temptu = udao.getIDByClientID(uid);

		return super.updao.deleteByUserID(temptu);
	}

	public Boolean update(int uid, List<Integer> pid) {
		if (uid == 0 || pid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		// 去除重复数据
		pid = super.dereplication(pid);

		temptu = udao.getIDByClientID(uid);
		pids = pdao.getIDByClientID(pid);

		return super.updao.update(temptu, pids);
	}

	public List<Integer> getPermissionIDByUserID(int uid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}
		temptu = udao.getIDByClientID(uid);

		pids = super.updao.findUserPermissionID(temptu);

		if (pids != null) {
			logger.info("查找用户权限成功");
			return pids;
		}
		logger.error("查无此用户权限，请确认用户ID是否正确");
		return null;
	}

	public Boolean cando(int uid, int pid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return false;
		}
		temptu = udao.getIDByClientID(uid);
		temptp = pdao.getIDByClientID(pid);
		List<UserPermission> userpermission = super.updao
				.findListByUserID(temptu);
		if (userpermission != null) {
			for (int i = 0; i < userpermission.size(); i++) {
				if (userpermission.get(i).getPerm().getClientPermissionID() == temptp) {
					logger.info("权限匹配成功");
					return true;
				}
			}
		} else
			logger.info("查无此权限");
		logger.error("权限匹配失败");
		return false;
	}
	/**
	 * 功能：查看
	 * 
	 * @param uid
	 *            用户ID
	 * @return 用户-权限关系的列表
	 */
	/*
	 * public List<UserPermission> getPermission(int uid) { if (uid == 0) {
	 * logger.error("用户名为空！！！"); return null; } super.updao.findUserPIds(uid);
	 * List<UserPermission> user = super.updao.findByUId(uid);
	 * 
	 * if (user != null) { logger.info("查找用户权限成功"); return user; } else {
	 * logger.error("查无此用户权限，请确认用户ID是否正确"); return null; } }
	 */
}

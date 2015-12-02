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
import com.itree.entity.UserRole;

public class UserRoleEngine extends EngineImpl implements UserRoleEngineAPI {

	private static Logger logger = Logger
			.getLogger(UserPermissionEngine.class);

	private List<Integer> rids = new ArrayList<Integer>();
	private List<Integer> rid2 = new ArrayList<Integer>();

	public Boolean add(int uid, List<Integer> rid) {

		if (uid == 0 || rid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}

		// 去除重复数据
		rids = this.dereplication(rid);

		// 和数据库中的数据比对
		rid2 = super.urdao.findRoleIDByUserID(uid);
		if (rid2 != null)
			rids.removeAll(rid2);

		if (rids.size() == 0) {
			logger.info("该权限已经存在，不能重复添加");
			return false;
		}
		return super.urdao.add(uid, rids);
	}

	public Boolean delete(int uid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}
		return super.urdao.deleteByUserID(uid);
	}

	public Boolean update(int uid, List<Integer> rid) {
		if (uid == 0 || rid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		// 去除重复数据
		rids = this.dereplication(rid);

		return super.urdao.update(uid, rids);
	}

	public List<Integer> getRIds(int uid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}
		rids = super.urdao.findRoleIDByUserID(uid);

		if (rids != null) {
			logger.info("查找用户权限成功");
			return rids;
		}
		logger.error("查无此用户权限，请确认用户ID是否正确");
		return null;
	}

	public Boolean cando(int uid, int rid) {

		if (uid == 0 || rid == 0) {
			logger.error("参数不能为空值！！！");
			return false;
		}

		List<UserRole> userrole = super.urdao.findListByUserID(uid);
		if (userrole != null) {
			for (int i = 0; i < userrole.size(); i++) {
				if (userrole.get(i).getRole().getId() == rid) {
					logger.info("权限匹配成功");
					return true;
				}
			}
		} else
			logger.error("查无此权限");
		logger.error("权限匹配失败");
		return false;
	}

	/*
	 * public List<UserRole> getRoleByUserID(int uid) {
	 * 
	 * if (uid == 0) { logger.error("用户名为空！！！"); return null; }
	 * 
	 * List<UserRole> user = super.urdao.findByUId(uid);
	 * 
	 * if (user != null) { logger.info("查找用户权限成功"); return user; }
	 * logger.error("查无此用户权限，请确认用户ID是否正确"); return null;
	 * 
	 * }
	 */
}

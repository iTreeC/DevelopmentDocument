/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.itree.dao.api.UserPermissionDao;
import com.itree.engine.api.UsersPermissionEngineAPI;
import com.itree.entity.UserPermission;

public class UsersPermissionEngine implements UsersPermissionEngineAPI {

	private static Logger logger = Logger
			.getLogger(UsersPermissionEngine.class);

	UserPermissionDao updao;

	@Resource
	@Required
	public void setUpdao(UserPermissionDao updao) {
		this.updao = updao;
	}

	/**
	 * 功能：添加
	 * 
	 * @param uid
	 *            用户ID
	 * @param pid
	 *            权限ID
	 * @return true/false
	 */
	public Boolean add(int uid, List<Integer> pid) {

		if (uid == 0 || pid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}

		// 去除重复数据
		HashSet<Integer> id = new HashSet<Integer>(pid);
		pid.clear();
		pid.addAll(id);

		List<Integer> pid2 = updao.findUserPIds(uid);
		if (pid2 != null)
			pid.removeAll(pid2);

		if (pid.size() == 0) {
			logger.error("该权限已经存在，不能重复添加");
			return false;
		}
		return updao.add(uid, pid);
	}

	/**
	 * 功能：删除
	 * 
	 * @param uid
	 *            用户ID
	 * @return true/false
	 */
	public Boolean delete(int uid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}
		return updao.deleteByUId(uid);
	}

	/**
	 * 功能：更新
	 * 
	 * @param uid
	 *            用户ID
	 * @param pid
	 *            权限ID
	 * @return true/false
	 */
	public Boolean update(int uid, List<Integer> pid) {
		if (uid == 0 || pid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		// 去除重复数据
		HashSet<Integer> pid2 = new HashSet<Integer>(pid);
		pid.clear();
		pid.addAll(pid2);

		return updao.updatePermission(uid, pid);
	}

	/**
	 * 功能：查看
	 * 
	 * @param uid
	 *            用户ID
	 * @return 用户-权限关系的列表
	 */
	public List<UserPermission> getPermission(int uid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}
		updao.findUserPIds(uid);
		updao.findAll();
		List<UserPermission> user = updao.findByUId(uid);

		if (user != null) {
			logger.info("查找用户权限成功");
			return user;
		} else {
			logger.error("查无此用户权限，请确认用户ID是否正确");
			return null;
		}
	}

	/**
	 * 功能：查看用户权限ID
	 * 
	 * @param uid
	 *            用户ID
	 * @return 用户权限ID集合
	 */
	public List<Integer> getPermissionID(int uid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}
		List<Integer> pids = updao.findUserPIds(uid);

		if (pids != null) {
			logger.info("查找用户权限成功");
			return pids;
		} else {
			logger.error("查无此用户权限，请确认用户ID是否正确");
			return null;
		}
	}

	/**
	 * 功能：用户-角色匹配
	 * 
	 * @param uid
	 *            用户ID
	 * @param pid
	 *            权限ID
	 * @return true/false
	 */
	public Boolean cando(int uid, int pid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return false;
		}
		List<UserPermission> user = updao.findByUId(uid);
		if (user != null) {
			for (int i = 0; i < user.size(); i++) {
				if (user.get(i).getPid() == pid) {
					logger.info("权限匹配成功");
					return true;
				}
			}
		} else {
			logger.info("查无此权限");
		}
		logger.error("权限匹配失败");
		return false;
	}

}

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

import com.itree.dao.api.UserRoleDao;
import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.entity.UserRole;

public class UserRoleEngine implements UserRoleEngineAPI {

	private static Logger logger = Logger
			.getLogger(UsersPermissionEngine.class);

	UserRoleDao urdao;

	@Resource
	@Required
	public void setUrdao(UserRoleDao urdao) {
		this.urdao = urdao;
	}

	/**
	 * 功能：添加
	 * 
	 * @param uid
	 *            用户ID
	 * @param rid
	 *            角色ID
	 * @return true/false
	 */
	public Boolean add(int uid, List<Integer> rid) {

		if (uid == 0 || rid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}

		// 去除重复数据
		HashSet<Integer> id = new HashSet<Integer>(rid);
		rid.clear();
		rid.addAll(id);

		// 和数据库中的数据比对
		List<Integer> rid2 = urdao.findUserRIds(uid);
		if (rid2 != null)
			rid.removeAll(rid2);

		if (rid.size() == 0) {
			logger.info("该权限已经存在，不能重复添加");
			return false;
		}
		return urdao.add(uid, rid);
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
		return urdao.deleteByUId(uid);
	}

	/**
	 * 功能：更新
	 * 
	 * @param uid
	 *            用户ID
	 * @param rid
	 *            角色ID
	 * @return true/false
	 */
	public Boolean update(int uid, List<Integer> rid) {
		if (uid == 0 || rid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		// 去除重复数据
		HashSet<Integer> rid2 = new HashSet<Integer>(rid);
		rid.clear();
		rid.addAll(rid2);

		return urdao.update(uid, rid);
	}

	/**
	 * 功能：通过用户ID查询
	 * 
	 * @param uid
	 *            用户ID
	 * @return 用户-角色列表
	 */
	public List<UserRole> getRoleByUser(int uid) {

		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}

		List<UserRole> user = urdao.findByUId(uid);

		if (user != null) {
			logger.info("查找用户权限成功");
			return user;
		} else {
			logger.error("查无此用户权限，请确认用户ID是否正确");
			return null;
		}
	}

	/**
	 * 功能：通过用户ID查询
	 * 
	 * @param uid
	 *            用户ID
	 * @return 角色ID的集合
	 */
	public List<Integer> getRIds(int uid) {
		if (uid == 0) {
			logger.error("用户名为空！！！");
			return null;
		}
		List<Integer> rids = urdao.findUserRIds(uid);

		if (rids != null) {
			logger.info("查找用户权限成功");
			return rids;
		} else {
			logger.error("查无此用户权限，请确认用户ID是否正确");
			return null;
		}
	}

	/**
	 * 功能：用户-角色 匹配
	 * 
	 * @param uid
	 *            用户ID
	 * @param rid
	 *            角色ID
	 * @return true/false
	 */
	public Boolean cando(int uid, int rid) {

		if (uid == 0 || rid == 0) {
			logger.error("参数不能为空值！！！");
			return false;
		}
		List<Integer> id = urdao.findUserRIds(uid);
		if (id != null) {
			for (int i = 0; i < id.size(); i++) {
				if (id.get(i) == rid) {
					logger.info("权限匹配成功");
					return true;
				}
			}
		} else {
			logger.error("查无此权限");
			return false;
		}
		logger.error("权限匹配失败");
		return false;
	}
}

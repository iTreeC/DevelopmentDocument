/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itree.engine.api.RolePermissionEngineAPI;

public class RolePermissionEngine extends EngineImpl implements
		RolePermissionEngineAPI {

	private static Logger logger = Logger.getLogger(RolePermissionEngine.class);

	private List<Integer> tid = new ArrayList<Integer>();

	public Boolean add(int rid, List<Integer> pid) {
		// 如果参数合法，通过权限实际ID得到存储ID
		if (rid == 0 || pid.size() == 0) {
			logger.info("参数不能为空值！！！");
			return false;
		}
		pid = pdao.getIDByClientID(dereplication(pid));
		// 如果参数合法，通过角色ID得到权限的存储ID
		if (pid.size() == 0) {
			logger.info("这些权限没有在数据库中存储，请先进行存储！");
			return false;
		}
		tid = rpdao.findPermissionIDByRoleID(rid);
		// 去掉已经储存了的权限ID
		if (tid != null & pid.size() != 0)
			pid.removeAll(tid);

		// 如果参数非空，执行添加权限
		if (pid.size() == 0)
			return false;
		return rpdao.add(rid, pid);
	}

	public Boolean delete(int rid) {
		// 如果参数合法
		if (rid == 0) {
			logger.info("角色为空值！！！");
			return false;
		}
		// 执行删除权限
		rpdao.deleteByRoleID(rid);
		return true;
	}

	public Boolean update(int rid, List<Integer> pid) {
		// 如果参数合法，得到权限的存储ID
		if (rid == 0 || pid.equals(null)) {
			logger.info("参数不能为空值！！！");
			return false;
		}
		pid = pdao.getIDByClientID(dereplication(pid));
		// 执行更新操作
		return rpdao.update(rid, pid);
	}

	public Boolean cando(int rid, int pid) {
		// 判断参数是否合法
		if (rid == 0 || pid == 0) {
			logger.info("角色为空值！！！");
			return false;
		}
		// 通过权限实际ID得到存储ID
		pid = pdao.getIDByClientID(pid);
		// 通过角色ID得到存储ID
		tid = rpdao.findPermissionIDByRoleID(rid);
		// 如果参数合法,循环执行匹配
		if (pid == 0 || tid.size() == 0)
			return false;
		for (int i = 0; i < tid.size(); i++) {
			if (tid.get(i) == pid) {
				logger.info("权限匹配成功");
				return true;
			}
		}
		// 循环结束，匹配失败
		logger.info("权限匹配失败");
		return false;
	}
	/*
	 * public List<Integer> getPermissionID(int rid) { // 如果参数合法，得到权限的存储ID if
	 * (rid == 0) { logger.info("角色为空值！！！"); return null; } tid =
	 * rpdao.findPermissionIDByRoleID(rid); // 如果参数合法，返回结果-权限真实ID if (tid !=
	 * null) { logger.info("查找角色权限成功"); return pdao.getClientIDByID(tid); }
	 * logger.info("查无此角色的权限，请确认角色ID是否正确！"); return null;
	 * 
	 * }
	 */

}

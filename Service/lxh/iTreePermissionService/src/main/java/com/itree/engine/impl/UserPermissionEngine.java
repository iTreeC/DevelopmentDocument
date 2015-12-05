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

public class UserPermissionEngine extends EngineImpl implements
		UserPermissionEngineAPI {

	private static Logger logger = Logger.getLogger(UserPermissionEngine.class);

	private List<Integer> tid = new ArrayList<Integer>();

	public Boolean add(int uid, List<Integer> pid) {

		// 如果参数合法，得到用户和权限的存储ID
		if (uid == 0 || pid.size() == 0) {
			logger.info("角色或权限ID不能为空值！！！");
			return false;
		}
		uid = udao.getIDByClientID(uid);
		pid = pdao.getIDByClientID(dereplication(pid));
		// 如果参数合法，和数据库中的权限值比对，并去掉重复数据
		if (uid == 0 || pid.size() == 0)
			return false;
		tid = super.updao.findUserPermissionID(uid);
		if (tid != null)
			pid.removeAll(tid);
		// 如果参数合法，添加权限
		if (pid.size() == 0) {
			logger.info("该权限已经存在，不能重复添加");
			return false;
		}
		return super.updao.add(uid, pid);
	}

	public Boolean delete(int uid) {

		// 如果参数合法，得到用户的存储ID
		if (uid == 0) {
			logger.info("用户名为空！！！");
			return null;
		}
		uid = udao.getIDByClientID(uid);
		// 如果参数合法，删除权限
		if (uid == 0)
			return false;
		return super.updao.deleteByUserID(uid);
	}

	public Boolean update(int uid, List<Integer> pid) {

		// 如果参数合法，得到用户和权限的存储ID
		if (uid == 0 || pid.equals(null)) {
			logger.info("参数不能为空值！！！");
			return false;
		}
		uid = udao.getIDByClientID(uid);
		pid = pdao.getIDByClientID(dereplication(pid));
		// 如果参数合法，更新用户-权限所有关系
		if (uid == 0 || pid.size() == 0)
			return false;
		return super.updao.update(uid, pid);
	}

	public List<Integer> getPermissionIDByUserID(int uid) {
		// 如果参数合法，得到用户的存储ID
		if (uid == 0) {
			logger.info("用户名为空！！！");
			return null;
		}
		uid = udao.getIDByClientID(uid);
		// 如果参数合法，得到用户权限的存储ID
		if (uid == 0)
			return null;
		tid = super.updao.findUserPermissionID(uid);
		// 如果参数合法，得到用户权限的真实ID
		if (tid == null) {
			logger.info("--");
			return null;
		}
		tid = pdao.getClientIDByID(tid);

		// 结果不管是null或者有效值，都返回给客户端。
		logger.info("查找用户权限成功");
		return tid;
	}
	
	public List<Integer> getUserPermissionID(int uid) {
		// 如果参数合法，得到权限存储ID
		if (uid == 0)
			return null;
		tid = updao.findUserPermissionID(uid);
		// 如果参数合法，返回真实权限ID
		if (tid.size() == 0)
			return null;
		return pdao.getClientIDByID(tid);

	}

	public Boolean cando(int uid, int pid) {

		// 如果参数合法，得到要匹配的用户和权限的存储ID
		if (uid == 0 || pid == 0) {
			logger.info("用户名为空！！！");
			return false;
		}
		uid = udao.getIDByClientID(uid);
		pid = pdao.getIDByClientID(pid);

		// 如果参数合法，得到具体的用户权限的存储ID
		if (uid == 0 || pid == 0)
			return false;
		List<Integer> tids = super.updao.findUserPermissionID(uid);
		// 如果参数合法，遍历具体的用户权限的存储ID，进行权限匹配
		if (tids.size() == 0)
			return false;
		for (int i = 0; i < tids.size(); i++) {
			if (tids.get(i) == pid) {
				logger.info("权限匹配成功");
				return true;
			}
		}
		logger.info("权限匹配失败");
		return false;
	}

}

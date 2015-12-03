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
import com.itree.entity.RolePermission;

public class RolePermissionEngine extends EngineImpl implements
		RolePermissionEngineAPI {

	private static Logger logger = Logger.getLogger(RolePermissionEngine.class);

	private List<Integer> pid = new ArrayList<Integer>();
	private int tempt;

	public Boolean add(int rid, List<Integer> cpid) {
		// 空值
		if (rid == 0 || cpid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		// 得到pid
		cpid = super.dereplication(cpid);
		cpid = pdao.getIDByClientID(cpid);
		// 和数据库中的权限值比对
		pid = rpdao.findPermissionIDByRoleID(rid);
		if (pid != null & cpid != null)
			cpid.removeAll(pid);

		// 添加权限
		if (cpid.size() == 0) {
			logger.info("该权限已经存在，不能重复添加");
			return false;

		}
		return rpdao.add(rid, cpid);
	}

	public Boolean delete(int rid) {
		// 空值
		if (rid == 0) {
			logger.error("角色为空值！！！");
			return false;
		}
		// 删除权限
		rpdao.deleteByRoleID(rid);
		logger.info("删除权限成功");
		return true;
	}

	public Boolean update(int rid, List<Integer> cpid) {
		// 空值
		if (rid == 0 || cpid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		// 得到pid
		cpid = super.dereplication(cpid);
		cpid = pdao.getIDByClientID(cpid);

		// 更新
		return rpdao.update(rid, cpid);
	}

	public List<Integer> getPermissionID(int rid) {
		// 空值
		if (rid == 0) {
			logger.error("角色为空值！！！");
			return null;
		}
		// 查找
		pid = rpdao.findPermissionIDByRoleID(rid);

		if (pid != null) {
			logger.info("查找角色权限成功");
			return pid;
		}
		logger.error("查无此角色的权限，请确认角色ID是否正确！");
		return null;

	}

	public Boolean cando(int rid, int cpid) {
		// 空值
		if (rid == 0 || cpid == 0) {
			logger.error("角色为空值！！！");
			return false;
		}

		// 得到pid
		tempt = pdao.getOneByClientID(cpid).getId();

		// 匹配
		List<RolePermission> role = rpdao.findListByRoleID(rid);
		if (role != null) {
			for (int i = 0; i < role.size(); i++) {
				if (role.get(i).getPerm().getId() == tempt) {
					logger.info("权限匹配成功");
					return true;
				}
			}
		} else
			logger.info("查无此角色");

		logger.info("权限匹配失败");
		return false;
	}

}

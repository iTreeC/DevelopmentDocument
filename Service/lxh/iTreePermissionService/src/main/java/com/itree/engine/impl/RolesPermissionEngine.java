/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.itree.dao.api.RolePermissionDao;
import com.itree.engine.api.RolesPermissionEngineAPI;
import com.itree.entity.RolePermission;

public class RolesPermissionEngine implements RolesPermissionEngineAPI {

	private static Logger logger = Logger
			.getLogger(RolesPermissionEngine.class);

	RolePermissionDao rpdao;

	@Resource  @Required
	public void setRpdao(RolePermissionDao rpdao) {
		this.rpdao = rpdao;
	}

	RolePermission role;

	public Boolean add(int rid, List<Integer> pid) {
		// 空值
		if (rid == 0 || pid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}

		// 去除重复数据
		List<Integer> pids = new ArrayList<Integer>();
		for (Integer i : pid) {
			if (!pids.contains(i)) {
				pids.add(i);
			}
		}

		// 和数据库中的权限值比对
		List<Integer> pid2 = rpdao.findRolePIds(rid);
		if (pid2 != null)
			pids.removeAll(pid2);

		// 添加权限
		if (pids.size() == 0) {
			logger.info("该权限已经存在，不能重复添加");
			return false;

		}
		return rpdao.add(rid, pids);
	}

	public Boolean delete(int rid) {
		// 空值
		if (rid == 0) {
			logger.error("角色为空值！！！");
			return false;
		}
		// 删除权限
		rpdao.deleteByRId(rid);
		logger.info("删除权限成功");
		return true;
	}

	public Boolean update(int rid, List<Integer> pid) {
		// 空值
		if (rid == 0 || pid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}

		// 去除重复数据
		List<Integer> pids = new ArrayList<Integer>();
		for (Integer i : pid) {
			if (!pids.contains(i)) {
				pids.add(i);
			}
		}
		// 更新
		return rpdao.updatePermission(rid, pids);
	}

	public List<Integer> getPermissionID(int rid) {
		// 空值
		if (rid == 0) {
			logger.error("角色为空值！！！");
			return null;
		}
		// 查找
		List<Integer> pids = rpdao.findRolePIds(rid);
		if (pids != null) {
			logger.info("查找角色权限成功");
			return pids;
		} else {
			logger.error("查无此角色的权限，请确认角色ID是否正确！");
			return null;
		}
	}

	public Boolean cando(int rid, int pid) {
		// 空值
		if (rid == 0 || pid == 0) {
			logger.error("角色为空值！！！");
			return false;
		}
		// 匹配
		List<RolePermission> role = rpdao.findByRId(rid);
		if (role != null) {
			for (int i = 0; i < role.size(); i++) {
				if (role.get(i).getPid() == pid) {
					logger.info("权限匹配成功");
					return true;
				}
			}
		} else {
			logger.info("查无此角色");
		}
		logger.info("权限匹配失败");
		return false;
	}

}

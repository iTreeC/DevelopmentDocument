/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.itree.dao.api.PermissionDao;
import com.itree.engine.api.PermissionEngineAPI;
import com.itree.entity.Perm;

public class PermissionEngine implements PermissionEngineAPI {

	private static Logger logger = Logger
			.getLogger(UsersPermissionEngine.class);

	PermissionDao pdao;

	@Resource
	@Required
	public void setPdao(PermissionDao pdao) {
		this.pdao = pdao;
	}

	/**
	 * 功能：添加权限
	 * 
	 * @param name
	 *            权限值
	 * @return true/false
	 */
	public Boolean add(String name) {

		if (name.equals(null)) {
			logger.error("权限不能为空！！！");
			return false;
		}
		if (pdao.getOneByName(name) != null) {
			logger.error("该权限已存在.");
			return false;
		}
		Perm perm = new Perm();
		perm.setName(name);
		return pdao.add(perm);
	}

	/**
	 * 功能：删除权限
	 * 
	 * @param id
	 *            权限id
	 * @return true/false
	 */
	public Boolean delete(int id) {
		if (id == 0) {
			logger.error("权限ID不能为空！！！");
			return false;
		}
		return pdao.deleteByRId(id);

	}

	/**
	 * 功能：更新权限
	 * 
	 * @param permission
	 *            权限
	 * @return true/false
	 */
	public Boolean update(Perm permission) {
		if (permission.equals(null)) {
			logger.error("权限ID不能为空！！！");
			return false;
		}
		return pdao.update(permission);

	}

	/**
	 * 功能：查看所有权限列表
	 * 
	 * @return 权限列表
	 */
	public List<Perm> getAll() {
		return pdao.getAll();
	}

	/**
	 * 功能：查所有name
	 * 
	 * @return name列表
	 */
	public List<String> getAllName() {
		return pdao.getAllName();
	}

	/**
	 * 功能：通过id查一条权限
	 * 
	 * @param id
	 * @return permission
	 */
	public Perm getOneByID(int id) {
		if (id == 0) {
			logger.error("权限ID不能为空！！！");
			return null;
		}
		return pdao.getOneById(id);
	}

	/**
	 * 功能：通过id查name
	 * 
	 * @param id
	 * @return name
	 */
	public String getNameByID(int id) {
		if (id == 0) {
			logger.error("权限ID不能为空！！！");
			return null;
		}
		/* return pdao.getNameByID(id); */
		Perm p = pdao.getOneById(id);
		if (p != null) {
			return p.getName();
		} else {
			return null;
		}
	}

}

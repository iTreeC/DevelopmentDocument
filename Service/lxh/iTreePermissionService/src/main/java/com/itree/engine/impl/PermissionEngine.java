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

	@Resource @Required
	public void setPdao(PermissionDao pdao) {
		this.pdao = pdao;
	}

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

	public Boolean delete(int id) {
		if (id == 0) {
			logger.error("权限ID不能为空！！！");
			return false;
		}
		return pdao.deleteByRId(id);

	}

	public Boolean update(Perm permission) {
		if (permission.equals(null)) {
			logger.error("权限ID不能为空！！！");
			return false;
		}
		return pdao.update(permission);

	}

	public List<Perm> getAll() {
		return pdao.getAll();
	}

	public List<String> getAllName() {
		return pdao.getAllName();
	}

	public Perm getOneByID(int id) {
		if (id == 0) {
			logger.error("权限ID不能为空！！！");
			return null;
		}
		return pdao.getOneById(id);
	}

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

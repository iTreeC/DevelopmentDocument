/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import org.apache.log4j.Logger;
import com.itree.engine.api.PermissionEngineAPI;
import com.itree.entity.Perm;

public class PermissionEngine extends EngineImpl implements PermissionEngineAPI {

	private static Logger logger = Logger.getLogger(UserPermissionEngine.class);

	public Boolean add(int id) {
		// 判断参数是否合法
		if (id == 0) {
			logger.info("ID不能为空！！！");
			return false;
		}
		// 查看数据库中是否存在此权限
		if (super.pdao.getOneByClientID(id) != null) {
			logger.info("该权限已存在.");
			return false;
		}
		// 在数据库中添加权限
		Perm perm = new Perm();
		perm.setClientPermissionID(id);
		return super.pdao.add(perm);
	}

	public Boolean delete(int id) {
		// 判断参数是否合法
		if (id == 0) {
			logger.info("权限ID不能为空！！！");
			return false;
		}
		// 在数据库中删除权限
		return super.pdao.deleteByID(id);

	}

}

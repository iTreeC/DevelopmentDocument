package com.position.dao.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.position.dao.ProNumberDao;
import com.position.pojo.Provincial_Number;
import com.position.utils.SessionUtils;

/**
 * Classname:ProNumberDaoImpl
 *
 * Version information:具体实现省表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
@Repository("pronumberdao")
public class ProNumberDaoImpl extends SessionUtils implements ProNumberDao {

	private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);
	private Provincial_Number pro;
	private List<Provincial_Number> list;

	// 根据id
	public Provincial_Number getById(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			pro = (Provincial_Number) getSession().get(Provincial_Number.class, id);
			return pro;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息获取失败", e);
			return null;
		}
	}

	// 根据名
	public Provincial_Number getByName(String name) {
		// TODO Auto-generated method stub
		try {
			if (name == null || name.trim().length() == 0) {
				logger.error("传入参数不能为空");
				return null;
			}
			String hql = "from Provincial_Number p where p.provincial =? ";
			list =  getSession().createQuery(hql).setString(0, name).list();
			pro = list.get(0);
			return pro;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息获取失败", e);
			return null;
		}
	}

	// 查找所有
	public List<Provincial_Number> getAll() {
		// TODO Auto-generated method stub
		try {
			list = getSession().createQuery("from Provincial_Number").list();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息获取失败", e);
			return null;
		}
	}

	// 增加
	public void add(Provincial_Number Pro) {
		// TODO Auto-generated method stub
		try {
			getSession().save(Pro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息存储失败", e);
		}
	}

	// 删除(隐藏式)
	public void deleteByIdHid(int ID) {
		// TODO Auto-generated method stub
		try {
			String hql = "update Provincial_Number p set p.usable = 0 where proID = ?";
			Query query = getSession().createQuery(hql).setParameter(0, ID);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息删除（隐藏）失败", e);
		}
	}

	// 删除（直接删除）
	public void deleteById(int ID) {
		// TODO Auto-generated method stub
		try {
			String hql = "delete from Provincial_Number where proid=?";
			getSession().createQuery(hql).setParameter(0, ID).executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息删除（直接）失败", e);
		}
	}

	// 修改
	public void update(Provincial_Number Pro) {
		// TODO Auto-generated method stub
		try {
			getSession().update(Pro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息更新失败", e);
		}
	}

	// 恢复（隐藏式删除的反向）
	public void regainByDelete(int id) {
		try {
			String hql = "update Provincial_Number p set p.usable = 1 where proID = ?";
			Query query = getSession().createQuery(hql).setParameter(0, id);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息隐藏后恢复失败", e);
		}
	}
}

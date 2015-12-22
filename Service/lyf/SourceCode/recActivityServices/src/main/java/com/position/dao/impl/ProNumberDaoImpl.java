package com.position.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.position.dao.ProNumberDao;
import com.position.pojo.ProvincialNumber;

/**
 * Classname:ProNumberDaoImpl
 *
 * Version information:具体实现省表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
@Repository
@Transactional
public class ProNumberDaoImpl implements ProNumberDao {

	private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);
	@Resource
	private SessionFactory sessionFactory;
	
	private ProvincialNumber pro;
	private List<ProvincialNumber> list;

	// 根据id
	public ProvincialNumber getById(int id) {
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			pro = (ProvincialNumber) this.getSession().get(ProvincialNumber.class, id);
			return pro;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息获取失败", e);
			return null;
		}
	}

	// 根据名
	public ProvincialNumber getByName(String name) {
		try {
			if (name == null || name.trim().length() == 0) {
				logger.error("传入参数不能为空");
				return null;
			}
			String hql = "from ProvincialNumber p where p.provincial =? ";
			list =  this.getSession().createQuery(hql).setString(0, name).list();
			pro = list.get(0);
			return pro;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息获取失败", e);
			return null;
		}
	}

	// 查找所有
	public List<ProvincialNumber> getAll() {
		try {
			list = this.getSession().createQuery("from ProvincialNumber").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息获取失败", e);
			return null;
		}
	}

	// 增加
	public void add(ProvincialNumber Pro) {
		try {
			this.getSession().save(Pro);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息存储失败", e);
		}
	}

	// 删除(隐藏式)
	public void deleteByIdHid(int ID) {
		try {
			String hql = "update ProvincialNumber p set p.usable = 0 where proID = ?";
			this.getSession().createQuery(hql).setParameter(0, ID).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息删除（隐藏）失败", e);
		}
	}

	// 删除（直接删除）
	public void deleteById(int ID) {
		try {
			String hql = "delete from ProvincialNumber where proid=?";
			this.getSession().createQuery(hql).setParameter(0, ID).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息删除（直接）失败", e);
		}
	}

	// 修改
	public void update(ProvincialNumber Pro) {
		try {
			this.getSession().update(Pro);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息更新失败", e);
		}
	}

	// 恢复（隐藏式删除的反向）
	public void regainByDelete(int id) {
		try {
			String hql = "update ProvincialNumber p set p.usable = 1 where proID = ?";
			this.getSession().createQuery(hql).setParameter(0, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息隐藏后恢复失败", e);
		}
	}
	
	/***********************get/set方法******************************/

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}

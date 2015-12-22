package com.position.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.position.dao.CompanyDao;
import com.position.pojo.Company;

/**
 * Classname:CompanyDaoImpl
 *
 * Version information:具体实现招聘公司表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {

	private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);
	@Resource
	private SessionFactory sessionFactory;
	
	private Company comp;
	private List<Company> list;

	//根据id
	public Company getById(int id) {
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			comp = (Company) this.getSession().get(Company.class, id);
			return comp;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		}
	}

	// 根据公司名
	public Company getByName(String name) {
		try {
			if (name == null || name.trim().length() == 0) {
				logger.error("传入参数不能为空");
				return null;
			}
			System.out.println(name);
			String hql = "from Company c where c.companyName =? ";
			list =  this.getSession().createQuery(hql).setString(0, name).list();
			//list = (List<Company>) session.createSQLQuery("select * from Company c where c.companyName ='测试数据1'").list();
			comp=list.get(0);
			return comp;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		} 
	}

	//查找所有
	public List<Company> getAll() {
		try {
			list = this.getSession().createQuery("from Company").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		}
	}

	//增加
	public void add(Company company) {
		try {
			this.getSession().save(company);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息存储失败", e);
		} 
	}

	//删除(隐藏式)
	public void deleteByIdHid(int ID) {
		try {
			String hql = "update Company c set c.usable = 0 where id = ?";
			this.getSession().createQuery(hql).setParameter(0, ID).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息删除（隐藏）失败", e);
		}
	}

	//删除（直接删除）
	public void deleteById(int ID) {
		try {
			String hql = "delete from Company where id=?";
			this.getSession().createQuery(hql).setParameter(0, ID).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息删除（直接）失败", e);
		} 
	}

	//修改
	public void update(Company company) {
		// TODO Auto-generated method stub
		try {
			this.getSession().update(company);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息更新失败", e);
		} 
	}

	// 恢复（隐藏式删除的反向）
	public void regainByDelete(int id) {
		try {
			String hql = "update Company c set c.usable = 1 where id = ?";
			Query query = this.getSession().createQuery(hql).setParameter(0, id);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息隐藏后恢复失败", e);
		} 
	}

	/***********************get/set方法******************************/
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}

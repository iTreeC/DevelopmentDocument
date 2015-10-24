package com.position.dao.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.position.dao.CompanyDao;
import com.position.pojo.Company;
import com.position.utils.SessionUtils;

/**
 * Classname:CompanyDaoImpl
 *
 * Version information:具体实现招聘公司表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
public class CompanyDaoImpl implements CompanyDao {

	private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);
	private Session session;
	private Transaction transaction;
	private Company comp;
	private List<Company> list;

	//根据id
	public Company getById(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			comp = (Company) session.get(Company.class, id);
			transaction.commit();
			return comp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		}
	}

	// 根据公司名
	public Company getByName(String name) {
		// TODO Auto-generated method stub
		try {
			if (name == null || name.trim().length() == 0) {
				logger.error("传入参数不能为空");
				return null;
			}
			System.out.println(name);
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "from Company c where c.companyName =? ";
			list =  session.createQuery(hql).setString(0, name).list();
			//list = (List<Company>) session.createSQLQuery("select * from Company c where c.companyName ='测试数据1'").list();
			comp=list.get(0);
			transaction.commit();
			return comp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		} 
	}

	//查找所有
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			list =session.createQuery("from Company").list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		}
	}

	//增加
	public void add(Company company) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.save(company);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息存储失败", e);
			session.getTransaction().rollback();
		} 
	}

	//删除(隐藏式)
	public void deleteByIdHid(int ID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update Company c set c.usable = 0 where id = ?";
			Query query = session.createQuery(hql).setParameter(0, ID);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息删除（隐藏）失败", e);
			session.getTransaction().rollback();
		}
	}

	//删除（直接删除）
	public void deleteById(int ID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "delete from Company where id=?";
			session.createQuery(hql).setParameter(0, ID).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息删除（直接）失败", e);
			session.getTransaction().rollback();
		} 
	}

	//修改
	public void update(Company company) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.update(company);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息更新失败", e);
			session.getTransaction().rollback();
		} 
	}

	// 恢复（隐藏式删除的反向）
	public void regainByDelete(int id) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update Company c set c.usable = 1 where id = ?";
			Query query = session.createQuery(hql).setParameter(0, id);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "招聘公司信息隐藏后恢复失败", e);
			session.getTransaction().rollback();
		} 
	}

}

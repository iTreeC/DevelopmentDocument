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
	@SuppressWarnings("unused")
	private Transaction transaction;
	private Company comp;
	private List<Company> list;

	/*
	 * 根据id
	 * 
	 * @see com.position.dao.CompanyDao#getById(int)
	 */
	public Company getById(int id) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(id);
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

	/*
	 * 根据公司名
	 * 
	 * @see com.position.dao.CompanyDao#getByName(java.lang.String)
	 */
	public Company getByName(String name) {
		// TODO Auto-generated method stub
		try {
			System.out.println(name);
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "from Company c where c.companyName =? ";
			list = (List<Company>) session.createQuery(hql).setString(0, name).list();
			//list = (List<Company>) session.createSQLQuery("select * from Company c where c.companyName ='测试数据1'").list();
				comp=(Company)list.get(0);
				transaction.commit();
			return comp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		} 
	}

	/*
	 * 查找所有
	 * 
	 * @see com.position.dao.CompanyDao#getAll()
	 */
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

	/*
	 * 增加
	 * 
	 * @see com.position.dao.CompanyDao#add(com.position.pojo.Company)
	 */
	public void add(Company company) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.save(company);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息存储失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/*
	 * 删除(隐藏式)
	 * 
	 * @see com.position.dao.CompanyDao#deleteByIdHid(int)
	 */
	public void deleteByIdHid(int ID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update Company c set c.usable = 0 where id = ?";
			Query query = session.createQuery(hql).setParameter(0, ID);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息删除（隐藏）失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/*
	 * 删除（直接删除）
	 * 
	 * @see com.position.dao.CompanyDao#deleteById(int)
	 */
	public void deleteById(int ID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			comp = (Company) session.get(Company.class, ID);
			session.delete(comp);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息删除（直接）失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/*
	 * 修改
	 * 
	 * @see com.position.dao.CompanyDao#update(com.position.pojo.Company)
	 */
	public void update(Company company) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.update(company);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息更新失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/*
	 * 恢复（隐藏式删除的反向）
	 * 
	 * @see com.position.dao.CompanyDao#regainByDelete(int)
	 */
	public void regainByDelete(int id) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update Company c set c.usable = 1 where id = ?";
			Query query = session.createQuery(hql).setParameter(0, id);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息隐藏后恢复失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

}

package com.position.dao.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.position.dao.CityNumberDao;
import com.position.pojo.City_Number;
import com.position.pojo.Provincial_Number;
import com.position.utils.SessionUtils;

/**
 * Classname:CityNumberDaoImpl
 *
 * Version information:具体实现城市表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
public class CityNumberDaoImpl implements CityNumberDao {

	private static Logger logger = Logger.getLogger(CityNumberDaoImpl.class);
	private Session session;
	private Transaction transaction;
	private City_Number city;
	private List<City_Number> list;

	// 根据id
	public City_Number getById(int cityid) {
		// TODO Auto-generated method stub
		try {
			if (cityid < 0 || cityid > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			city = (City_Number) session.get(City_Number.class, cityid);
			transaction.commit();
			return city;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息获取失败", e);
			return null;
		}
	}

	// 根据城市名
	public City_Number getByName(String name) {
		// TODO Auto-generated method stub
		try {
			if(name == null || name.trim().length() == 0){
				logger.error("传入参数不能为空");
				return null;
			}
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "from City_Number c where c.city =? ";
			list = session.createQuery(hql).setString(0, name).list();
			city = list.get(0);
			transaction.commit();
			return city;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息获取失败", e);
			return null;
		}
	}

	// 根据父类名字
	public List<City_Number> getByParentId(int parentCity) {
		// TODO Auto-generated method stub
		// 可从ProNumberDaoImpl类里的getByName得到父类后，关联得到
		return null;
	}

	// 查找所有
	public List<City_Number> getAll() {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			list = session.createQuery("from City_Number").list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息获取失败", e);
			return null;
		}
	}

	// 增加(一个参数)
	public void add(City_Number city) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.save(city);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息存储失败", e);
			session.getTransaction().rollback();
		}
	}

	// 增加(两个参数)
	public void add2(City_Number city, Provincial_Number pro) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			city.setPro(pro);
			session.save(city);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息存储失败", e);
			session.getTransaction().rollback();
		}
	}

	// 删除(隐藏式)
	public void deleteByIdHid(int cityID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update City_Number c set c.usable = 0 where cityID = ?";
			Query query = session.createQuery(hql).setParameter(0, cityID);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "城市信息删除（隐藏）失败", e);
			session.getTransaction().rollback();
		}
	}

	// 删除（直接删除）
	public void deleteById(int cityID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "delete from City_Number where cityid=?";
			session.createQuery(hql).setParameter(0, cityID).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			logger.log(Level.ALL, "城市信息删除失败", e);
			session.getTransaction().rollback();
		}
	}

	// 修改
	public void update(City_Number city) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.update(city);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "城市信息更新失败", e);
			session.getTransaction().rollback();
		}
	}

	// 恢复（隐藏式删除的反向）
	public void regainByDelete(int cityid) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update City_Number c set c.usable = 1 where cityID = ?";
			Query query = session.createQuery(hql).setParameter(0, cityid);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息隐藏后恢复失败", e);
			session.getTransaction().rollback();
		}
	}

}

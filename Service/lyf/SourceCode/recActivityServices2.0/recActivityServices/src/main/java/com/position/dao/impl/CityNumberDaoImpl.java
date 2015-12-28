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

import com.position.dao.CityNumberDao;
import com.position.dao.ProNumberDao;
import com.position.pojo.CityNumber;
import com.position.pojo.ProvincialNumber;

/**
 * Classname:CityNumberDaoImpl
 *
 * Version information:具体实现城市表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
@Repository
@Transactional
public class CityNumberDaoImpl implements CityNumberDao {
	private static Logger logger = Logger.getLogger(CityNumberDaoImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private ProNumberDao proNumberDaoImpl;
	
	private CityNumber city;
	private List<CityNumber> list;

	// 根据id
	public CityNumber getById(int cityid) {
		 
		try {
			if (cityid < 0 || cityid > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			city = (CityNumber) this.getSession().get(CityNumber.class, cityid);
			return city;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息获取失败", e);
			return null;
		}
	}

	// 根据城市名
	public CityNumber getByName(String name) {
		 
		try {
			if(name == null || name.trim().length() == 0){
				logger.error("传入参数不能为空");
				return null;
			}
			String hql = "from CityNumber c where c.city =? ";
			list = this.getSession().createQuery(hql).setString(0, name).list();
			city = list.get(0);
			return city;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息获取失败", e);
			return null;
		}
	}
	// 根据父类名字
		public List<CityNumber> getByParentName(String parentCity){
			 
			// 可从ProNumberDaoImpl类里的getByName得到父类后，关联得到
			list = (List<CityNumber>) proNumberDaoImpl.getByName(parentCity).getCities();
			return list;
		}

	// 根据父类id
	public List<CityNumber> getByParentId(int parentCity) {
		 
		// 可从ProNumberDaoImpl类里的getById得到父类后，关联得到
		list = (List<CityNumber>)proNumberDaoImpl.getById(parentCity).getCities();
		return list;
	}

	// 查找所有
	public List<CityNumber> getAll() {
		 
		try {
			list = this.getSession().createQuery("from CityNumber").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息获取失败", e);
			return null;
		}
	}

	// 增加(一个参数)
	public void add(CityNumber city) {
		 
		try {
			this.getSession().save(city);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息存储失败", e);
		}
	}

	// 增加(两个参数)
	public void addtwo(CityNumber city, ProvincialNumber pro) {
		 
		try {
			city.setPro(pro);
			this.getSession().save(city);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息存储失败", e);
		}
	}

	// 删除(隐藏式)
	public void deleteByIdHid(int cityID) {
		 
		try {
			String hql = "update CityNumber c set c.usable = 0 where cityID = ?";
			Query query = this.getSession().createQuery(hql).setParameter(0, cityID);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息删除（隐藏）失败", e);
		}
	}

	// 删除（直接删除）
	public void deleteById(int cityID) {
		 
		try {
			String hql = "delete from CityNumber where cityid=?";
			this.getSession().createQuery(hql).setParameter(0, cityID).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息删除失败", e);
		}
	}

	// 修改
	public void update(CityNumber city) {

		try {
			this.getSession().update(city);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息更新失败", e);
		}
	}

	// 恢复（隐藏式删除的反向）
	public void regainByDelete(int cityid) {
		try {
			String hql = "update CityNumber c set c.usable = 1 where cityID = ?";
			this.getSession().createQuery(hql).setParameter(0, cityid).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "省信息隐藏后恢复失败", e);
		}
	}
	
	/***********************get/set方法******************************/

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public ProNumberDao getProDao() {
		return proNumberDaoImpl;
	}

	public void setProDao(ProNumberDao proNumberDaoImpl) {
		this.proNumberDaoImpl = proNumberDaoImpl;
	}
}

package com.position.dao.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

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
@Repository("citynumberdao")
public class CityNumberDaoImpl extends SessionUtils implements CityNumberDao {

	private static Logger logger = Logger.getLogger(CityNumberDaoImpl.class);
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
			city = (City_Number) getSession().get(City_Number.class, cityid);
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
			String hql = "from City_Number c where c.city =? ";
			list = getSession().createQuery(hql).setString(0, name).list();
			city = list.get(0);
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
			list = getSession().createQuery("from City_Number").list();
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
			getSession().save(city);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息存储失败", e);
		}
	}

	// 增加(两个参数)
	public void add2(City_Number city, Provincial_Number pro) {
		// TODO Auto-generated method stub
		try {
			city.setPro(pro);
			getSession().save(city);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "城市信息存储失败", e);
		}
	}

	// 删除(隐藏式)
	public void deleteByIdHid(int cityID) {
		// TODO Auto-generated method stub
		try {
			String hql = "update City_Number c set c.usable = 0 where cityID = ?";
			Query query = getSession().createQuery(hql).setParameter(0, cityID);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "城市信息删除（隐藏）失败", e);
		}
	}

	// 删除（直接删除）
	public void deleteById(int cityID) {
		// TODO Auto-generated method stub
		try {
			String hql = "delete from City_Number where cityid=?";
			getSession().createQuery(hql).setParameter(0, cityID).executeUpdate();
		} catch (Exception e) {
			logger.log(Level.ALL, "城市信息删除失败", e);
		}
	}

	// 修改
	public void update(City_Number city) {
		// TODO Auto-generated method stub
		try {
			getSession().update(city);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "城市信息更新失败", e);
		}
	}

	// 恢复（隐藏式删除的反向）
	public void regainByDelete(int cityid) {
		try {
			String hql = "update City_Number c set c.usable = 1 where cityID = ?";
			Query query = getSession().createQuery(hql).setParameter(0, cityid);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(Level.ALL, "省信息隐藏后恢复失败", e);
		}
	}

}

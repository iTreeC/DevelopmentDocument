package com.position.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.position.dao.PositionDao;
import com.position.pojo.CompanyPosition;
import com.position.pojo.CityNumber;

/**
 * Classname:PositionDaoImpl
 *
 * Version information:具体实现位置表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
@Repository
@Transactional
public class PositionDaoImpl implements PositionDao {

	private static Logger logger = Logger.getLogger(PositionDaoImpl.class);
	@Resource
	private SessionFactory sessionFactory;
	
	private CompanyPosition pos;
	private List<CompanyPosition> list;
	
	//根据位置id
	public CompanyPosition getById(int id) {
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			pos = (CompanyPosition) this.getSession().get(CompanyPosition.class, id);
			return pos;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//根据公司id
	public CompanyPosition getByPosId(int id) {
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			pos = (CompanyPosition) this.getSession().get(CompanyPosition.class, id);
			return pos;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//查找所有
	public List<CompanyPosition> getAll() {
		try {
			list = this.getSession().createQuery("from CompanyPosition").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//根据详尽地址查找（模糊查询）
	public List<CompanyPosition> getByAddress(String str) {
		try {
			if (str == null || str.trim().length() == 0) {
				logger.error("传入参数不能为空");
				return null;
			}
			String hql = "from CompanyPosition b where b.address like"
					+"'"+"%"+str+"%"+"'";
			list =  this.getSession().createQuery(hql).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地址信息获取失败", e);
			return null;
		} 
	}

	//根据省查找
	public List<CompanyPosition> getByPro(int id) {
		//暂不开发
		return null;
	}

	//城市id
	public List<CompanyPosition> getByCity(int id) {
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			System.out.println("实现**************************"+id);
			System.out.println("实现2******************"+sessionFactory);
			this.getSession().beginTransaction();
			CityNumber city = (CityNumber) this.getSession().get(CityNumber.class, id);
			System.out.println("**************************"+city);
			Set<CompanyPosition> list = city.getPos();
			System.out.println("**************************"+list);
			List<CompanyPosition> list1 = new ArrayList<CompanyPosition>(list);
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//根据县查找
	public List<CompanyPosition> getByCount(int id) {
		// TODO Auto-generated method stub
		//暂时不开发
		return null;
	}

	//增加
	public void add(CompanyPosition Bus) {
		try {
			this.getSession().save(Bus);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息存储失败", e);
		} 
	}

	//删除(隐藏式)
	public void deleteByIdHid(int ID) {
		try {
			String hql = "update CompanyPosition b set b.usable = 0 where id = ?";
			this.getSession().createQuery(hql).setParameter(0, ID).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息删除（隐藏）失败", e);
		}
	}

	// 删除（直接删除）。注意该表不能直接删除，次方法仅为测试
	public void deleteById(int ID) {
		try {
			String hql = "delete from CompanyPosition where id=?";
			this.getSession().createQuery(hql).setParameter(0, ID).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息删除（直接）失败", e);
		}
	}

	//修改
	public void update(CompanyPosition Bus) {
		try {
			this.getSession().update(Bus);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息更新失败", e);
		} 
	}
	
	
	//恢复（隐藏式删除的反向）
	public void regainByDelete(int id){
		try {
			String hql = "update CompanyPosition b set b.usable = 1 where id = ?";
			this.getSession().createQuery(hql).setParameter(0, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息隐藏后恢复失败", e);
		} 
	}
	
	/***********************get/set方法******************************/

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}

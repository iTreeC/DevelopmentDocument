package com.position.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.position.dao.PositionDao;
import com.position.pojo.Business_Position;
import com.position.pojo.City_Number;
import com.position.utils.SessionUtils;

/**
 * Classname:PositionDaoImpl
 *
 * Version information:具体实现位置表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
@Repository("positiondao")
public class PositionDaoImpl extends SessionUtils implements PositionDao {

	private static Logger logger = Logger.getLogger(PositionDaoImpl.class);
	@Autowired(required=true)
	private Business_Position pos;
	private List<Business_Position> list;
	
	//根据位置id
	public Business_Position getById(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			pos = (Business_Position) getSession().get(Business_Position.class, id);
			return pos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//根据公司id
	public Business_Position getByPosId(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			pos = (Business_Position) getSession().get(Business_Position.class, id);
			return pos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//查找所有
	public List<Business_Position> getAll() {
		// TODO Auto-generated method stub
		try {
			list =getSession().createQuery("from Business_Position").list();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//根据详尽地址查找（模糊查询）
	public List<Business_Position> getByAddress(String str) {
		// TODO Auto-generated method stub
		try {
			if (str == null || str.trim().length() == 0) {
				logger.error("传入参数不能为空");
				return null;
			}
			String hql = "from Business_Position b where b.address like"
					+"'"+"%"+str+"%"+"'";
			list =  getSession().createQuery(hql).list();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地址信息获取失败", e);
			return null;
		} 
	}

	//根据省查找
	public List<Business_Position> getByPro(int id) {
		// TODO Auto-generated method stub
		//暂不开发，资源不足
		return null;
	}

	//城市id
	public List<Business_Position> getByCity(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			City_Number city = (City_Number) getSession().get(City_Number.class, id);
			Set<Business_Position> list = city.getPos();
			List<Business_Position> list1 = new ArrayList<Business_Position>(list);
			return list1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//根据县查找
	public List<Business_Position> getByCount(int id) {
		// TODO Auto-generated method stub
		//暂时不开发
		return null;
	}

	//增加
	public void add(Business_Position Bus) {
		// TODO Auto-generated method stub
		try {
			getSession().save(Bus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息存储失败", e);
		} 
	}

	//删除(隐藏式)
	public void deleteByIdHid(int ID) {
		// TODO Auto-generated method stub
		try {
			String hql = "update Business_Position b set b.usable = 0 where id = ?";
			Query query = getSession().createQuery(hql).setParameter(0, ID);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息删除（隐藏）失败", e);
		}
	}

	// 删除（直接删除）。注意该表不能直接删除，次方法仅为测试
	public void deleteById(int ID) {
		// TODO Auto-generated method stub
		try {
			String hql = "delete from Business_Position where id=?";
			getSession().createQuery(hql).setParameter(0, ID).executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息删除（直接）失败", e);
		}
	}

	//修改
	public void update(Business_Position Bus) {
		// TODO Auto-generated method stub
		try {
			getSession().update(Bus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息更新失败", e);
		} 
	}
	
	
	//恢复（隐藏式删除的反向）
	public void regainByDelete(int id){
		try {
			String hql = "update Business_Position b set b.usable = 1 where id = ?";
			Query query = getSession().createQuery(hql).setParameter(0, id);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息隐藏后恢复失败", e);
		} 
	}
}

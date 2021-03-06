package com.position.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.position.dao.PositionDao;
import com.position.pojo.CompanyPosition;
import com.position.pojo.CityNumber;
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
public class PositionDaoImpl implements PositionDao {

	private static Logger logger = Logger.getLogger(PositionDaoImpl.class);
	private Session session;
	private Transaction transaction;
	private CompanyPosition pos;
	private List<CompanyPosition> list;
	
	//根据位置id
	public CompanyPosition getById(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			pos = (CompanyPosition) session.get(CompanyPosition.class, id);
			transaction.commit();
			return pos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//根据公司id
	public CompanyPosition getByPosId(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			pos = (CompanyPosition) session.get(CompanyPosition.class, id);
			transaction.commit();
			return pos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//查找所有
	public List<CompanyPosition> getAll() {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			list =session.createQuery("from CompanyPosition").list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	//根据详尽地址查找（模糊查询）
	public List<CompanyPosition> getByAddress(String str) {
		// TODO Auto-generated method stub
		try {
			if (str == null || str.trim().length() == 0) {
				logger.error("传入参数不能为空");
				return null;
			}
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "from CompanyPosition b where b.address like"
					+"'"+"%"+str+"%"+"'";
			list =  session.createQuery(hql).list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地址信息获取失败", e);
			return null;
		} 
	}

	//根据省查找
	public List<CompanyPosition> getByPro(int id) {
		// TODO Auto-generated method stub
		//暂不开发，资源不足
		return null;
	}

	//城市id
	public List<CompanyPosition> getByCity(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			CityNumber city = (CityNumber) session.get(CityNumber.class, id);
			Set<CompanyPosition> list = city.getPos();
			List<CompanyPosition> list1 = new ArrayList<CompanyPosition>(list);
			transaction.commit();
			return list1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.save(Bus);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息存储失败", e);
			session.getTransaction().rollback();
		} 
	}

	//删除(隐藏式)
	public void deleteByIdHid(int ID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update CompanyPosition b set b.usable = 0 where id = ?";
			Query query = session.createQuery(hql).setParameter(0, ID);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息删除（隐藏）失败", e);
			session.getTransaction().rollback();
		}
	}

	// 删除（直接删除）。注意该表不能直接删除，次方法仅为测试
	public void deleteById(int ID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "delete from CompanyPosition where id=?";
			session.createQuery(hql).setParameter(0, ID).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息删除（直接）失败", e);
			session.getTransaction().rollback();
		}
	}

	//修改
	public void update(CompanyPosition Bus) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.update(Bus);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息更新失败", e);
			session.getTransaction().rollback();
		} 
	}
	
	
	//恢复（隐藏式删除的反向）
	public void regainByDelete(int id){
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update CompanyPosition b set b.usable = 1 where id = ?";
			Query query = session.createQuery(hql).setParameter(0, id);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息隐藏后恢复失败", e);
			session.getTransaction().rollback();
		} 
	}
}

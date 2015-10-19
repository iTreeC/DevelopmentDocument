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
import com.position.pojo.Business_Position;
import com.position.pojo.City_Number;
import com.position.utils.SessionUtils;

public class PositionDaoImpl implements PositionDao {

	private static Logger logger = Logger.getLogger(PositionDaoImpl.class);
	private Session session;
	private Transaction transaction;
	private Business_Position pos;
	private List<Business_Position> list;
	
	//根据id
	public Business_Position getById(int id) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(id);
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			pos = (Business_Position) session.get(Business_Position.class, id);
			transaction.commit();
			return pos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	/**
	 * 根据公司id
	 */
	public Business_Position getByPosId(int id) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(id);
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			pos = (Business_Position) session.get(Business_Position.class, id);
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
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			list =session.createQuery("from Business_Position").list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	/*
	 * 根据详尽地址查找（模糊查询）
	 * @see com.position.dao.PositionDao#getByAddress(java.lang.String)
	 */
	public List<Business_Position> getByAddress(String str) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(str);
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "from Business_Position b where b.address like"
					+"%"+str+"%";
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

	/*
	 * 根据省查找
	 * @see com.position.dao.PositionDao#getByPro(int)
	 */
	public List<Business_Position> getByPro(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 城市id
	 */
	public List<Business_Position> getByCity(int id) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(id);
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
//			String hql = "from Business_Position b where b.cityid =? ";
//			list = (List<Business_Position>) session.createQuery(hql).setParameter(0, id).list();			
			City_Number city = (City_Number) session.get(City_Number.class, id);
			Set<Business_Position> list = city.getPos();
			List<Business_Position> list1 = new ArrayList<Business_Position>(list);
			transaction.commit();
			return list1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息获取失败", e);
			return null;
		}
	}

	/*
	 * 根据县查找
	 * @see com.position.dao.PositionDao#getByCount(int)
	 */
	public List<Business_Position> getByCount(int id) {
		// TODO Auto-generated method stub
		//暂时不开发
		return null;
	}

	/*
	 * 增加
	 * @see com.position.dao.PositionDao#add(com.position.pojo.Business_Position)
	 */
	public void add(Business_Position Bus) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.save(Bus);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息存储失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/*
	 * 删除(隐藏式)
	 * @see com.position.dao.PositionDao#deleteByIdHid(int)
	 */
	public void deleteByIdHid(int ID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update Business_Position b set b.usable = 0 where id = ?";
			Query query = session.createQuery(hql).setParameter(0, ID);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息删除（隐藏）失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/*
	 * 删除（直接删除）
	 * @see com.position.dao.PositionDao#deleteById(int)
	 */
	public void deleteById(int ID) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			pos = (Business_Position) session.get(Business_Position.class, ID);
			session.delete(pos);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息删除（直接）失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	/*
	 * 修改
	 * @see com.position.dao.PositionDao#update(com.position.pojo.Business_Position)
	 */
	public void update(Business_Position Bus) {
		// TODO Auto-generated method stub
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			session.update(Bus);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息更新失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
	
	
	/*
	 * 恢复（隐藏式删除的反向）
	 * @see com.position.dao.PositionDao#regainByDelete(int)
	 */
	public void regainByDelete(int id){
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			String hql = "update Business_Position b set b.usable = 1 where id = ?";
			Query query = session.createQuery(hql).setParameter(0, id);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "地点信息隐藏后恢复失败", e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
}

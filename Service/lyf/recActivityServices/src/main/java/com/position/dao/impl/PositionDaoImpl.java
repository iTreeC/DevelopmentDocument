package com.position.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.position.dao.PositionDao;
import com.position.pojo.Business_Position;
import com.position.pojo.City_Number;
import com.position.pojo.Company;
import com.position.utils.SessionUtils;

public class PositionDaoImpl implements PositionDao {

	private static Logger logger = Logger.getLogger(PositionDaoImpl.class);
	private Session session;
	@SuppressWarnings("unused")
	private Transaction transaction;
	private Business_Position pos;
	private List<Business_Position> list;
	
	//根据id
	public Business_Position getById(int id) {
		// TODO Auto-generated method stub
		//暂时用不到
		return null;
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
		return null;
	}

	public List<Business_Position> getByAddress(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Business_Position> getByPro(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
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

	public List<Business_Position> getByCount(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(Business_Position Bus) {
		// TODO Auto-generated method stub

	}

	public void deleteByIdHid(int ID) {
		// TODO Auto-generated method stub

	}

	public void deleteById(int ID) {
		// TODO Auto-generated method stub

	}

	public void update(Business_Position Bus) {
		// TODO Auto-generated method stub

	}
	
	
	/*
	 * 恢复（隐藏式删除的反向）
	 * @see com.position.dao.PositionDao#regainByDelete(int)
	 */
	public void regainByDelete(int id){
		
	}

}

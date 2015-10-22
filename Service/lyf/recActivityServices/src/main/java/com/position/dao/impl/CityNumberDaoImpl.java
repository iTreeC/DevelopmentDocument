package com.position.dao.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.position.dao.CityNumberDao;
import com.position.pojo.City_Number;
import com.position.pojo.Company;
import com.position.utils.SessionUtils;

public class CityNumberDaoImpl implements CityNumberDao {

	private static Logger logger = Logger.getLogger(CityNumberDaoImpl.class);
	private Session session;
	private Transaction transaction;
	private City_Number city;
	private List<City_Number> list;
	
	
	public City_Number getById(int cityid) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(id);
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			city = (City_Number) session.get(City_Number.class, cityid);
			transaction.commit();
			return city;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		}
	}

	public City_Number getByName(String parentCity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<City_Number> getByParentId(int parentCity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<City_Number> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(City_Number city) {
		// TODO Auto-generated method stub

	}

	public void deleteByIdHid(int cityID) {
		// TODO Auto-generated method stub

	}

	public void deleteById(int cityID) {
		// TODO Auto-generated method stub

	}

	public void update(City_Number city) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * 恢复（隐藏式删除的反向）
	 * @see com.position.dao.CityNumberDao#regainByDelete(int)
	 */
	public void regainByDelete(int cityid){
		
	}

}

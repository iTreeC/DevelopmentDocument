package com.position.dao.impl;

import java.util.List;

import com.position.dao.CityNumberDao;
import com.position.pojo.City_Number;

public class CityNumberDaoImpl implements CityNumberDao {

	public City_Number getById(int cityid) {
		// TODO Auto-generated method stub
		return null;
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

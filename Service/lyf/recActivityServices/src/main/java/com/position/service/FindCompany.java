package com.position.service;

import java.util.ArrayList;
import java.util.List;

import com.position.dao.PositionDao;
import com.position.dao.impl.CompanyDaoImpl;
import com.position.dao.impl.PositionDaoImpl;
import com.position.pojo.Business_Position;
import com.position.pojo.Company;


public class FindCompany {

	List<Business_Position> list;
	List<Company> listcom;
	CompanyDaoImpl comp;
	Company com;

	public List<Company> FindCompanyByCityId(int cityId) {

		PositionDao pos = new PositionDaoImpl();
		list = pos.getByCity(cityId);
		comp = new CompanyDaoImpl();
		listcom = new ArrayList<Company>();
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				//com= new Company();
				int a = list.get(i).getCompanyID();
				//System.out.println(a);			
				com = comp.getById(a);
				//System.out.println(a);
				listcom.add(i, com);
			}
		} else {
			System.out.println("查找失败");
		}
		return listcom;
	}
}

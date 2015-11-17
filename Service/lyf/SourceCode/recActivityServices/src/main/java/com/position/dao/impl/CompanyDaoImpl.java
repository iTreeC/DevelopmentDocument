package com.position.dao.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.position.dao.CompanyDao;
import com.position.pojo.Company;
import com.position.utils.SessionUtils;

/**
 * Classname:CompanyDaoImpl
 *
 * Version information:具体实现招聘公司表增删改查操作
 *
 * Date：2015-10-17
 *
 * Copyright notice：liangyanfei
 */
@Repository("companydao")
public class CompanyDaoImpl extends SessionUtils implements CompanyDao  {

	private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);

	private Company comp;
	private List<Company> list;

	//根据id
	public Company getById(int id) {
		// TODO Auto-generated method stub
		try {
			if (id < 0 || id > 65535) {
				logger.error("传入参数超出范围");
				return null;
			}
			comp = (Company) getSession().get(Company.class, id);
			return comp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.ALL, "招聘公司信息获取失败", e);
			return null;
		}
	}

	// 根据公司名
	public Company getByName(String name) {
		return comp;
		
	}

	//查找所有
	public List<Company> getAll() {
		return list;
		
	}

	//增加
	public void add(Company company) {
		
	}

	//删除(隐藏式)
	public void deleteByIdHid(int ID) {
		
	}

	//删除（直接删除）
	public void deleteById(int ID) {
		
	}

	//修改
	public void update(Company company) {
		
	}

	// 恢复（隐藏式删除的反向）
	public void regainByDelete(int id) {
		
	}
		
}

package com.itree.dao.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itree.dao.api.PermissionDao;
import com.itree.entity.Perm;

public class PermissionDaoImplTest {

	PermissionDao pdao  = new PermissionDaoImpl();

	Perm perm; 
	int id;
	String name;
	
	@Test
	public void testAdd() {
		perm = new Perm();
		perm.setName("1");
		pdao.add(perm);
	}

	@Test
	public void testAdd2() {
		pdao.add(perm);
	}
	
	@Test
	public void testDeleteByRId() {
		pdao.deleteByRId(id);
	}

	@Test
	public void testDeleteByRId2() {
		id=1;
		pdao.deleteByRId(id);
	}
	
	@Test
	public void testUpdate() {
		perm = new Perm();
		perm.setId(1);
		perm.setName("a");
		pdao.update(perm);
	}

	@Test
	public void testUpdate2() {
		pdao.update(perm);
	}
	
	@Test
	public void testGetAll() {
		pdao.getAll();
	}
	
	@Test
	public void testGetAllName() {
		pdao.getAllName();
	}
	
	@Test
	public void testGetOneById() {
		pdao.getOneById(id);
	}

	@Test
	public void testGetOneById2() {
		id =1;
		pdao.getOneById(id);
	}
	
	@Test
	public void testGetOneByName() {
		name="a";
		pdao.getOneByName(name);
	}

	@Test
	public void testGetOneByName2() {
		name=null;
		pdao.getOneByName(name);
	}
	
	
	

}

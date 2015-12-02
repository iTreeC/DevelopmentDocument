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

	PermissionDao pdao = new PermissionDaoImpl();

	Perm perm;
	int id;
	String name;

	@Test
	public void testAdd() {
		perm = new Perm();
		perm.setClientPermissionID(1);
		pdao.add(perm);
	}

	@Test
	public void testAdd2() {
		pdao.add(perm);
	}

	@Test
	public void testDeleteByRId() {
		pdao.deleteByID(id);
	}

	@Test
	public void testDeleteByRId2() {
		id = 1;
		pdao.deleteByID(id);
	}

	@Test
	public void testUpdate() {
		perm = new Perm();
		perm.setId(1);
		perm.setClientPermissionID(1);
		pdao.update(perm);
	}

	@Test
	public void testUpdate2() {
		pdao.update(perm);
	}

	@Test
	public void testGetOneById() {
		pdao.getOneByID(id);
	}

	@Test
	public void testGetOneById2() {
		id = 1;
		pdao.getOneByID(id);
	}

	@Test
	public void testGetOneByClientID() {
		id = 1;
		pdao.getOneByClientID(id);
	}

	@Test
	public void testGetOneByClientID2() {
		id = 0;
		pdao.getOneByClientID(id);
	}

}

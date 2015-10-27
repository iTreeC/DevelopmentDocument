package com.itree.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itree.dao.api.PermissionDao;
import com.itree.entity.Perm;

public class PermissionDaoImplTest {

	PermissionDao pdao = new PermissionDaoImpl();

	@Test
	public void testAdd() {
		Perm perm = new Perm();
		perm.setName("1");
		pdao.add(perm);
	}

	@Test
	public void testAdd2() {
		Perm perm = new Perm();
		pdao.add(perm);
	}
	
	@Test
	public void testDeleteByRId() {
		pdao.deleteByRId(1);
	}

	@Test
	public void testDeleteByRId2() {
		int i = 0;
		pdao.deleteByRId(i);
	}
	
	@Test
	public void testUpdate() {
		Perm perm = new Perm();
		perm.setId(1);
		perm.setName("a");
		pdao.update(perm);
	}

	@Test
	public void testUpdate2() {
		Perm perm = null;
		pdao.update(perm);
	}
	

}

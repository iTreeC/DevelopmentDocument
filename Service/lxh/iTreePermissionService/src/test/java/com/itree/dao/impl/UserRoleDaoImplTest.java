package com.itree.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.itree.dao.api.UserRoleDao;

public class UserRoleDaoImplTest {

	UserRoleDao urdao = new UserRoleDaoImpl();

	int uid;
	List<Integer> rid;
		
	@Test
	public void testAdd() {
		urdao.add(uid, rid);
	}
	@Test
	public void testAdd2() {
		uid = 1;
		rid = new ArrayList<Integer>();
		rid.add(1);
		urdao.add(uid, rid);
	}
	@Test
	public void testDeleteByUId() {
		urdao.deleteByUId(uid);
	}
	@Test
	public void testDeleteByUId2() {
		uid=1;
		urdao.deleteByUId(uid);
	}
	
	
	@Test
	public void testUpdate() {
		urdao.update(uid, rid);
	}
	@Test
	public void testUpdate2() {
		uid = 1;
		rid = new ArrayList<Integer>();
		rid.add(1);
		urdao.update(uid, rid);
	}
	@Test
	public void testFindAll() {
		urdao.findAll();
	}
	@Test
	public void testFindByUId() {
		urdao.findByUId(uid);
	}
	
	@Test
	public void testFindByUId2() {
		uid=1;
		urdao.findByUId(uid);
	}
	@Test
	public void testFindUserRIds() {
		urdao.findUserRIds(uid);
	}
	@Test
	public void testFindUserRIds2() {
		uid=2;
		urdao.findUserRIds(uid);
	}

}

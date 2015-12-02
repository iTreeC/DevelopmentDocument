package com.itree.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
		urdao.deleteByUserID(uid);
	}

	@Test
	public void testDeleteByUId2() {
		uid = 1;
		urdao.deleteByUserID(uid);
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
	public void testFindByUId() {
		urdao.findListByUserID(uid);
	}

	@Test
	public void testFindByUId2() {
		uid = 1;
		urdao.findListByUserID(uid);
	}

	@Test
	public void testFindUserRIds() {
		urdao.findRoleIDByUserID(uid);
	}

	@Test
	public void testFindUserRIds2() {
		uid = 2;
		urdao.findRoleIDByUserID(uid);
	}

}

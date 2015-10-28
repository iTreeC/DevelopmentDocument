package com.itree.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.itree.dao.api.UserPermissionDao;

public class UserPermissionDaoImplTest {

	UserPermissionDao udao = new UserPermissionDaoImpl();

	int uid;
	List<Integer> pid;

	@Test
	public void testAdd() {
		udao.add(uid, pid);
	}

	@Test
	public void testAdd2() {
		uid = 1;
		pid = new ArrayList<Integer>();
		pid.add(1);
		udao.add(uid, pid);
	}

	@Test
	public void testDeleteByUId() {
		udao.deleteByUId(uid);

	}

	@Test
	public void testDeleteByUId2() {
		udao.deleteByUId(uid);
		uid = 1;
		udao.deleteByUId(uid);

	}

	@Test
	public void testUpdatePermission() {
		udao.updatePermission(uid, pid);
	}

	@Test
	public void testUpdatePermission2() {
		uid = 1;
		pid = new ArrayList<Integer>();
		pid.add(1);
		udao.updatePermission(uid, pid);
	}

	@Test
	public void testFindAll() {
		udao.findAll();
	}

	@Test
	public void testFindByUId() {
		udao.findByUId(uid);
	}

	@Test
	public void testFindByUId2() {
		uid = 1;
		udao.findByUId(uid);
	}

	@Test
	public void testFindUserPIds() {
		udao.findUserPIds(uid);
	}

	@Test
	public void testFindUserPIds2() {
		uid = 1;
		udao.findUserPIds(uid);
	}
}

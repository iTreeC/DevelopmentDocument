package com.itree.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		udao.deleteByUserID(uid);

	}

	@Test
	public void testDeleteByUId2() {
		udao.deleteByUserID(uid);
		uid = 1;
		udao.deleteByUserID(uid);

	}

	@Test
	public void testUpdatePermission() {
		udao.update(uid, pid);
	}

	@Test
	public void testUpdatePermission2() {
		uid = 1;
		pid = new ArrayList<Integer>();
		pid.add(1);
		udao.update(uid, pid);
	}

	@Test
	public void testFindByUId() {
		udao.findListByUserID(uid);
	}

	@Test
	public void testFindByUId2() {
		uid = 1;
		udao.findListByUserID(uid);
	}

	@Test
	public void testFindUserPIds() {
		udao.findUserPermissionID(uid);
	}

	@Test
	public void testFindUserPIds2() {
		uid = 1;
		udao.findUserPermissionID(uid);
	}
}

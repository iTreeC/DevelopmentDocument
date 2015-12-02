package com.itree.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itree.dao.api.RolePermissionDao;

public class RolePermissionDaoImplTest {

	RolePermissionDao rdao = new RolePermissionDaoImpl();

	int rid;
	List<Integer> pid;

	@Test
	public void testAdd() {
		rdao.add(rid, pid);

	}

	@Test
	public void testAdd2() {
		rid = 1;
		pid = new ArrayList<Integer>();
		pid.add(1);
		rdao.add(rid, pid);

	}

	@Test
	public void testDeleteByRId() {
		rid = 1;
		rdao.deleteByRoleID(rid);
	}

	@Test
	public void testDeleteByRId2() {
		rdao.deleteByRoleID(rid);
	}

	@Test
	public void testUpdatePermission() {
		rdao.update(rid, pid);
	}

	@Test
	public void testUpdatePermission2() {
		rid = 1;
		pid = new ArrayList<Integer>();
		pid.add(1);
		rdao.update(rid, pid);
	}

	@Test
	public void testFindByRId() {
		rdao.findListByRoleID(rid);
	}

	@Test
	public void testFindByRId2() {
		rid = 1;
		rdao.findListByRoleID(rid);
	}

	@Test
	public void testFindRolePIds() {
		rid = 0;
		rdao.findPermissionIDByRoleID(rid);
	}

	@Test
	public void testFindRolePIds2() {
		rid = 1;
		rdao.findPermissionIDByRoleID(rid);
	}

}

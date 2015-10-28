package com.itree.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		rdao.deleteByRId(rid);
	}

	@Test
	public void testDeleteByRId2() {
		rdao.deleteByRId(rid);
	}

	@Test
	public void testUpdatePermission() {
		rdao.updatePermission(rid, pid);
	}

	@Test
	public void testUpdatePermission2() {
		rid = 1;
		pid = new ArrayList<Integer>();
		pid.add(1);
		rdao.updatePermission(rid, pid);
	}

	@Test
	public void testFindAll() {
		rdao.findAll();
	}

	@Test
	public void testFindByRId() {
		rdao.findByRId(rid);
	}

	@Test
	public void testFindByRId2() {
		rid = 1;
		rdao.findByRId(rid);
	}

	@Test
	public void testFindRolePIds() {
		rid = 0;
		rdao.findRolePIds(rid);
	}

	@Test
	public void testFindRolePIds2() {
		rid = 1;
		rdao.findRolePIds(rid);
	}

}

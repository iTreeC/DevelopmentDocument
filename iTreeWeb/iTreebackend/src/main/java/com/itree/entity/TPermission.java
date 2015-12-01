package com.itree.entity;

import java.io.Serializable;

public class TPermission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;//具体权限节点
	private int pid;//权限的父节点
	private int gid;//权限的祖父节点
	private String name;//角色名称
	private String describe;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}

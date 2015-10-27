package com.itree.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_role_permission")

public class RolePermission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	private int rid;
	private int pid;
	private int status = 0;
	
	////// getter & setter ///////
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", rid=" + rid + ", pid=" + pid + ", status="
				+ status + "]";
	}
	
	
}

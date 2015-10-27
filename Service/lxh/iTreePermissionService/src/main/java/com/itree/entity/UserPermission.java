package com.itree.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Entity
@Table(name="tb_user_permission")

public class UserPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	private int uid;
	private int pid;
	private int status = 0;
	
	////////// getter & setter ///////////
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
		return "User [id=" + id + ", uid=" + uid + ", pid=" + pid + ", status="
				+ status + "]";
	}
	
	
	
	
	
}

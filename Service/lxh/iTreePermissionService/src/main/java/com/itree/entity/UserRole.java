/**
 * @info 
 * @author ÀîÏş»¶
 * @time 2015.10.28
 */
package com.itree.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_user_role")

public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	private int uid;
	private int rid;
	private int status = 0;
	
	/////////////// getter & setter ///////////////
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
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
}

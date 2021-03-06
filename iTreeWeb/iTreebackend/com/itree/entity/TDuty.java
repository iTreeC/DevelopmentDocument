package com.itree.entity;
// Generated 2015-11-15 16:02:00 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * TDuty generated by hbm2java
 */
public class TDuty implements java.io.Serializable {

	private Integer id;
	private String dutyName;
	private int dutyStatus;
	private Set<TUser> TUsers = new HashSet<TUser>(0);

	public TDuty() {
	}

	public TDuty(String dutyName, int dutyStatus) {
		this.dutyName = dutyName;
		this.dutyStatus = dutyStatus;
	}

	public TDuty(String dutyName, int dutyStatus, Set<TUser> TUsers) {
		this.dutyName = dutyName;
		this.dutyStatus = dutyStatus;
		this.TUsers = TUsers;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDutyName() {
		return this.dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public int getDutyStatus() {
		return this.dutyStatus;
	}

	public void setDutyStatus(int dutyStatus) {
		this.dutyStatus = dutyStatus;
	}

	public Set<TUser> getTUsers() {
		return this.TUsers;
	}

	public void setTUsers(Set<TUser> TUsers) {
		this.TUsers = TUsers;
	}

}

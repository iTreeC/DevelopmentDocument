package com.itree.entity;
// Generated 2015-11-15 16:02:00 by Hibernate Tools 4.0.0

/**
 * TLogin generated by hbm2java
 */
public class TLogin implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private TUser TUser;
	private String password;
	private int loginStatus;

	public TLogin() {
	}

	public TLogin(TUser TUser, String password, int loginStatus) {
		this.TUser = TUser;
		this.password = password;
		this.loginStatus = loginStatus;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

}

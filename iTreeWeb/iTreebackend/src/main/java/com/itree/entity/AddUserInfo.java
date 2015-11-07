/*
 * 该实体类用于接受“admin-add.jsp”页面传来的数据。
 */
package com.itree.entity;

public class AddUserInfo {
	private String name;
	private int duty;
	private int sex;
	private String password;
	private String pototPath;
	private String hoby;
	private String telephone;
	private String profile;
	private boolean userStatus;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public int getDuty() {
		return duty;
	}
	public void setDuty(int duty) {
		this.duty = duty;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getHoby() {
		return hoby;
	}
	public void setHoby(String hoby) {
		this.hoby = hoby;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPototPath() {
		return pototPath;
	}
	public void setPototPath(String pototPath) {
		this.pototPath = pototPath;
	}
	public String getHobby() {
		return hoby;
	}
	public void setHobby(String hobby) {
		this.hoby = hobby;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public boolean isUserStatus() {
		return userStatus;
	}
	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public String toString() {
		return "AddUserInfo [name=" + name + ", duty=" + duty + ", sex=" + sex + ", password=" + password
				+ ", pototPath=" + pototPath + ", hoby=" + hoby + ", telephone=" + telephone + ", profile=" + profile
				+ ", userStatus=" + userStatus + "]";
	}
	
	
}

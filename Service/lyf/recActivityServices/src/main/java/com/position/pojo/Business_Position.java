package com.position.pojo;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 存储公司位置信息
 * @author Fei
 *
 */
public class Business_Position {
	
	private int id;//编号
	private int companyID;//一对一公司
	private String address;//详尽地址
	private Provincial_Number province;//省
	private City_Number city;//市
	private int county;//县
	private int usable;//可用标识
	private Company company;
	@JsonIgnore
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@JsonIgnore
	public Provincial_Number getProvince() {
		return province;
	}
	public void setProvince(Provincial_Number province) {
		this.province = province;
	}
	@JsonIgnore
	public City_Number getCity() {
		return city;
	}
	public void setCity(City_Number city) {
		this.city = city;
	}
	public int getCounty() {
		return county;
	}
	public void setCounty(int county) {
		this.county = county;
	}
	public int getUsable() {
		return usable;
	}
	public void setUsable(int usable) {
		this.usable = usable;
	}
	

}

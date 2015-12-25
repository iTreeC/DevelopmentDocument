package com.position.pojo;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 存储公司位置信息
 * @author Fei
 *
 */
public class CompanyPosition {
	
	private int id;//编号
	//private int companyID;//公司id
	private String address;//详尽地址
	private ProvincialNumber province;//省 ,多对一
	private CityNumber city;//市，多对一
	private int county;//县，多对一
	private Company company;//一对一，单向
	private int usable;//可用标识
	
	
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
	/*public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}*/
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@JsonIgnore
	public ProvincialNumber getProvince() {
		return province;
	}
	public void setProvince(ProvincialNumber province) {
		this.province = province;
	}
	@JsonIgnore
	public CityNumber getCity() {
		return city;
	}
	public void setCity(CityNumber city) {
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

package com.position.pojo;
/**
 * 存储公司位置信息
 * @author Fei
 *
 */
public class Business_Position {
	private int id;//编号
	private Company company;//一对一公司
	private String Address;//详尽地址
	private Provincial_Number Province;//省
	private City_Number City;//市
	private int County;//县
	private int Usable;//可用标识
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Provincial_Number getProvince() {
		return Province;
	}
	public void setProvince(Provincial_Number province) {
		Province = province;
	}
	public City_Number getCity() {
		return City;
	}
	public void setCity(City_Number city) {
		City = city;
	}
	public int getCounty() {
		return County;
	}
	public void setCounty(int county) {
		County = county;
	}
	public int getUsable() {
		return Usable;
	}
	public void setUsable(int usable) {
		Usable = usable;
	}
}

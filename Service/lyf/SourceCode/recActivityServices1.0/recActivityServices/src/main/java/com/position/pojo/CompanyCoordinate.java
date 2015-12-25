package com.position.pojo;
/**
 * 经纬度坐标表
 * @author Fei
 *
 */
public class CompanyCoordinate {
	private int id;//主键标识
	private Company company;//商家,双向一对一
	private double Lng;//商家精度
	private double Lat;//商家纬度 
	private String Location;//经纬度算法标识
	private int usable;//可用标识
	
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
	public double getLng() {
		return Lng;
	}
	public void setLng(double lng) {
		Lng = lng;
	}
	public double getLat() {
		return Lat;
	}
	public void setLat(double lat) {
		Lat = lat;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public int getUsable() {
		return usable;
	}
	public void setUsable(int usable) {
		this.usable = usable;
	}
	
}

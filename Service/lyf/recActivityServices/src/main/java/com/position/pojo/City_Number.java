package com.position.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 城市编号表
 * @author Fei
 *
 */
public class City_Number {
	private int CityID;//城市编号
	private String City;//名称
	private int Usable;//可用标识
	private Provincial_Number pro;//多对一个省
	private Set<Company> Comps = new HashSet<Company>();//一对多个公司
	
	
	public Set<Company> getComps() {
		return Comps;
	}
	public void setComps(Set<Company> comps) {
		Comps = comps;
	}
	public Provincial_Number getPro() {
		return pro;
	}
	public void setPro(Provincial_Number pro) {
		this.pro = pro;
	}
	public int getCityID() {
		return CityID;
	}
	public void setCityID(int cityID) {
		CityID = cityID;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public int getUsable() {
		return Usable;
	}
	public void setUsable(int usable) {
		Usable = usable;
	}
}

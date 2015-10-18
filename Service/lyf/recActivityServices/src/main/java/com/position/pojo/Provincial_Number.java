package com.position.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 所有省编号
 * @author Fei
 *
 */
public class Provincial_Number {
	private int proID;//编号
	private String provincial;//名称
	private int usable;//可用标识
	private Set<City_Number> cities = new HashSet<City_Number>();//一对多个市
	private Set<Company> comps = new HashSet<Company>();//一对多个公司
	public int getProID() {
		return proID;
	}
	public void setProID(int proID) {
		this.proID = proID;
	}
	public String getProvincial() {
		return provincial;
	}
	public void setProvincial(String provincial) {
		this.provincial = provincial;
	}
	public int getUsable() {
		return usable;
	}
	public void setUsable(int usable) {
		this.usable = usable;
	}
	public Set<City_Number> getCities() {
		return cities;
	}
	public void setCities(Set<City_Number> cities) {
		this.cities = cities;
	}
	public Set<Company> getComps() {
		return comps;
	}
	public void setComps(Set<Company> comps) {
		this.comps = comps;
	}
}

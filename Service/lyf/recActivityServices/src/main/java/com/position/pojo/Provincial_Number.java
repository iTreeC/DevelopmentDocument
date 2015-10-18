package com.position.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 所有省编号
 * @author Fei
 *
 */
public class Provincial_Number {
	private int ProID;//编号
	private String Provincial;//名称
	private int Usable;//可用标识
	private Set<City_Number> Cities = new HashSet<City_Number>();//一对多个市
	private Set<Company> Comps = new HashSet<Company>();//一对多个公司

	public Set<Company> getComps() {
		return Comps;
	}
	public void setComps(Set<Company> comps) {
		Comps = comps;
	}
	public Set<City_Number> getCities() {
		return Cities;
	}
	public void setCities(Set<City_Number> cities) {
		Cities = cities;
	}
	public int getProID() {
		return ProID;
	}
	public void setProID(int proID) {
		ProID = proID;
	}
	public String getProvincial() {
		return Provincial;
	}
	public void setProvincial(String provincial) {
		Provincial = provincial;
	}
	public int getUsable() {
		return Usable;
	}
	public void setUsable(int usable) {
		Usable = usable;
	}
}

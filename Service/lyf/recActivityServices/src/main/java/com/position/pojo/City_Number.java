package com.position.pojo;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 城市编号表
 * @author Fei
 *
 */
public class City_Number {
	private int cityID;//城市编号
	private String city;//名称
	private int usable;//可用标识
	private Provincial_Number pro;//多对一个省 
	private Set<Business_Position> pos = new HashSet<Business_Position>();//一对多个公司地点
	
	@JsonIgnore
	public Set<Business_Position> getPos() {
		return pos;
	}
	public void setPos(Set<Business_Position> pos) {
		this.pos = pos;
	}
	public int getCityID() {
		return cityID;
	}
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getUsable() {
		return usable;
	}
	public void setUsable(int usable) {
		this.usable = usable;
	}
	@JsonIgnore
	public Provincial_Number getPro() {
		return pro;
	}
	public void setPro(Provincial_Number pro) {
		this.pro = pro;
	}
}

package com.position.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 城市编号表
 * @author Fei
 *
 */
@Entity
public class CityNumber {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cityID;//城市编号
	private String city;//名称
	private int usable;//可用标识
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ProID")
	private ProvincialNumber pro;//多对一个省 
	
	
	@OneToMany(mappedBy="city")
	private Set<CompanyPosition> pos = new HashSet<CompanyPosition>();//一对多个公司地点
	
	@JsonIgnore
	public Set<CompanyPosition> getPos() {
		return pos;
	}
	public void setPos(Set<CompanyPosition> pos) {
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
	public ProvincialNumber getPro() {
		return pro;
	}
	public void setPro(ProvincialNumber pro) {
		this.pro = pro;
	}
}

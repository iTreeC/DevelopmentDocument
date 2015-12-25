package com.position.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 所有省编号
 * @author Fei
 *
 */
@Entity
public class ProvincialNumber {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int proID;//编号
	private String provincial;//名称
	private int usable;//可用标识
	
	@OneToMany(mappedBy="pro")
	private Set<CityNumber> cities = new HashSet<CityNumber>();//一对多个市
	
	@OneToMany(mappedBy="province")
	private Set<CompanyPosition> comps = new HashSet<CompanyPosition>();//一对多个公司地址
	
	
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
	@JsonIgnore
	public Set<CityNumber> getCities() {
		return cities;
	}
	public void setCities(Set<CityNumber> cities) {
		this.cities = cities;
	}
	@JsonIgnore
	public Set<CompanyPosition> getComps() {
		return comps;
	}
	public void setComps(Set<CompanyPosition> comps) {
		this.comps = comps;
	}
	
}

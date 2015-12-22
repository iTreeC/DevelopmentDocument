package com.position.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 招聘公司信息（测试用）
 * @author Fei
 *
 */
@Entity
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;//id
	private String companyName;//名称
	private int usable;//可用标识
	
	@OneToOne
	private CompanyPosition pos;//一对一地址，双向
	
	@OneToOne
	private CompanyCoordinate coo;//一对一坐标，双向
	
	@JsonIgnore
	public CompanyPosition getPos() {
		return pos;
	}
	public void setPos(CompanyPosition pos) {
		this.pos = pos;
	}
	@JsonIgnore
	public CompanyCoordinate getCoo() {
		return coo;
	}
	public void setCoo(CompanyCoordinate coo) {
		this.coo = coo;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getUsable() {
		return usable;
	}
	public void setUsable(int usable) {
		this.usable = usable;
	}
	
}

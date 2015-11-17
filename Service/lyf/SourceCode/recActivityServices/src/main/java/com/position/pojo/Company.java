package com.position.pojo;

import java.io.Serializable;

import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 招聘公司信息（测试用）
 * @author Fei
 *
 */
@Entity
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;//id
	private String companyName;//名称
	private int usable;//可用标识
	private Business_Position pos;//一对一地址
	
	@JsonIgnore
	public Business_Position getPos() {
		return pos;
	}
	public void setPos(Business_Position pos) {
		this.pos = pos;
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

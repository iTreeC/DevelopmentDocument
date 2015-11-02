/**
 * @info 
 * @author ÀîÏþ»¶
 * @time 2015.10.28
 */
package com.itree.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Entity
@Table(name="tb_user")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	private String name;
	private int status = 0;
	

	////// getter & setter ///////
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
		
}

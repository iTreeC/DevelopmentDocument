/**
 * @info 
 * @author ÀîÏş»¶
 * @time 2015.10.28
 */
package com.itree.entity;

import javax.persistence.*;

@Entity
@Table(name="tb_user")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	
	@Column(nullable = false)
	private int ClientUserID;
	
	@Column(nullable = true)
	private Boolean status;
	
	////////// getter & setter ////////
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public int getClientUserID() {
		return ClientUserID;
	}
	public void setClientUserID(int clientUserID) {
		ClientUserID = clientUserID;
	}
	

	
	
	

	
	
}

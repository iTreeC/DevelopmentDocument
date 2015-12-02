/**
 * @info 
 * @author ÀîÏş»¶
 * @time 2015.10.28
 */
package com.itree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_permission")

public class Perm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	
	@Column(nullable = false)
	private int ClientPermissionID;
	
	@Column(nullable = true)
	private Boolean status;


	////////// getter & setter ////////
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientPermissionID() {
		return ClientPermissionID;
	}

	public void setClientPermissionID(int clientPermissionID) {
		ClientPermissionID = clientPermissionID;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	



	
	

	
	
	
	

	
	
}

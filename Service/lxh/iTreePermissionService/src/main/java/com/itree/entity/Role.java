/**
 * @info 
 * @author ÀîÏş»¶
 * @time 2015.10.28
 */
package com.itree.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false, length = 50)
	private String name;
	@Column(nullable = true)
	private Boolean status;

	// //////// getter & setter ////////

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Perm [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}

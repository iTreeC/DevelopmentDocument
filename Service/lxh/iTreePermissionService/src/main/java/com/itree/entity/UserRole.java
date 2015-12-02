/**
 * @info 
 * @author ÀîÏþ»¶
 * @time 2015.10.28
 */
package com.itree.entity;

import javax.persistence.*;

@Entity
@Table(name="tb_user_role")

public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;

	@ManyToOne(targetEntity = com.itree.entity.User.class)
	@JoinColumn(name="uid" ,nullable = false)
	//private int uid;
	private User user;

	@ManyToOne(targetEntity = com.itree.entity.Role.class)
	@JoinColumn(name="rid" ,nullable = false)
	//private int rid;
	private Role role;

	@Column(nullable = true)
	private Boolean status;
	
	/////////////// getter & setter ///////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}*/
	
	
	public Boolean getStatus() {
		return status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
	
	
}

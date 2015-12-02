/**
 * @info 
 * @author ÀîÏþ»¶
 * @time 2015.10.28
 */
package com.itree.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_role_permission")
public class RolePermission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(targetEntity = Role.class)
	@JoinColumn(name = "rid", nullable = false)
//	private int rid;
	private Role role;

	@ManyToOne(targetEntity = Perm.class)
	@JoinColumn(name = "pid", nullable = false)
//	private int pid;
	private Perm perm;

	@Column(nullable = true)
	private Boolean status;

	// //// getter & setter ///////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

/*	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}*/

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Perm getPerm() {
		return perm;
	}

	public void setPerm(Perm perm) {
		this.perm = perm;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


}

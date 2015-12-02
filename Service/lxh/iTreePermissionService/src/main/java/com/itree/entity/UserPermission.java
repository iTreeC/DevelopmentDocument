/**
 * @info 
 * @author ÀîÏş»¶
 * @time 2015.10.28
 */
package com.itree.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_permission")
public class UserPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(targetEntity = User.class,cascade=CascadeType.ALL)
	@JoinColumn(name = "uid", nullable = false)
	//private int uid;
	User user;

	@ManyToOne(targetEntity = Perm.class,cascade=CascadeType.ALL)
	@JoinColumn(name = "pid", nullable = false)
	//private int pid;
	Perm perm;
	@Column(nullable = true)
	private Boolean status;

	// //////// getter & setter ///////////




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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}*/

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Perm getPerm() {
		return perm;
	}

	public void setPerm(Perm perm) {
		this.perm = perm;
	}	
}

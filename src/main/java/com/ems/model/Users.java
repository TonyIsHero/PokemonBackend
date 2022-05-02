package com.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="poke_users")
public class Users {
	@Id
	@Column(name="u_id")
	int uid;
	@Column(name="xP")
	int xP;
	@Column(name="score")
	int score;
	@Column(name="status")
	boolean status;
	@Column(name="username")
	String username;
	@Column(name="email")
	String email;
	@Column(name="password")
	String password;
	
	public Users() {
		super();
	}
	
	public Users(int uid, int xP, int score, boolean status, String username, String email, String password) {
		super();
		this.uid = uid;
		this.xP = xP;
		this.score = score;
		this.status = status;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getxP() {
		return xP;
	}
	public void setxP(int xP) {
		this.xP = xP;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

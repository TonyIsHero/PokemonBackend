package com.ems.model;

public class Pokemon {

	int p_id;
	int p_xp;
	
	String p_name;
	String p_type;
	String p_ability;
	
	public int getP_xp() {
		return p_xp;
	}
	public void setP_xp(int p_xp) {
		this.p_xp = p_xp;
	}
	
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getP_ability() {
		return p_ability;
	}
	public void setP_ability(String p_ability) {
		this.p_ability = p_ability;
	}
}

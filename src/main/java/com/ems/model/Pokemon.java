package com.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="poke_pokemons")
public class Pokemon {
	@Id
	@Column(name="p_id")
	int p_id;
	@Column(name="p_xP")
	int p_xp;
	@Column(name="p_name")
	String p_name;
	@Column(name="p_type")
	String p_type;
	@Column(name="p_ability")
	String p_ability;
	@Column(name="isGiga")
	boolean isGiga;
	@Column(name="p_desc")
	String p_description;
	
	public Pokemon() {
		super();
	}
	
	public Pokemon(int p_id, int p_xp, String p_name, String p_type, String p_ability, boolean isGiga,
			String p_description) {
		super();
		this.p_id = p_id;
		this.p_xp = p_xp;
		this.p_name = p_name;
		this.p_type = p_type;
		this.p_ability = p_ability;
		this.isGiga = isGiga;
		this.p_description = p_description;
	}
	
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getP_xp() {
		return p_xp;
	}
	public void setP_xp(int p_xp) {
		this.p_xp = p_xp;
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
	public boolean isGiga() {
		return isGiga;
	}
	public void setGiga(boolean isGiga) {
		this.isGiga = isGiga;
	}
	public String getP_description() {
		return p_description;
	}
	public void setP_description(String p_description) {
		this.p_description = p_description;
	}
}

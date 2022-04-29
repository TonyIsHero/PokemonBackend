package com.ems.constants;

public class DaoConstants {

	public static final String GET_ALL_POKEMONS_QUERY="select * from pokemon_db.poke_pokemons";
	public static final String INSERT_INTO_USERS="insert into poke_users(username,email, password)";
	public static final String GET_ALL_USERS_QUERY="select * from pokemon_db.poke_users";
	public static final String USERNAME="username";
	public static final String EMAIL="email";
	public static final String PASSWORD="password";
	public static final String STATUS="status";
	public static final String xP="xP";
	public static final String SCORE="score";
	public static final String UPDATE_STATUS_TRUE_WHERE_EMAIL="update poke_users set status=true where email=";
	public static final String UPDATE_STATUS_FALSE_WHERE_EMAIL="update poke_users set status=false where email=";
	public static final String STATUS_UPDATE_SUCCESS="Status Update Success";
	public static final String SCORE_UPDATE_SUCCESS="Score Update Success";
	public static final String UPDATE_FAILED="Failure";
	public static final String POKEMON_ID="p_id";
	public static final String POKEMON_xP="p_xP";
	public static final String POKEMON_NAME="p_name";
	public static final String POKEMON_TYPE="p_type";
	public static final String POKEMON_ABILITY="p_ability";
}

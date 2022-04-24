package com.ems.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.ems.model.Pokemon;
import com.ems.model.Users;

@Component
public class PokemonDao {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	/*
	 * method to fetch pokemon details from poke_pokemons table 
	 */
	public List<Pokemon> getPokeDetails()
	{
		List<Pokemon> empList = new ArrayList<>();
		String sql="select * from pokemon_db.poke_pokemons";
		
		return jdbctemplate.query(sql, new ResultSetExtractor<List<Pokemon>>() {
			
			public List<Pokemon> extractData(ResultSet rs)throws SQLException, DataAccessException{

				while(rs.next()) {
					
					Pokemon e=new Pokemon();
					e.setP_id(rs.getInt("p_id"));
					e.setP_xp(rs.getInt("p_xP"));
					e.setP_name(rs.getString("p_name"));
					e.setP_type(rs.getString("p_type"));
					e.setP_ability(rs.getString("p_ability"));
					empList.add(e);
					
				}
				return empList;
			}
		});
	}
	
	/*
	 * Method to insert users into database
	 */
	
	public boolean insertUsers(String username,String email, String password) {
		
		String sql="insert into poke_users(username,email, password) values ('"+username+"','"+email+"','"+password+"')";
		
		boolean status=false;
		try
		{
			jdbctemplate.execute(sql);
			status=true;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	/*
	 * Method to fetch Users from poke_users database
	 */
	
	public List<Users> getUserDetails() {
		
		List<Users> userList = new ArrayList<>();
		String sql="select * from pokemon_db.poke_users";
		
		return jdbctemplate.query(sql, new ResultSetExtractor<List<Users>>() {
			
			public List<Users> extractData(ResultSet rs)throws SQLException, DataAccessException{

				while(rs.next()) {
					
					Users e=new Users();
					e.setEmail(rs.getString("email"));
					e.setUsername(rs.getString("username"));
					e.setPassword(rs.getString("password"));
					e.setStatus(rs.getBoolean("status"));
					e.setxP(rs.getInt("xP"));
					e.setScore(rs.getInt("score"));
					userList.add(e);
					
				}
				return userList;
			}
		});

}

	public void updateStatus(Users user) {
		// TODO Auto-generated method stub
		
		String email="'"+user.getEmail()+"'";
		String sql="update poke_users set status=true where email="+email;
		
		try
		{
			jdbctemplate.execute(sql);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
}

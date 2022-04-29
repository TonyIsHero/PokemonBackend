package com.ems.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ems.constants.ServiceConstants;
import com.ems.dao.PokemonDao;
import com.ems.model.Pokemon;
import com.ems.model.Users;

@Component			
public class PokemonService {

	@Autowired
	PokemonDao pokeDao;
	
	public List<Pokemon> getPokeDetails() {
		
		List<Pokemon> pokeList= pokeDao.getPokeDetails();
		
		return pokeList;
	}
	
	public boolean insertUsers(Users user) {
		
		return pokeDao.insertUsers(user.getUsername(),user.getEmail(), user.getPassword());
	}
	
	public List <Users> getUserDetails(){
		
		List<Users> userList= pokeDao.getUserDetails();
		
		return userList;
	}
	
	/*
	 * Method to check if Email exists
	 */
	
	public  boolean checkEmailId(Users user)
	{
		boolean flag=false;
		
		/*
		 * Displaying email to check fetch from database success
		 */
		String email=user.getEmail();
		System.out.println(email);
		
		//try catch block to catch No Element Exception : If email id does not exist in database 
		
		try
		{
			
			/*
			 * Checks if the email entered by the user already exists in the database 
			 */
			
		Users usersPresent = getUserDetails().stream().filter(emp -> emp.getEmail().equals(email)).findAny().get();
		
		if(usersPresent.getEmail().equals(email)) 
			
			flag=true;
			
		}
		
		/*
		 * If element does not exist return false
		 */
		
		catch(NoSuchElementException e) {
			
			flag=false;
		}
		
		catch(Exception e) {}
		
		return flag;
		
	}

	public boolean checkUser(Users user) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String email=user.getEmail();
		try {
			
			
			Users usersPresent = getUserDetails().stream().filter(emp -> emp.getEmail().equals(email)).findAny().get();
		
		/*
		 * Checks if the password entered by the user is same as the one in the database
		 */
		
			if(user.getPassword().equals(usersPresent.getPassword()))
			
				flag=true;
				pokeDao.updateStatus(user);
			}
		//If no entries are found
			catch(NoSuchElementException e) {
			
			flag=false;
			}
		
		catch(Exception e) {}
		
		return flag;
	}

	/*
	 * Calls the method in DAO, argument email is passed.
	 */

	public String updateStatusOFFLINE(String email) 
	{
		
		return pokeDao.updateStatusOFFLINE(email);
	}

	public String updateScore(String email, int score)
	{
		return pokeDao.updateScore(email,score);
	}
	
	/*
	 *Method gets the List of Users from the Data Access Object.
	 *Fetches the user details with respect to the Email passed.
	 */
	
	public Users getUserByEmail(String email) {
		
		List<Users> userList=pokeDao.getUserDetails();
		
		
		if(CollectionUtils.isEmpty(userList))
		{
			System.out.println(ServiceConstants.DATA_NOT_FOUND);
		}
		
		/*
		 * Gets the user with the email id that matches with the one passed.
		 */
		
		return userList.stream()
				.filter(user -> user.getEmail().equals(email))
						.findAny().get();
		
	}
	/*
	 * Method gets the List of Users from the Data Access Object.
	 * List of users is sorted in descending order with respect to the score.
	 * Stored in sorted_leaders.
	 */
	
	public List<Users> getLeaderBoard()
	{
		List<Users> leaders=pokeDao.getUserDetails();
		
		List<Users> sorted_leaders=leaders.stream().
				sorted((o1,o2)->(int)(o2.getScore()-o1.getScore())).
				collect(Collectors.toList());
		
		return sorted_leaders;
	}



	
}


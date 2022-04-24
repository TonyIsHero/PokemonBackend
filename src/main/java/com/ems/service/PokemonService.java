package com.ems.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

	public String updateStatus() {
		// TODO Auto-generated method stub
		return "Test";
	}

	public Users getUserByEmail(String email) {
		
		List<Users> userList=pokeDao.getUserDetails();
		
		
		if(CollectionUtils.isEmpty(userList))
		{
			System.out.println("Data Not Found");
		}
		
		return userList.stream()
				.filter(user -> user.getEmail().equals(email))
						.findAny().get();
		
	}

	
}


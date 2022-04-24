package com.ems.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.constants.ControllerConstants;
import com.ems.model.Pokemon;
import com.ems.model.Users;
import com.ems.service.PokemonService;


@RestController
@CrossOrigin(ControllerConstants.ORIGINS_URL)
public class PokemonController {
	
	@Autowired
	PokemonService service;

	
	/*A sample text to check host connection
	  */
	@RequestMapping(value=ControllerConstants.GET_SAMPLE_STRING_VALUE, method = RequestMethod.GET)
	public String test() {
		return ControllerConstants.SAMPLE_TEXT;
	}
	
	/*
	 * A method to get pokemon detail from database
	 * */
	
	@RequestMapping(value=ControllerConstants.GET_POKE_VALUE, method = RequestMethod.GET)
	public List<Pokemon> getPokeDetails() {
		
		List<Pokemon> pokeList= service.getPokeDetails();
		
		return pokeList;
	}
	
	
	@RequestMapping(value=ControllerConstants.GET_USER_BY_EMAIL, method=RequestMethod.GET)
	public Users getUserByEmail(@RequestParam("email") String email)
	{
		if(email==null) {
			throw new IllegalArgumentException("Cannot be null");
		}
		
		else
			return service.getUserByEmail(email);
	}
	
	
	@RequestMapping(value=ControllerConstants.GET_USER_VALUE, method = RequestMethod.GET)
	public List<Users> getUserDetails() {
		
		List<Users> userList= service.getUserDetails();
		
		return userList;
	}
	
	@RequestMapping(value=ControllerConstants.POST_LOGIN_VALUE, method = RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String checkUser(@RequestBody Users user)
	{	
		
		boolean check= service.checkUser(user);
		
		if(check)
			return "Login Success";
		else
			return "Login Failed";
		
	}
	/*
	 * Method to insert username, password and email to database 
	 */
	
	@RequestMapping(value=ControllerConstants.POST_INSERT_VALUE, method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String insertUsers(@RequestBody Users user)
	{
		boolean insertstatus=false;
		boolean checkEmailPresent=false;
		
		/*
		 * If user enters no value to username, password or email
		 */
		if(user.getUsername()==null||user.getPassword()==null||user.getEmail()==null)
			
			throw new IllegalArgumentException(ControllerConstants.INSERT_VALID_CREDENTIALS);
		
		//checks if email already exists in database 
		checkEmailPresent = service.checkEmailId(user);

		if(checkEmailPresent)
			
			return ControllerConstants.DATA_EMAIL_PRESENT;
		//Insertion of data
		else
		{
			
			insertstatus=service.insertUsers(user);
						
		if(insertstatus==true)
			
			return ControllerConstants.DATA_INSERTION_SUCCESS;
		
		else
			return ControllerConstants.DATA_INSERTION_FAILURE;
		}
	}
	
	@RequestMapping(value="/updateStatusOnline", method=RequestMethod.POST,
			consumes=MediaType.ALL_VALUE)
	public String updateStatus(@RequestBody String status) {
		
		if(status=="Login Success")
		return service.updateStatus();
		return status;
		
	}

	
}

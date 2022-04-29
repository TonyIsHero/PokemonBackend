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
	
	/*
	 * Method to fetch user with the respective email id using 
	 * email parameter
	 */
	
	@RequestMapping(value=ControllerConstants.GET_USER_BY_EMAIL, method=RequestMethod.GET)
	public Users getUserByEmail(@RequestParam(ControllerConstants.CONSTANT_EMAIL) String email)
	{
		if(email==null) {
			throw new IllegalArgumentException(ControllerConstants.CANNOT_BE_NULL);
		}
		
		else
			return service.getUserByEmail(email);
	}
	
	/*
	 * Returns all the users present is the database
	 */
	
	@RequestMapping(value=ControllerConstants.GET_USER_VALUE, method = RequestMethod.GET)
	public List<Users> getUserDetails() {
		
		List<Users> userList= service.getUserDetails();
		
		return userList;
	}
	
	@RequestMapping(value=ControllerConstants.GET_LEADER_LIST, method=RequestMethod.GET)
	public List<Users> getLeaderBoard(){
		
		List<Users> leaderList=service.getLeaderBoard();
		return leaderList;
	}
	
	/*
	 * To check if the credentials entered by the user is correct or not
	 * Credentials entered are:
	 * Email ID and Password
	 */
	
	@RequestMapping(value=ControllerConstants.POST_LOGIN_VALUE, method = RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String checkUser(@RequestBody Users user)
	{	
		
		boolean check= service.checkUser(user);
		
		if(check)
			return ControllerConstants.LOGIN_SUCCESS;
		else
			return ControllerConstants.LOGIN_FAILED;
		
	}
	/*
	 * Method to create an Account in the database 
	 * Parameters:
	 * Email id, User name, Password
	 */
	
	@RequestMapping(value=ControllerConstants.POST_INSERT_VALUE, method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String insertUsers(@RequestBody Users user)
	{
		boolean insertstatus=false;
		boolean checkEmailPresent=false;
		
		/*
		 * If user enters no value to User name, password or email
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
	
	/*
	 * Method to update the status when the user logs out of the system
	 * Parameter used is Email, column changed in database is "Status"
	 */
	
	@RequestMapping(value=ControllerConstants.POST_UPDATE_STATUS, method=RequestMethod.POST,
			consumes=MediaType.ALL_VALUE)
	public String updateStatusOFFLINE(@RequestParam (ControllerConstants.CONSTANT_EMAIL) String email) {
		
		return service.updateStatusOFFLINE(email);

		
	}
	
	/*
	 * Method to update score when the user updates the Team in front end
	 * Parameters used are email (to fetch user), column updated is score.
	 */
	
	@RequestMapping(value=ControllerConstants.POST_UPDATE_SCORE, method=RequestMethod.POST,
			consumes=MediaType.ALL_VALUE)
	public String updateScore(@RequestParam(ControllerConstants.CONSTANT_EMAIL)String email,@RequestParam(ControllerConstants.CONSTANT_SCORE) int score)
	{
		return service.updateScore(email,score);
	}
	

}

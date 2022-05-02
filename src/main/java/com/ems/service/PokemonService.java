package com.ems.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ems.constants.DaoConstants;
import com.ems.constants.ServiceConstants;
import com.ems.model.Pokemon;
import com.ems.model.Users;
import com.ems.repository.PokemonRepository;
import com.ems.repository.UsersRepository;

@Component			
public class PokemonService {

	@Autowired
	PokemonRepository pokeRepo;
	@Autowired
	UsersRepository usersRepo;
	
	public List<Pokemon> getPokeDetails() {
		List<Pokemon> pokeList = pokeRepo.findAll();
		return pokeList;
	}
	
	public boolean insertUsers(Users user) {
		try {
			usersRepo.save(user);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public List <Users> getUserDetails(){
		List<Users> userList= usersRepo.findAll();
		return userList;
	}
	
	public  boolean checkEmailId(Users user){
		boolean flag=false;
		String email=user.getEmail();
		System.out.println(email);
		try{
			Users usersPresent = getUserDetails().stream().filter(emp -> emp.getEmail().equals(email)).findAny().get();
			if(usersPresent.getEmail().equals(email)) 
				flag=true;
		}
		catch(NoSuchElementException e) {	
			flag=false;
		}
		catch(Exception e) {}
		return flag;
	}

	public boolean checkUser(Users user) {
		boolean flag=false;
		String email=user.getEmail();
		try {
			Users usersPresent = getUserDetails().stream().filter(emp -> emp.getEmail().equals(email)).findAny().get();
			if(user.getPassword().equals(usersPresent.getPassword()))
				flag=true;
				usersRepo.updateStatus(email);
			}
		catch(NoSuchElementException e) {
			flag=false;
			}
		catch(Exception e) {}
		return flag;
	}

	public String updateStatusOFFLINE(String email) {
		try {
			usersRepo.updateStatusOFFLINE(email);
			return DaoConstants.SCORE_UPDATE_SUCCESS;
		}catch(Exception e) {
			return DaoConstants.UPDATE_FAILED;
		}
	}

	public String updateScore(String email, int score){
		try {
			usersRepo.updateScore(email, score);
			return DaoConstants.SCORE_UPDATE_SUCCESS;
		}catch(Exception e) {
			return DaoConstants.UPDATE_FAILED;
		}
	}
	
	public Users getUserByEmail(String email) {
		List<Users> userList = usersRepo.findAll();
		if(CollectionUtils.isEmpty(userList))
		{
			System.out.println(ServiceConstants.DATA_NOT_FOUND);
		}
		return userList.stream()
				.filter(user -> user.getEmail().equals(email))
						.findAny().get();
		
	}

	public List<Users> getLeaderBoard()
	{
		List<Users> leaders = usersRepo.findAll();
		
		List<Users> sorted_leaders=leaders.stream().
				sorted((o1,o2)->(int)(o2.getScore()-o1.getScore())).
				collect(Collectors.toList());
		return sorted_leaders;
	}



	
}


package com.example.automation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automation.Model.LoginModel;
import com.example.automation.Repository.LoginRepos;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepos repo;
	  
	  public LoginModel login(String username, String password) {
	  LoginModel user = repo.findByUsernameAndPassword(username, password);
	   return user;
	  }
	  

}

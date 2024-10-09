package com.example.automation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.automation.Model.LoginModel;

@Repository
public interface LoginRepos extends JpaRepository<LoginModel, Long>{
	LoginModel findByUsernameAndPassword(String username, String password);
	 
	}

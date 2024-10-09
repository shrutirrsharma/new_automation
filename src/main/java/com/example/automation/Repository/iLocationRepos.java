package com.example.automation.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.automation.Model.LocationModel;
import com.example.automation.Model.LoginModel;

@Repository
public interface iLocationRepos extends JpaRepository<LocationModel, Long>{
	 @Procedure("get_all_name1")  
	public int fetchAllLocation();
	 
	}
 
 
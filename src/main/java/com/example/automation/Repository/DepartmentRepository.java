package com.example.automation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.example.automation.Model.DepartmentModel;

public interface DepartmentRepository extends JpaRepository<DepartmentModel,Long>{
	@Procedure("get_Department_name")  
	public String fetchDepartment();	
}

package com.example.automation.Model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tbl_Location")
//@Data
//@NamedStoreProcedureQueries({
//	@NamedStoreProcedureQuery(name="getAllLocation", procedureName="Usp_Tbl_Location", resultClasses="DashboardModel.class")
//})
public class DashboardModel {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
	private int id;
	private String LocationName;
	
	public DashboardModel(int Id, String locationName) {
		super();
		id = Id;
		LocationName = locationName;
	}
	
	public DashboardModel() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int Id) {
		id = Id;
	}
	public String getLocationName() {
		return LocationName;
	}
	public void setLocationName(String locationName) {
		LocationName = locationName;
	}
}

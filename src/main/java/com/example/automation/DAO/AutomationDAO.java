package com.example.automation.DAO;

import org.springframework.stereotype.Repository;

import com.example.automation.Model.DashboardModel;
import com.example.automation.Model.DashboardModelList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
	
@Repository
public class AutomationDAO {
	private static DashboardModelList list = new DashboardModelList();
	    
	    static 
	    {
	        list.getdashboardModelList().add(new DashboardModel(1, "ANDHERI"));
	        list.getdashboardModelList().add(new DashboardModel(2, "JOGESHWARI"));
	        list.getdashboardModelList().add(new DashboardModel(3, "PANVEL"));
	        list.getdashboardModelList().add(new DashboardModel(3, "PANVEL"));

	    }
	    
	    public DashboardModelList getAllDashBoardModel()  //
	    {
	        return list;
	    }
	    public void addPriyankaModel(DashboardModel demodel) {
	        list.getdashboardModelList().add(demodel);
	        
	    }
	    
	    public DashboardModel addPriyankaModelString(DashboardModel demodel) {
	        list.getdashboardModelList().add(demodel);
	        return demodel;
	    }
	    
	    public String addPriyankaModelJson(DashboardModel demodel) {
	        list.getdashboardModelList().add(demodel);
	        ObjectMapper mapper = new ObjectMapper();
	        try {
				return mapper.writeValueAsString(list);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
	    }
		
	}


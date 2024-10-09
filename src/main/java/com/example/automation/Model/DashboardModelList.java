package com.example.automation.Model;

import java.util.ArrayList;
import java.util.List;

public class DashboardModelList {
	
private List<DashboardModel> dashboardModelList;
	
	public List<DashboardModel> getdashboardModelList(){
	if(dashboardModelList==null) {
		dashboardModelList = new ArrayList<>();
	}
	return dashboardModelList; 

}
	public void setDashboardModelList(List<DashboardModel> dashboardModelList) {
        this.dashboardModelList = dashboardModelList;
    }

}

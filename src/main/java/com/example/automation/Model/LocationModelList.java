package com.example.automation.Model;

import java.util.ArrayList;
import java.util.List;

public class LocationModelList {
	
private List<LocationModel> locationModelList;
	
	public List<LocationModel> getlocationModelList(){
	if(locationModelList==null) {
		locationModelList = new ArrayList<>();
	}
	return locationModelList; 

}
	public void setDashboardModelList(List<LocationModel> locationModelList) {
        this.locationModelList = locationModelList;
    }


}

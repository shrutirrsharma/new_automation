package com.example.automation.Model;

import java.util.ArrayList;
import java.util.List;

public class RealTimeModelList {
	
private List<RealTimeModel> realtimedataModelList;
	
	public List<RealTimeModel> getrealtimedataModelList(){
	if(realtimedataModelList==null) {
		realtimedataModelList = new ArrayList<>();
	}
	return realtimedataModelList; 

}
	public void setDashboardModelList(List<RealTimeModel> realtimedataModelList) {
        this.realtimedataModelList = realtimedataModelList;
    }

}

package com.example.automation.Controller;


import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.automation.Model.RealTimeModel;

@RestController
public class DashboardPieChart {
    private static final Logger log = LogManager.getLogger(DashboardPieChart.class);

	private static EntityManagerFactory entityManagerFactory =
	          Persistence.createEntityManagerFactory("automation");
	
	
	
	@PostMapping(path = "/PostGetDashboard", consumes = "application/json", produces = "application/json")
	public String JenkinsJsonResponse1(			
			@RequestBody String postData) throws Exception {
		
		  log.info("PostGetDashboard API called with data: {}", postData);

			List<String> data1 = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();			
			try {			
				String[] arrOfStr = postData.split(",");
				String LocationId = ""+"'" + arrOfStr[0].toString() + "'";
				LocationId=LocationId.replace("\"", "");
				//System.out.print(LocationId);
				
				String DepartmentId = ""+"'" + arrOfStr[1].toString() + "'";
				DepartmentId=DepartmentId.replace("\"", "");
				//System.out.print(DepartmentId);
				
				String BotId = ""+"'" + arrOfStr[2].toString() + "'";
				BotId=BotId.replace("\"", "");
				//System.out.print(BotId);
				
					JSONObject obj1 = new JSONObject();	
					obj1.put("ProcesedCount",60);	
					obj1.put("UnProcessedCount",40);	
					ja1.add(obj1);
					return ja1.toString();			
			//}
			}catch (Exception e) {
			// TODO: handle exception
			}
		return null;
	}
	
	
	//Chart Data with Input Parameter of LocationId, DepartmentId and BotId invoke from Onchange event
	
	@PostMapping(path = "/PostGetDashboardChart", consumes = "application/json", produces = "application/json")
	public  String PostDataChartResponse(			
			@RequestBody String postData) throws Exception {
		  log.info("PostGetDashboardChart API called with data: {}", postData);

			List<String> data1 = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();			
			try {
				
				String[] arrOfStr = postData.split(",");
				String LocationId = ""+"'" + arrOfStr[0].toString() + "'";
				LocationId=LocationId.replace("\"", "");
				//System.out.print(LocationId + "Locationkhalid ");
				String DepartmentId = ""+"'" + arrOfStr[1].toString() + "'";
				DepartmentId=DepartmentId.replace("\"", "");
				//System.out.print(DepartmentId + "Department");
				String BotId = ""+"'" + arrOfStr[2].toString() + "'";
				BotId=BotId.replace("\"", "");
				//System.out.print(BotId + "BotId");
				
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(RealTimeModel.NamedQuery_FetchChartData);	
			     // System.out.print(LocationId.replace("{LocationId:", ""));
			    //  System.out.print(DepartmentId.replace("DepartmentId:", ""));
			    //  System.out.print(BotId.replace("BotId:", "").replace("}", ""));
			      LocationId = LocationId.replace("{LocationId:", "");
			      DepartmentId = DepartmentId.replace("DepartmentId:", "");
			      BotId = BotId.replace("BotId:", "").replace("}", "");
			      
			      LocationId = LocationId.replace("'","");
			      DepartmentId = DepartmentId.replace("'","");
			      BotId = BotId.replace("'","");
			      
			     // System.out.print(Integer.parseInt(LocationId) + " dfsfsdfsdf");
			      //System.out.print(Integer.parseInt(DepartmentId));
			      //System.out.print(Integer.parseInt(BotId));
			      
			      procedureQuery.setParameter("pLocationId",Integer.parseInt(LocationId));
			      procedureQuery.setParameter("pDepartmentId",Integer.parseInt(DepartmentId));
			      procedureQuery.setParameter("pBotId", Integer.parseInt(BotId));
			      
			      procedureQuery.execute(); 
			      @SuppressWarnings("unchecked")
			      List<Object[]> resultList = procedureQuery.getResultList();
			      for (Object[] r : resultList) {
			    	  //System.out.print(r[1]);
			    	  //System.out.print(r[2]);
			    	  JSONObject obj1 = new JSONObject();
					  obj1.put("total_Bot_count",r[0]);
					  obj1.put("ProcesedCount",r[1]);	
					  obj1.put("UnProcessedCount",r[2]);
					  ja1.add(obj1); 
			      }
			      return ja1.toString();		
			//}
			}catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			}
		return null;
	}
	
}

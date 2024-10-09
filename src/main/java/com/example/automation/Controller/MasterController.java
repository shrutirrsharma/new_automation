package com.example.automation.Controller;


import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.automation.Model.BotNameModel;
import com.example.automation.Model.DepartmentModel;
import com.example.automation.Model.LocationModel;
import com.example.automation.Model.LoginModel;
import com.example.automation.Model.RealTimeModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MasterController {	
	
    private static final Logger log = LogManager.getLogger(MasterController.class);

		private static EntityManagerFactory entityManagerFactory =
		          Persistence.createEntityManagerFactory("automation");
		
		
		@RequestMapping(value="/GetLocationProc" , method= RequestMethod.GET)
		public List<LocationModel> getLocation()
	    { log.info("GetLocationProc API called");
	    	EntityManager entityManager = entityManagerFactory.createEntityManager();
		      StoredProcedureQuery procedureQuery =
		              entityManager.createNamedStoredProcedureQuery(LocationModel.NamedQuery_LOCATIONStoreProcedure);		      
		      procedureQuery.execute(); 
		      //@SuppressWarnings("unchecked")
		      List<LocationModel> resultList = procedureQuery.getResultList();
			return resultList;
		}
		@GetMapping(path = "/GetStartTime", produces = "application/json")
		public String getStartTimeString()
	    {
			 log.info("GetStartTime API called");
			List<String> data = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();
			ObjectMapper mapper = new ObjectMapper();
			try {
				
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(RealTimeModel.NamedQuery_FetchSTartTimeStoreProcedure);		      
			      procedureQuery.execute(); 
			      @SuppressWarnings("unchecked")
			      List<Object[]> resultList = procedureQuery.getResultList();
			      for (Object[] r : resultList) {
			    	 // System.out.print(r[0]);
			    	//  System.out.print(r[1]);
			    	  JSONObject obj1 = new JSONObject();
					  obj1.put("ProcessId",r[0]);
					  if (r[1] != null) {
					        // Assuming r[8] is a Date object, you may want to format it
					        // appropriately before adding it to the JSON object
					        // For example, you can convert it to a string in ISO8601 format
					        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					        String formattedStartTime2 = dateFormat.format(r[1]);
					        obj1.put("StartTime", formattedStartTime2);
					    } else {
					        // Handle the case where r[8] is null
					        obj1.put("StartTime", null);
					    }				
					  ja1.add(obj1); 
			      
			      }
			     
				//return resultList;
			     return ja1.toString();
			     // return "Developer";
			
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			
			return null;
		}
		
		
		@PostMapping(path = "/GetDurationTime", consumes = "application/json", produces = "application/json")
		
		public String getDurationTimeString(@RequestBody String postData) throws Exception
	    {
			log.info("GetDurationTime API called with data: {}", postData);
			List<String> data = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();
			ObjectMapper mapper = new ObjectMapper();
			try {

				String[] arrOfStr = postData.split(",");

					String BotName = "" + "'" + arrOfStr[0].toString() + "'";
					
					BotName = BotName.replace("{", "").replace("\"", "").replace("'", "");
					String[] arrOfUStr = BotName.split(":");
					
					BotName = BotName.replace("}", "").replace("\"", "").replace("'", "");
					 System.out.println(BotName);
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotDurationStoreProcedure_BotNameModel);
	entityManager.getTransaction().begin();
				
				procedureQuery.setParameter("Botname", BotName);

			      procedureQuery.execute(); 
			      @SuppressWarnings("unchecked")
			      List<Object[]> resultList = procedureQuery.getResultList();
			      for (Object[] r : resultList) {
			    	 // System.out.print(r[0]);
			    	//  System.out.print(r[1]);
			    	  JSONObject obj1 = new JSONObject();
					  obj1.put("ProcessId",r[0]);
					  obj1.put("Duration",r[1]);
					  obj1.put("BotName",r[2]);
					  ja1.add(obj1); 
			      
			      }
			     
				//return resultList;
			     return ja1.toString();
			     // return "Developer";
			
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			
			return null;
		}
		@GetMapping(path = "/GetLocationlist", produces = "application/json")
		public String getLocationString()
	    {
			log.info("GetLocationlist API called");
			List<String> data = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();
			ObjectMapper mapper = new ObjectMapper();
			try {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(LocationModel.NamedQuery_LOCATIONStoreProcedure);		      
			      procedureQuery.execute(); 
			      @SuppressWarnings("unchecked")
			      List<Object[]> resultList = procedureQuery.getResultList();
			      for (Object[] r : resultList) {
			    	 // System.out.print(r[0]);
			    	//  System.out.print(r[1]);
			    	  JSONObject obj1 = new JSONObject();
					  obj1.put("LocationId",r[0]);
					  obj1.put("LocationName",r[1]);					
					  ja1.add(obj1); 
			      
			      }
			     
				//return resultList;
			     return ja1.toString();
			     // return "Developer";
			
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			
			return null;
		}
	
		
		@GetMapping(path = "/GetDepartmentlist", produces = "application/json")
		public String getDepartmentString()
	    {
		    log.info("GetDepartmentlist API called");

			List<String> data = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();
			ObjectMapper mapper = new ObjectMapper();
			try {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentNameStoreProcedure);		      
			      procedureQuery.execute(); 
			      @SuppressWarnings("unchecked")
			      List<Object[]> resultList = procedureQuery.getResultList();
			      for (Object[] r : resultList) {
			    	  //System.out.print(r[0]);
			    	 // System.out.print(r[1]);
			    	  JSONObject obj1 = new JSONObject();
					  obj1.put("DepartmentId",r[0]);
					  obj1.put("DepartmentName",r[1]);					
					  ja1.add(obj1); 
			      
			      }
			     
				//return resultList;
			     return ja1.toString();
			     // return "Developer";
			
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			
			return null;	
		}
		
	
		
		@GetMapping(path = "/GetBotNamelist", produces = "application/json")
		public String getBotNameString()
	    {
		    log.info("GetBotNamelist API called");

			List<String> data = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();
			ObjectMapper mapper = new ObjectMapper();
			try {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotNameStoreProcedure_BotNameModel);		      
			      procedureQuery.execute(); 
			      @SuppressWarnings("unchecked")
			      List<Object[]> resultList = procedureQuery.getResultList();
			      for (Object[] r : resultList) {
			    	//  System.out.print(r[0]);
			    	//  System.out.print(r[1]);
			    	  JSONObject obj1 = new JSONObject();
					  obj1.put("BotId",r[0]);
					  obj1.put("BotName",r[1]);	
					  
					  ja1.add(obj1); 
			      
			      }
			     
				//return resultList;
			     return ja1.toString();
			     // return "Developer";
			
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
		
			return null;	
		}
		
		
	
		//added by kiran
		@GetMapping(path = "/GetRoleList", produces = "application/json")
		public String getRoleString()
	    {
		    log.info("GetRoleList API called");

			List<String> data = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();
			ObjectMapper mapper = new ObjectMapper();
			try {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(LoginModel.NamedQuery_RoleStoreProcedure);		      
			      procedureQuery.execute(); 
			      @SuppressWarnings("unchecked")
			      List<Object[]> resultList = procedureQuery.getResultList();
			      for (Object[] r : resultList) {
			    	 // System.out.print(r[0]);
			    	//  System.out.print(r[1]);
			    	  JSONObject obj1 = new JSONObject();
					  obj1.put("role_id",r[0]);
					  obj1.put("name",r[1]);					
					  ja1.add(obj1);  
			      
			      }
			     
				//return resultList;
			     return ja1.toString();
			     // return "Developer";
			
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			
			return null;
		}
	
		//added by kiran
		@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
		public String registerData(@RequestBody String postData) throws Exception {
		    log.info("register API called with data: {}", postData);

			List<String> data1 = new ArrayList<String>();
			JSONArray ja1 = new JSONArray();
			try {

				String[] arrOfStr = postData.split(",");
				System.out.println("arrOfStr:::::"+arrOfStr[4].toString());
				String id = "" + "'" + arrOfStr[0].toString() + "'";
				id = id.replace("{", "").replace("\"", "").replace("'", "");
				String[] arrOfIStr = id.split(":");
				id = "" + "'" + arrOfIStr[1].toString() + "'";
				id = id.replace("{", "").replace("\"", "").replace("'", "");


				String username = "" + "'" + arrOfStr[1].toString() + "'";
				username = username.replace("{", "").replace("\"", "").replace("'", "");
				String[] arrOfUStr = username.split(":");
				username = "" + "'" + arrOfUStr[1].toString() + "'";
				username = username.replace("{", "").replace("\"", "").replace("'", "");
				 System.out.println(username);

				String password = "" + "'" + arrOfStr[2].toString() + "'";
				password = password.replace("{", "").replace("\"", "").replace("'", "");
				String[] arrOfPStr = password.split(":");
				password = "" + "'" + arrOfPStr[1].toString() + "'";
				password = password.replace("{", "").replace("\"", "").replace("'", "");
				// System.out.print(Integer.parseInt(password));
				 System.out.println(password + "password");
				 
				 String retry_pass = "" + "'" + arrOfStr[3].toString() + "'";
				 retry_pass = retry_pass.replace("{", "").replace("\"", "").replace("'", "");
				 String[] arrOfAStr = retry_pass.split(":");
				 retry_pass = "" + "'" + arrOfAStr[1].toString() + "'";
				 retry_pass = retry_pass.replace("{", "").replace("\"", "").replace("'", "");
				 // System.out.print(Integer.parseInt(retry_pass));
				 System.out.println(retry_pass + "retry_pass");

				String role_id = "" + "'" + arrOfStr[4].toString() + "'";
				role_id = role_id.replace("}", "").replace("\"", "").replace("'", "");
				String[] arrOfRStr = role_id.split(":");
				role_id = "" + "'" + arrOfRStr[1].toString() + "'";
				role_id = role_id.replace("}", "").replace("\"", "").replace("'", "");
				// System.out.print(Integer.parseInt(role_id));
				 System.out.println(role_id + "role_id");

				

				EntityManager entityManager = entityManagerFactory.createEntityManager();
				StoredProcedureQuery procedureQuery = entityManager
						.createNamedStoredProcedureQuery(LoginModel.NamedQuery_RoleInsertStoreProcedure);
				System.out.println("procedureQuery: "+procedureQuery);
				entityManager.getTransaction().begin();
				
				procedureQuery.setParameter("username", username);
				procedureQuery.setParameter("password", password);
				procedureQuery.setParameter("role_id", Integer.parseInt(role_id));
				procedureQuery.setParameter("CreatedBy", "Kiran");
				procedureQuery.setParameter("UpdatedBy", "Aishwarya");
				procedureQuery.setParameter("retry_pass", retry_pass);
				procedureQuery.setParameter("ActionType", "Insert");

				procedureQuery.execute();
				entityManager.getTransaction().commit();
				entityManager.close();
				// @SuppressWarnings("unchecked");
				System.out.print("inserted successfully user register");

				return "{    \r\n" + "  			\"RoleId\" : \"Inserted Sucessfully\"\r\n" + "  			}";

			} catch (Exception e) {
				return "Somthing went Wrong";
			}
		
		}
	
		
		//added by kiran
		@GetMapping("/UserList")
			public ModelAndView userList() {
		    log.info("UserList API called");

			ModelAndView mav = new ModelAndView("UserList");	
			return mav;
		}


		//added by kiran
		//start get method

						@GetMapping(path = "/UserListData", produces = "application/json")
							public String getUserString() {
						    log.info("UserListData API called");

								List<String> data = new ArrayList<String>();
								JSONArray ja1 = new JSONArray();
								ObjectMapper mapper = new ObjectMapper();
								try {
									EntityManager entityManager = entityManagerFactory.createEntityManager();
									StoredProcedureQuery procedureQuery = entityManager
											.createNamedStoredProcedureQuery(LoginModel.NamedQuery_UserListStoreProcedure);
									procedureQuery.execute();
									@SuppressWarnings("unchecked")
									List<Object[]> resultList = procedureQuery.getResultList();
									System.out.println(resultList);
									
									for (Object[] r : resultList) {
										
										JSONObject obj1 = new JSONObject();
										
										obj1.put("id", r[0]);
										obj1.put("username", r[1]);
										obj1.put("name", r[2]);
										obj1.put("CreatedDate", r[3]);
										obj1.put("CreatedBy", r[4]);
										obj1.put("UpdatedDate", r[5]);
										obj1.put("UpdatedBy", r[6]);
										
										ja1.add(obj1);
										
									
									
									
									// return "Developer";
									}
									return ja1.toString();
								} catch (Exception e) {
									e.printStackTrace();

								}
								return null;
							}

							// End getmethod

		
	}
		



package com.example.automation.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.example.automation.Model.DepartmentModel;
import com.example.automation.Model.LoginModel;
import com.example.automation.Repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/DepartmentController1")
public class DepartmentController {
	
	@Autowired
	
	private DepartmentRepository departmentrepository;
	
	
@GetMapping("/EditDepartment")
    
    public ModelAndView Edit() {
     ModelAndView mav = new ModelAndView("DepartmentEdit");
        mav.addObject("user", new LoginModel());
        return mav;
    }

@GetMapping("/AddDepartment")

public ModelAndView AddDepartment() {
 ModelAndView mav = new ModelAndView("DepartmentMaster");
    mav.addObject("user", new LoginModel());
    return mav;
}
	
	private static EntityManagerFactory entityManagerFactory =
	          Persistence.createEntityManagerFactory("automation");
	

			@RequestMapping(value="/AllDepartment", method = RequestMethod.GET)
			public String fetchDepartments() {
				return departmentrepository.fetchDepartment();
			  }
			
			@GetMapping(path = "/DepartmentDetails", produces = "application/json")
			public String getDeparrmentString()
		    {
				return "";
			}


//Comment Post Method Start

@PostMapping(path = "/PostInsertDepartmentData", consumes = "application/json", produces = "application/json")
public String PostDataChartResponse(			
		@RequestBody String postData) throws Exception {
		//System.out.print(postData);
		
		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();		
		
		try {
			/*
			 * String[] arrOfStr = postData.split(":"); String DepartmentName = ""+"'" +
			 * arrOfStr[1].toString() + "'"; DepartmentName=DepartmentName.replace("}",
			 * "").replace("\"","").replace("'", "");
			 */
			

	        String[] arrOfStr = postData.split(":");
	        String DepartmentName = arrOfStr[1].trim().replace("\"", "").replace("}", "");
	         arrOfStr = DepartmentName.split(",");
	         DepartmentName = arrOfStr[0].trim();

			
			//System.out.print(DepartmentName);
			
					EntityManager entityManager = entityManagerFactory.createEntityManager();
		      StoredProcedureQuery procedureQuery =
		              entityManager.createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentInsertStoreProcedure);	
		   Date date =new Date();
		      entityManager.getTransaction().begin();
		      procedureQuery.setParameter("department_id",6);
		      procedureQuery.setParameter("DepartmentName",DepartmentName);
		      procedureQuery.setParameter("CreatedDate",date);
		      procedureQuery.setParameter("CreatedBy","Sakshi");
		      procedureQuery.setParameter("UpdatedDate",date); 
		      procedureQuery.setParameter("UpdatedBy","Sakshi");
		      
		      procedureQuery.setParameter("IsActive", true);
		      procedureQuery.setParameter("typecal","Insert"); 
		      procedureQuery.execute(); 
		      entityManager.getTransaction().commit();
		      entityManager.close();
		      //@SuppressWarnings("unchecked");
		      System.out.print("inserted successfully");
		     
		      
			
		      return "{    \r\n"
		      		+ "  			\"DepartmentId\" : \"Inserted Sucessfully\"\r\n"
		      		+ "  			}";		
		//}
		}
		catch (Exception e) {
		// TODO: handle exception
			return "Somthing went Wrong";
		}
}

// Added by 'Vaibhav'
@PostMapping(path = "/importExcel", consumes = "multipart/form-data", produces = "text/plain")
public String importExcel(@RequestParam("DepartmentData") MultipartFile file, HttpServletResponse response) {
    if (file.isEmpty()) {
        return "No file uploaded";
    }

    try (InputStream inputStream = file.getInputStream()) {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // Assuming there's only one sheet

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Begin transaction
        entityManager.getTransaction().begin();


        boolean isFirstRow = true;

        for (Row row : sheet) {
            if (isFirstRow) {
                isFirstRow = false;
                continue; // Skip header row
            }
            
        	Cell cell = row.getCell(0); 
            // Extract data from cells
        	int id = 0;
			if (cell.getCellType() == CellType.NUMERIC) {
				id = (int) cell.getNumericCellValue();
			} else if (cell.getCellType() == CellType.STRING) {
				// Handle string value, maybe parse or convert it to numeric
				id = Integer.parseInt(cell.getStringCellValue());
			} else {
				// Handle other cell types if needed
			}
            String DepartmentName = row.getCell(1).getStringCellValue(); // Assuming DepartmentName is in the second column
            cell = row.getCell(2);
            Date date = null;

			if (cell != null) {
				if (cell.getCellType() == CellType.NUMERIC) {
					// Cell contains a numeric value, convert it to a Date
					date = cell.getDateCellValue();
				} else if (cell.getCellType() == CellType.STRING) {
					// Cell contains a string value, parse it into a Date if it represents a date
					try {
						date = new SimpleDateFormat("MMMM dd yyyy").parse(cell.getStringCellValue());
					} catch (ParseException e) {
						// Handle parsing error or set date to null
						e.printStackTrace();
					}
				} else {
					// Handle other cell types if needed
				}
			} else {
				// Handle null cell, maybe set date to null or provide default value
			}

			// Use the retrieved date value or handle null if date is null // Assuming date
			// is in third column
			cell = row.getCell(6); // Assuming it's the third column
			Date date2 = null;

			if (cell != null) {
				if (cell.getCellType() == CellType.NUMERIC) {
					// Cell contains a numeric value, convert it to a Date
					date2 = cell.getDateCellValue();
				} else if (cell.getCellType() == CellType.STRING) {
					// Cell contains a string value, parse it into a Date if it represents a date
					try {
						date2 = new SimpleDateFormat("MMMM dd yyyy").parse(cell.getStringCellValue());
					} catch (ParseException e) {
						// Handle parsing error or set date to null
						e.printStackTrace();
					}
				} else {
					// Handle other cell types if needed
				}
			} else {
				// Handle null cell, maybe set date to null or provide default value
			}
			
            String createdBy = "Sakshi"; // Assuming createdBy is constant
            String updatedBy = "Sakshi"; // Assuming updatedBy is constant
            String Active = row.getCell(8).getStringCellValue();
			cell = row.getCell(2); // Assuming it's the third column
            String typeCal = "Insert"; // Assuming typeCal is constant
        	String CreatedBy = row.getCell(3).getStringCellValue();

			String UpdatedBy = row.getCell(5).getStringCellValue();

            // Create named stored procedure query for insertion
            StoredProcedureQuery procedureQuery = entityManager
                    .createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentExcelInsertStoreProcedure);

            // Set parameters for the stored procedure
            procedureQuery.setParameter("department_id", id);
            procedureQuery.setParameter("DepartmentName", DepartmentName);
            procedureQuery.setParameter("CreatedDate", date);
            procedureQuery.setParameter("CreatedBy", CreatedBy);
            procedureQuery.setParameter("UpdatedDate", date2);
            procedureQuery.setParameter("UpdatedBy", UpdatedBy);
            procedureQuery.setParameter("isActive", Boolean.parseBoolean(Active));
          

            // Execute stored procedure
            procedureQuery.execute();
        }

        // Commit transaction
        entityManager.getTransaction().commit();
        entityManager.close();
        workbook.close();

        return "Data imported successfully";
    } catch (IOException e) {
        e.printStackTrace();
        return "Error occurred during import: " + e.getMessage();
    }
}
//Added by 'Vaibhav'
//for update   
@PostMapping(path = "/PostUpdateDepartmentData", consumes = "application/json", produces = "application/json")
public String UpdateDataChartResponse(			
		@RequestBody String postData) throws Exception {
		//System.out.print(postData);
		
		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();		
		
		try {
			//System.out.print(postData);
			String[] arrOfStr = postData.split(":");
			String DepartmentId = ""+"'" + arrOfStr[1].toString() + "'";
			DepartmentId=DepartmentId.replace("}", "").replace("\"","").replace("'", "");
			DepartmentId=DepartmentId.replace("DepartmentName", "").replace(",","").replace("'", "");
			//System.out.print(DepartmentId);
			
			String DepartmentName = ""+"'" + arrOfStr[2].toString() + "'";
			DepartmentName=DepartmentName.replace("}", "").replace("\"","").replace("'", "");
			DepartmentName=DepartmentName.replace("IsActive", "").replace(",","").replace("'", "");
			//System.out.print(LocationName);

			String IsActive = ""+"'" + arrOfStr[3].toString() + "'";
			IsActive=IsActive.replace("}", "").replace("\"","").replace("'", "");
			//System.out.print(IsActive);
			

			boolean _IsActive = true;
			if (IsActive.equals("0")) {
			    _IsActive = false;
			} else if (IsActive.equals("1")) {
			    _IsActive = true;
			}
			
					EntityManager entityManager = entityManagerFactory.createEntityManager();
		      StoredProcedureQuery procedureQuery =
		              entityManager.createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentUpdateStoreProcedure);	
		   Date date =new Date();
		      entityManager.getTransaction().begin();
		      procedureQuery.setParameter("p_DepartmentId", Integer.parseInt(DepartmentId));
		    
		      procedureQuery.setParameter("p_DepartmentName",DepartmentName);
		      procedureQuery.setParameter("p_CreatedDate",date);
		      procedureQuery.setParameter("p_CreatedBy","Sakshi");
		      procedureQuery.setParameter("p_UpdatedDate",date); 
		      procedureQuery.setParameter("p_UpdatedBy","Sakshi");
		      
		      procedureQuery.setParameter("p_IsActive", _IsActive);
		     
		      procedureQuery.execute(); 
		      entityManager.getTransaction().commit();
		      entityManager.close();
		      //@SuppressWarnings("unchecked");
		      System.out.print("updated successfully");
		     
		      
			
		      return "{    \r\n"
		      		+ "  			\"DepartmentId\" : \"Updated Sucessfully\"\r\n"
		      		+ "  			}";		
		//}
		}
		catch (Exception e) {
		// TODO: handle exception
			return "Somthing went Wrong";
		}
}

// Comment post method end

@GetMapping(path = "/GetDepartmentListData", produces = "application/json")
public String getdepartmetString()
{
	List<String> data = new ArrayList<String>();
	JSONArray ja1 = new JSONArray();
	ObjectMapper mapper = new ObjectMapper();
	try {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	      StoredProcedureQuery procedureQuery =
	              entityManager.createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentListStoreProcedure);		      
	      procedureQuery.execute(); 
	      @SuppressWarnings("unchecked")
	      List<Object[]> resultList = procedureQuery.getResultList();
	      for (Object[] r : resultList) {
	    	  //System.out.print(r[0]);
	    	  //System.out.print(r[1]);
	    	  JSONObject obj1 = new JSONObject();		 
	    	  obj1.put("DepartmentId",r[0]);
			  obj1.put("DepartmentName",r[1]);
			  obj1.put("CreatedDate",r[2]);			
			  obj1.put("CreatedBy",r[3]);
			  obj1.put("UpdatedDate",r[4]);
			  obj1.put("UpdatedBy",r[5]);
			  obj1.put("IsActive",r[6]);
			  ja1.add(obj1); 
	      
	      }
	     
		
	     return ja1.toString();
	     // return "Developer";
	
	}
	catch (Exception e) {
		e.printStackTrace();
		
	}
	
	return null;	
}
//-- 20 feb 2024 Vaibhav--/
@GetMapping(path = "/excel/export2", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
public void exportToExcel2(HttpServletResponse response) throws IOException, Exception {
 try (Workbook workbook = new XSSFWorkbook()) {

     // Set response headers for Excel file
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-Disposition", "attachment; filename=DepartmentData.xlsx");

     // Populate the workbook with data
     List<String> data = new ArrayList<String>();
     JSONArray ja2 = new JSONArray();
     ObjectMapper mapper = new ObjectMapper();
     try {
         EntityManager entityManager = entityManagerFactory.createEntityManager();
         StoredProcedureQuery procedureQuery = entityManager
                 .createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentListStoreProcedure);
         procedureQuery.execute();
         @SuppressWarnings("unchecked")
         List<Object[]> resultList = procedureQuery.getResultList();
         for (Object[] r : resultList) {
             JSONObject obj1 = new JSONObject();
             obj1.put("DepartmentId", r[0]);
             obj1.put("DepartmentName", r[1]);
             obj1.put("CreatedDate", r[2]);
             obj1.put("CreatedBy", r[3]);
             obj1.put("UpdatedDate", r[4]);
             obj1.put("UpdatedBy", r[5]);
             obj1.put("IsActive", r[6]);
             ja2.add(obj1);
         }

         try (OutputStream outputStream = response.getOutputStream()) {
             Sheet sheet = workbook.createSheet("DepartmentData");

             // Create header row
             Row headerRow = sheet.createRow(0);
             headerRow.createCell(0).setCellValue("DepartmentId");
             headerRow.createCell(1).setCellValue("DepartmentName");
             headerRow.createCell(2).setCellValue("CreatedDate");
             headerRow.createCell(3).setCellValue("CreatedBy");
             headerRow.createCell(4).setCellValue("UpdatedDate");
             headerRow.createCell(5).setCellValue("UpdatedBy");
             headerRow.createCell(6).setCellValue("IsActive");

             // Populate data rows
             int rowNum = 1; // Start from the second row for data
             for (Object json : ja2) {
                 JSONObject jsonObject = (JSONObject) json;
                 Row dataRow = sheet.createRow(rowNum++);
                 dataRow.createCell(0).setCellValue(String.valueOf(jsonObject.get("DepartmentId")));
                 dataRow.createCell(1).setCellValue(String.valueOf(jsonObject.get("DepartmentName")));
                 dataRow.createCell(2).setCellValue(String.valueOf(jsonObject.get("CreatedDate")));
                 dataRow.createCell(3).setCellValue(String.valueOf(jsonObject.get("CreatedBy")));
                 dataRow.createCell(4).setCellValue(String.valueOf(jsonObject.get("UpdatedDate")));
                 dataRow.createCell(5).setCellValue(String.valueOf(jsonObject.get("UpdatedBy")));
                 dataRow.createCell(6).setCellValue(String.valueOf(jsonObject.get("IsActive")));
                 // ... other cell values ...
             }

             // Write workbook to output stream
             workbook.write(outputStream);
         }

     } catch (Exception e) {
         e.printStackTrace();
         // Handle exception (e.g., log, show error message)
     }
 } catch (IOException e) {
     e.printStackTrace();
     // Handle IO exception (e.g., log, show error message)
 }
}

@PostMapping(path = "/1PostDeleteDepartmentData", consumes = "application/json", produces = "application/json")
public String PostDeleteDepartmentData1(			
	@RequestBody String postData) throws Exception {
	//System.out.print(postData);
	
	List<String> data1 = new ArrayList<String>();
	JSONArray ja1 = new JSONArray();			
			try {
				
				//System.out.print(postData);
				String[] arrOfStr = postData.split(":");
				String DepartmentId = ""+"'" + arrOfStr[1].toString() + "'";
				DepartmentId=DepartmentId.replace("}", "").replace("\"","").replace("'", "");
				//System.out.print(DepartmentId);
				
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				  StoredProcedureQuery procedureQuery =
				          entityManager.createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentDeleteStoreProcedure);	
				  			entityManager.getTransaction().begin();
				  			procedureQuery.setParameter("p_DepartmentId",Integer.parseInt(DepartmentId) );
				  			
				  			procedureQuery.execute();
				  			entityManager.getTransaction().commit();
				  			entityManager.close();
		 
		  
		
		  return "{    \r\n"
		  		+ "  			\"DepartmentId\" : \"Deleted Sucessfully\"\r\n"
		  		+ "  			}";		
		//}
		}
		catch (Exception e) {
		
			 return "{    \r\n"
				  		+ "  			\"Error\" : \"Something Went Wrong\"\r\n" + e 
				  		+ "  			}";		
		

		
		}
	
	}


@PostMapping(path = "/lPostEditDepartmentData", consumes = "application/json", produces = "application/json")
public Object lPostEditDepartmentData1(			
	@RequestBody String postData,HttpResponse response) throws Exception {
	//System.out.print(postData);
	
	List<String> data1 = new ArrayList<String>();
	JSONArray ja1 = new JSONArray();	
	String[] arrOfStr = postData.split(":");
	String DepartmentId = ""+"'" + arrOfStr[1].toString() + "'";
	DepartmentId=DepartmentId.replace("}", "").replace("\"","").replace("'", "");
	//System.out.print(DepartmentId);
	
	ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    return modelAndView;
    
	
	}

@RequestMapping(value = "/departmentget/{id}", method = RequestMethod.GET)
public ModelAndView EditDepartment() {
    ModelAndView mav = new ModelAndView("EditDepartment");
       return mav;
   }

@RequestMapping("/")
public ModelAndView index () {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    return modelAndView;
}

@PostMapping(path = "/PostEditDepartmentData", consumes = "application/json", produces = "application/json")
public String PostDepartmentDataResponse(			
	@RequestBody String postData) throws Exception {
	//System.out.print(postData);
	
	List<String> data1 = new ArrayList<String>();
	JSONArray ja1 = new JSONArray();			
			try {
				
				//System.out.print(postData);
				String[] arrOfStr = postData.split(":");
				String DepartmentId = ""+"'" + arrOfStr[1].toString() + "'";
				DepartmentId=DepartmentId.replace("}", "").replace("\"","").replace("'", "");
				//System.out.print(DepartmentId);
				//code 
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentEditStoreProcedure);		
			      procedureQuery.setParameter("iDepartmentId", Integer.parseInt(DepartmentId));
			      procedureQuery.execute(); 
			      
			      List<Object[]> resultList = procedureQuery.getResultList();
			      for (Object[] r : resultList) {
			    	  //System.out.print(r[1]);
			    	  //System.out.print(r[2]);
			    	  JSONObject obj1 = new JSONObject();
					  //obj1.put("total_Bot_count",r[0]);
					  obj1.put("DepartmentName",r[1]);	
					  obj1.put("IsActive",r[6]);
					  ja1.add(obj1); 
			      }
				
			      return ja1.toString();
			      
				
		//}
		}
		catch (Exception e) {
		
			 return "{    \r\n"
				  		+ "  			\"Error\" : \"Something Went Wrong\"\r\n" + e 
				  		+ "  			}";		
		
		
		
		}
	
	}

public static boolean stringToBoolean(String str)
{

    // Converting a given string to its primitive
    // boolean value using parseBoolean() method
    boolean b1 = Boolean.parseBoolean(str);

    // Return primitive boolean value
    return b1;
}


@PostMapping(path = "/PostEditDDepartmentData", consumes = "application/json", produces = "application/json")
public String PostEditDDepartmentData(			
	@RequestBody String postData) throws Exception {
	//System.out.print(postData);
	
	List<String> data1 = new ArrayList<String>();
	JSONArray ja1 = new JSONArray();			
			try {
				
				//System.out.print(postData);
				String[] arrOfStr = postData.split(":");
			String DepartmentId = ""+"'" + arrOfStr[1].toString() + "'";
				DepartmentId=DepartmentId.replace("}", "").replace("\"","").replace("'", "");
				DepartmentId=DepartmentId.replace("DepartmentName", "").replace(",","").replace("'", "");
				//System.out.print(DepartmentId);
				
				String DepartmentName = ""+"'" + arrOfStr[2].toString() + "'";
				DepartmentName=DepartmentName.replace("}", "").replace("\"","").replace("'", "");
				DepartmentName=DepartmentName.replace("IsActive", "").replace(",","").replace("'", "");
				//System.out.print(LocationName);
				
				String IsActive = ""+"'" + arrOfStr[3].toString() + "'";
				IsActive=IsActive.replace("}", "").replace("\"","").replace("'", "");
				System.out.print(IsActive);
				
				boolean _bIActive;
				
				if(IsActive.contains("1"))
				{
					//System.out.print("I am in true");
					_bIActive = true;
				}
				else
				{
					//System.out.print("I am in false");
					_bIActive = false;
				}
				
				//System.out.print(_bIActive);
				
				EntityManager entityManager = entityManagerFactory.createEntityManager();
			      StoredProcedureQuery procedureQuery =
			              entityManager.createNamedStoredProcedureQuery(DepartmentModel.NamedQuery_DepartmentInsertStoreProcedure);	
			   
			      entityManager.getTransaction().begin();
			      procedureQuery.setParameter("iDepartmentId", Integer.parseInt(DepartmentId));
			      procedureQuery.setParameter("DepartmentName",DepartmentName);
			      procedureQuery.setParameter("CreatedBy","Sakshi");
			      procedureQuery.setParameter("UpdatedBy","Sakshi");
			      procedureQuery.setParameter("iActive", _bIActive);
			      procedureQuery.setParameter("ActionType","Update"); 
			      procedureQuery.execute(); 
			      entityManager.getTransaction().commit();
			      entityManager.close();
			      //@SuppressWarnings("unchecked");
			     
		 
		  
		
		  return "{    \r\n"
		  		+ "  			\"DepartmentId\" : \"Edited Sucessfully\"\r\n"
		  		+ "  			}";		
		//}
		}
		catch (Exception e) {
		
			 return "{    \r\n"
				  		+ "  			\"Error\" : \"Something Went Wrong\"\r\n" + e 
				  		+ "  			}";		
		
		
		
		}
	
	}


}


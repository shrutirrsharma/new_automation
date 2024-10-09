package com.example.automation.Controller;

import java.io.IOException;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.automation.Model.BotNameModel;
import com.example.automation.Model.LoginModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/BotNameValues")
public class BotNameController {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("automation");
	@GetMapping("/EditBot")

	public ModelAndView Edit() {
		ModelAndView mav = new ModelAndView("EditBotDetails");
		mav.addObject("user", new LoginModel());
		return mav;
	}

	@GetMapping("/StartBot")

	public ModelAndView StartBot() {
		ModelAndView mav = new ModelAndView("Upload");
		mav.addObject("user", new LoginModel());
		return mav;
	}
	@GetMapping("/InsertBot")
	public ModelAndView InsertBot() {
		ModelAndView mav = new ModelAndView("InsertBotDetail");
		mav.addObject("user", new LoginModel());
		return mav;
	}

	

	@RequestMapping(value = "/AllBotnameValue", method = RequestMethod.GET)
	public String fetchBotNames() {
		// return ibotnamerepos.fetchBotName();
		return "BotName";
	}

	@GetMapping(path = "/GetBotNameDetails", produces = "application/json")
	public String getBotNameString() {
		List<String> data = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotAllStoreProcedure_BotNameModel);
			procedureQuery.execute();
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = procedureQuery.getResultList();
			for (Object[] r : resultList) {
				// System.out.print(r[0]);
				// System.out.print(r[1]);
				JSONObject obj1 = new JSONObject();
				obj1.put("BotId", r[0]);
				obj1.put("BotName", r[1]);
				obj1.put("LocactionId", r[2]);
				obj1.put("DepartmentId", r[3]);
				obj1.put("CreateDate", r[4]);
				obj1.put("CreatedBy", r[5]);
				obj1.put("UpdatedDate", r[6]);
				obj1.put("UpdatedBy", r[7]);
				obj1.put("IsActive", r[8]);
				ja1.add(obj1);

			}
			// return resultList;
			return ja1.toString();
			// return "Developer";

		} catch (Exception e) {
			e.printStackTrace();

		} 
		return null;
	}

	// -- 20 feb 2024 Vaibhav--/
	@GetMapping(path = "/excel/export", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public void exportToExcel(HttpServletResponse response) throws IOException, Exception {
	    try (Workbook workbook = new XSSFWorkbook()) {
	
	        // Set response headers for Excel file
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename=BotData.xlsx");
	
	        // Populate the workbook with data
	        List<String> data = new ArrayList<String>();
	        JSONArray ja1 = new JSONArray();
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            EntityManager entityManager = entityManagerFactory.createEntityManager();
	            StoredProcedureQuery procedureQuery = entityManager
	                    .createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotAllStoreProcedure_BotNameModel);
	            procedureQuery.execute();
	            @SuppressWarnings("unchecked")
	            List<Object[]> resultList = procedureQuery.getResultList();
	            for (Object[] r : resultList) {
	                JSONObject obj1 = new JSONObject();
	                obj1.put("BotId", r[0]);
	                obj1.put("BotName", r[1]);
	                obj1.put("LocactionId", r[2]);
	                obj1.put("DepartmentId", r[3]);
	                obj1.put("CreateDate", r[4]);
	                obj1.put("CreatedBy", r[5]);
	                obj1.put("UpdatedDate", r[6]);
	                obj1.put("UpdatedBy", r[7]);
	                obj1.put("IsActive", r[8]);
	                ja1.add(obj1);
	            }
	
	            try (OutputStream outputStream = response.getOutputStream()) {
	                Sheet sheet = workbook.createSheet("BotData");
	
	                // Create header row
	                Row headerRow = sheet.createRow(0);
	                headerRow.createCell(0).setCellValue("BotId");
	                headerRow.createCell(1).setCellValue("BotName");
	                headerRow.createCell(2).setCellValue("LocactionId");
	                headerRow.createCell(3).setCellValue("DepartmentId");
	                headerRow.createCell(4).setCellValue("CreateDate");
	                headerRow.createCell(5).setCellValue("CreatedBy");
	                headerRow.createCell(6).setCellValue("UpdatedDate");
	                headerRow.createCell(7).setCellValue("UpdatedBy");
	                headerRow.createCell(8).setCellValue("IsActive");
	
	                // Populate data rows
	                int rowNum = 1; // Start from the second row
	                for (Object json : ja1) {
	                    JSONObject jsonObject = (JSONObject) json;
	                    Row dataRow = sheet.createRow(rowNum++);
	                    dataRow.createCell(0).setCellValue(String.valueOf(jsonObject.get("BotId")));
	                    dataRow.createCell(1).setCellValue(String.valueOf(jsonObject.get("BotName")));
	                    dataRow.createCell(2).setCellValue(String.valueOf(jsonObject.get("LocactionId")));
	                    dataRow.createCell(3).setCellValue(String.valueOf(jsonObject.get("DepartmentId")));
	                    dataRow.createCell(4).setCellValue(String.valueOf(jsonObject.get("CreateDate")));
	                    dataRow.createCell(5).setCellValue(String.valueOf(jsonObject.get("CreatedBy")));
	                    dataRow.createCell(6).setCellValue(String.valueOf(jsonObject.get("UpdatedDate")));
	                    dataRow.createCell(7).setCellValue(String.valueOf(jsonObject.get("UpdatedBy")));
	                    dataRow.createCell(8).setCellValue(String.valueOf(jsonObject.get("IsActive")));
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
	} // Comment Post Method Start
	@PostMapping(path = "/PostInsertBotData", consumes = "application/json", produces = "application/json")
	public String PostDataChartResponse(@RequestBody String postData) throws Exception {
		List<String> data1 = new ArrayList<String>();

		JSONArray ja1 = new JSONArray();
		try {

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotInsertStoreProcedure_BotNameModel);

			entityManager.getTransaction().begin();

			Date date = new Date();

			procedureQuery.setParameter("BotName", "Bot10");
			procedureQuery.setParameter("LocationId", 104);
			procedureQuery.setParameter("DepartmentId", 1);
			procedureQuery.setParameter("CreatedBy", "Sakshi");

			procedureQuery.setParameter("UpdatedBy", "Priyanka");

			procedureQuery.setParameter("isActive", true);
			procedureQuery.setParameter("ActionType", "Insert");

			procedureQuery.execute();
			entityManager.getTransaction().commit();
			entityManager.close();
			// @SuppressWarnings("unchecked");
			System.out.print("inserted successfully");

			return "{    \r\n" + "  			\"BotId\" : \"Inserted Sucessfully\"\r\n" + "  			}";
			// }
		} catch (Exception e) {
			// TODO: handle exception
			return "Somthing went Wrong";
		}

	}

	// Comment post method end

	// start getmethod

	@GetMapping(path = "/GetBotListData", produces = "application/json")
	public String getbotString() {
		List<String> data = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotListStoreProcedure_BotNameModel);
			procedureQuery.execute();
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = procedureQuery.getResultList();
			for (Object[] r : resultList) {
				// System.out.print(r[0]);
				// System.out.print(r[1]);
				JSONObject obj1 = new JSONObject();
				obj1.put("BotId", r[0]);
				obj1.put("BotName", r[1]);
				obj1.put("LocationName", r[3]);
				obj1.put("DepartmentName", r[5]);
				obj1.put("CreatedDate", r[6]);
				obj1.put("CreatedBy", r[7]);
				obj1.put("UpdatedDate", r[8]);
				obj1.put("UpdatedBy", r[9]);
				obj1.put("IsActive", r[10]);
				ja1.add(obj1);

			}

			return ja1.toString();
			// return "Developer";

		} catch (Exception e) {
			e.printStackTrace();

		} 
		return null;
	}

	// End getmethod

	@PostMapping(path = "/lPostDeletBotData", consumes = "application/json", produces = "application/json")
	public String PostDeleteLocatioData1(@RequestBody String postData) throws Exception {
		// System.out.print(postData);

		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {

			// System.out.print(postData);
			String[] arrOfStr = postData.split(":");
			String BotId = "" + "'" + arrOfStr[1].toString() + "'";
			BotId = BotId.replace("}", "").replace("\"", "").replace("'", "");
			// System.out.print(BotId);

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotDeleteStoreProcedure_BotNameModel);
			entityManager.getTransaction().begin();
			procedureQuery.setParameter("p_BotId", Integer.parseInt(BotId));

			procedureQuery.execute();
			entityManager.getTransaction().commit();
			entityManager.close();

			return "{    \r\n" + "  			\"BotId\" : \"Deleted Sucessfully\"\r\n" + "  			}";
			// }
		} catch (Exception e) {

			return "{    \r\n" + "  			\"Error\" : \"Something Went Wrong\"\r\n" + e + "  			}";

		}

	}

	@PostMapping(path = "/lPostEditBotData", consumes = "application/json", produces = "application/json")
	public Object lPostEditLocationData1(@RequestBody String postData, HttpResponse response) throws Exception {
		// System.out.print(postData);

		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		String[] arrOfStr = postData.split(":");
		String BotId = "" + "'" + arrOfStr[1].toString() + "'";
		BotId = BotId.replace("}", "").replace("\"", "").replace("'", "");
		// System.out.print(BotId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;

	}

	@RequestMapping(value = "/botget/{id}", method = RequestMethod.GET)
	public ModelAndView EditLocation() {
		ModelAndView mav = new ModelAndView("EditBot");
		return mav;
	}

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@PostMapping(path = "/PostNewBotData", consumes = "application/json", produces = "application/json")
	public String PostBotDataResponse(@RequestBody String postData) throws Exception {
		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {

			String[] arrOfStr = postData.split(",");
			String BotId = "" + "'" + arrOfStr[0].toString() + "'";
			BotId = BotId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfBStr = BotId.split(":");
			BotId = "" + "'" + arrOfBStr[1].toString() + "'";
			BotId = BotId.replace("{", "").replace("\"", "").replace("'", "");

			// System.out.print(Integer.parseInt(BotId));

			String BotName = "" + "'" + arrOfStr[1].toString() + "'";
			BotName = BotName.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfBNStr = BotName.split(":");
			BotName = "" + "'" + arrOfBNStr[1].toString() + "'";
			BotName = BotName.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(BotName);

			String LocationId = "" + "'" + arrOfStr[2].toString() + "'";
			LocationId = LocationId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfLCStr = LocationId.split(":");
			LocationId = "" + "'" + arrOfLCStr[1].toString() + "'";
			LocationId = LocationId.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(LocationId));
			// System.out.print(LocationId + "Location Id");

			String DepartmentId = "" + "'" + arrOfStr[3].toString() + "'";
			DepartmentId = DepartmentId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfDLStr = DepartmentId.split(":");
			DepartmentId = "" + "'" + arrOfDLStr[1].toString() + "'";
			DepartmentId = DepartmentId.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(DepartmentId));
			// System.out.print(DepartmentId + "Deparment Id");

			String IsActive = "" + "'" + arrOfStr[4].toString() + "'";
			IsActive = IsActive.replace("}", "").replace("\"", "").replace("'", "");
			String[] arrOfAStr = IsActive.split(":");
			IsActive = "" + "'" + arrOfAStr[1].toString() + "'";
			IsActive = IsActive.replace("}", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(IsActive));
			// System.out.print(IsActive + "IsActive");
			
			
			

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotInsertStoreProcedure_BotNameModel);

			entityManager.getTransaction().begin();
			Date date = new Date();

			procedureQuery.setParameter("BotName", BotName);
			procedureQuery.setParameter("LocationId", Integer.parseInt(LocationId));
			procedureQuery.setParameter("DepartmentId", Integer.parseInt(DepartmentId));
			procedureQuery.setParameter("CreatedBy", "Sakshi");

			procedureQuery.setParameter("UpdatedBy", "Priyanka");

			procedureQuery.setParameter("isActive",Boolean.parseBoolean(IsActive) );
			
			procedureQuery.setParameter("ActionType", "Insert");
			
			/* procedureQuery.setParameter("DurationtId",Integer.parseInt(DurationtId)); */
			

			procedureQuery.execute();
			entityManager.getTransaction().commit();
			entityManager.close();
			// @SuppressWarnings("unchecked");
			System.out.print("inserted successfully");

			return "{    \r\n" + "  			\"BotId\" : \"Inserted Sucessfully\"\r\n" + "  			}";
			// }
		} catch (Exception e) {
			// TODO: handle exception
			return "Somthing went Wrong";
		}
		// Create New Controller in BotName Controller. POst in that call the store
		// procedure which will
		// return Bot records bsed on Bot id. Return jsin array then fill the data in
		// text and Drop Dwsn ok? ok sir
	}

	
	
	
	
	// Added by 'Vaibhav'
	@PostMapping(path = "/importExcel", consumes = "multipart/form-data", produces = "text/plain")
	public String importExcel(@RequestParam("BotData") MultipartFile file, HttpServletResponse response) {
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
	    			
	    			Cell cell2 = row.getCell(2); 
	                // Extract data from cells
	            	int locationId = 0;
	    			if (cell.getCellType() == CellType.NUMERIC) {
	    				locationId = (int) cell.getNumericCellValue();
	    			} else if (cell.getCellType() == CellType.STRING) {
	    				// Handle string value, maybe parse or convert it to numeric
	    				locationId = Integer.parseInt(cell.getStringCellValue());
	    			} else {
	    				// Handle other cell types if needed
	    			}
	    			
	    			Cell cell3 = row.getCell(3); 
	                // Extract data from cells
	            	int departmentId = 0;
	    			if (cell.getCellType() == CellType.NUMERIC) {
	    				departmentId = (int) cell.getNumericCellValue();
	    			} else if (cell.getCellType() == CellType.STRING) {
	    				// Handle string value, maybe parse or convert it to numeric
	    				departmentId = Integer.parseInt(cell.getStringCellValue());
	    			} else {
	    				// Handle other cell types if needed
	    			}
	    			cell = row.getCell(4); 
	    			Date date = null;

					if (cell != null) {
						if (cell.getCellType() == CellType.NUMERIC) {
							// Cell contains a numeric value, convert it to a Date
							date = cell.getDateCellValue();
						} else if (cell.getCellType() == CellType.STRING) {
							try {
								date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(cell.getStringCellValue());
							} catch (java.text.ParseException e) {
								// TODO Auto-generated catch block
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
							try {
								date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(cell.getStringCellValue());
							} catch (java.text.ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							// Handle other cell types if needed
						}
					} else {
						// Handle null cell, maybe set date to null or provide default value
					}
	                // Extract data from cells
	                String botName = row.getCell(1).getStringCellValue();
	                String isActive = row.getCell(8).getStringCellValue();

	                // Create named stored procedure query for bot insertion
	                StoredProcedureQuery procedureQuery = entityManager.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotnameExcelInsertStoreProcedure);

	                // Set parameters for the stored procedure
	                procedureQuery.setParameter("BotName", botName);
	                procedureQuery.setParameter("LocationId", locationId);
	                procedureQuery.setParameter("DepartmentId", departmentId);
	                procedureQuery.setParameter("CreatedBy", "Sakshi");
	                procedureQuery.setParameter("CreatedDate", date);
	                procedureQuery.setParameter("UpdatedBy", "Priyanka");
	                procedureQuery.setParameter("UpdatedDate", date2);
	                procedureQuery.setParameter("isActive", Boolean.parseBoolean(isActive));
	               


	                // Execute stored procedure
	                procedureQuery.execute();
	            }

	            // Commit transaction
	            entityManager.getTransaction().commit();
	            entityManager.close();
	            workbook.close();

	            return "Bot data imported successfully";
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "Error occurred during import: " + e.getMessage();
	        }
	    }
	// on 06/03/24 Added by Vaibhav
	@PostMapping(path = "/PostUpdateBotData", consumes = "application/json", produces = "application/json")
	public String PostupdateBotDataResponse(@RequestBody String postData) throws Exception {
		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {

			String[] arrOfStr = postData.split(",");
			String BotId = "" + "'" + arrOfStr[0].toString() + "'";
			BotId = BotId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfBStr = BotId.split(":");
			BotId = "" + "'" + arrOfBStr[1].toString() + "'";
			BotId = BotId.replace("{", "").replace("\"", "").replace("'", "");

			// System.out.print(Integer.parseInt(BotId));

			String BotName = "" + "'" + arrOfStr[1].toString() + "'";
			BotName = BotName.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfBNStr = BotName.split(":");
			BotName = "" + "'" + arrOfBNStr[1].toString() + "'";
			BotName = BotName.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(BotName);

			String LocationId = "" + "'" + arrOfStr[2].toString() + "'";
			LocationId = LocationId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfLCStr = LocationId.split(":");
			LocationId = "" + "'" + arrOfLCStr[1].toString() + "'";
			LocationId = LocationId.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(LocationId));
			// System.out.print(LocationId + "Location Id");

			String DepartmentId = "" + "'" + arrOfStr[3].toString() + "'";
			DepartmentId = DepartmentId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfDLStr = DepartmentId.split(":");
			DepartmentId = "" + "'" + arrOfDLStr[1].toString() + "'";
			DepartmentId = DepartmentId.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(DepartmentId));
			// System.out.print(DepartmentId + "Deparment Id");

			String IsActive = "" + "'" + arrOfStr[4].toString() + "'";
			IsActive = IsActive.replace("}", "").replace("\"", "").replace("'", "");
			String[] arrOfAStr = IsActive.split(":");
			IsActive = "" + "'" + arrOfAStr[1].toString() + "'";
			IsActive = IsActive.replace("}", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(IsActive));
			// System.out.print(IsActive + "IsActive");
			
			boolean _IsActive = true;
			if (IsActive.equals("0")) {
			    _IsActive = false;
			} else if (IsActive.equals("1")) {
			    _IsActive = true;
			}
			
			
			
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotUpdateStoreProcedure_BotNameModel);

			entityManager.getTransaction().begin();
			Date date = new Date();
			procedureQuery.setParameter("p_BotId", Integer.parseInt(BotId));
			procedureQuery.setParameter("p_BotName", BotName);
			procedureQuery.setParameter("p_LocationId", Integer.parseInt(LocationId));
			procedureQuery.setParameter("p_DepartmentId", Integer.parseInt(DepartmentId));
			procedureQuery.setParameter("p_CreatedBy", "Sakshi");
            
			procedureQuery.setParameter("p_UpdatedBy", "Priyanka");

			procedureQuery.setParameter("p_isActive", _IsActive) ;

			procedureQuery.execute();
			entityManager.getTransaction().commit();
			entityManager.close();
			// @SuppressWarnings("unchecked");
			System.out.print("Updated successfully");

			return "{    \r\n" + "  			\"BotId\" : \"Updated Sucessfully\"\r\n" + "  			}";
			// }
		} catch (Exception e) {
			// TODO: handle exception
			return "Somthing went Wrong";
		}
		// Create New Controller in BotName Controller. POst in that call the store
		// procedure which will
		// return Bot records bsed on Bot id. Return jsin array then fill the data in
		// text and Drop Dwsn ok? ok sir
	}

	@PostMapping(path = "/PostBotEditDataEdit", consumes = "application/json", produces = "application/json")
	public String PostBotEditData(@RequestBody String postData) throws Exception {
		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {
			// System.out.print(postData);
			String[] arrOfStr = postData.split(":");
			String BotId = "" + "'" + arrOfStr[1].toString() + "'";
			BotId = BotId.replace("}", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(BotId) + "Valid");

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotIdtoreProcedure_BotNameModel);
			procedureQuery.setParameter("iBotId", Integer.parseInt(BotId));
			procedureQuery.execute();
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = procedureQuery.getResultList();
			for (Object[] r : resultList) {

				JSONObject obj1 = new JSONObject();
				obj1.put("BotId", r[0]);
				obj1.put("BotName", r[1]);
				obj1.put("LocationId", r[2]);
				obj1.put("DepartmentId", r[3]);
				obj1.put("IsActive", r[6]);
				ja1.add(obj1);
			}

			return ja1.toString();

		} catch (Exception e) {
			// TODO: handle exception
			return "{    \r\n" + "  			\"BotId\" : \"Something went wrong\"\r\n" + "  			}";
		}

	}

	@PostMapping(path = "/PostBotEditBotData", consumes = "application/json", produces = "application/json")
	public String PostBotListDataResponse(@RequestBody String postData) throws Exception {
		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {
			// {"BotId":"2","BotName":"TradeQuery","LocationId":"1","DepartmentId":"2","IsActive":"0"}
			// BotId:2Bot IdBotName:TradeQueryBot NameLocationId:1Location
			// IdDepartmentId:2Deparment IdIsActive:0IsActive
			// System.out.print(postData);
			String[] arrOfStr = postData.split(",");
			String BotId = "" + "'" + arrOfStr[0].toString() + "'";
			BotId = BotId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfBStr = BotId.split(":");
			BotId = "" + "'" + arrOfBStr[1].toString() + "'";
			BotId = BotId.replace("{", "").replace("\"", "").replace("'", "");

			// System.out.print(Integer.parseInt(BotId));

			String BotName = "" + "'" + arrOfStr[1].toString() + "'";
			BotName = BotName.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfBNStr = BotName.split(":");
			BotName = "" + "'" + arrOfBNStr[1].toString() + "'";
			BotName = BotName.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(BotName);

			String LocationId = "" + "'" + arrOfStr[2].toString() + "'";
			LocationId = LocationId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfLCStr = LocationId.split(":");
			LocationId = "" + "'" + arrOfLCStr[1].toString() + "'";
			LocationId = LocationId.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(LocationId));
			// System.out.print(LocationId + "Location Id");

			String DepartmentId = "" + "'" + arrOfStr[3].toString() + "'";
			DepartmentId = DepartmentId.replace("{", "").replace("\"", "").replace("'", "");
			String[] arrOfDLStr = DepartmentId.split(":");
			DepartmentId = "" + "'" + arrOfDLStr[1].toString() + "'";
			DepartmentId = DepartmentId.replace("{", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(DepartmentId));
			// System.out.print(DepartmentId + "Deparment Id");

			String IsActive = "" + "'" + arrOfStr[4].toString() + "'";
			IsActive = IsActive.replace("}", "").replace("\"", "").replace("'", "");
			String[] arrOfAStr = IsActive.split(":");
			IsActive = "" + "'" + arrOfAStr[1].toString() + "'";
			IsActive = IsActive.replace("}", "").replace("\"", "").replace("'", "");
			// System.out.print(Integer.parseInt(IsActive));
			// System.out.print(IsActive + "IsActive");

			boolean _bIActive;

			if (IsActive.contains("1")) {
				// System.out.print("I am in true");
				_bIActive = true;
			} else {
				// System.out.print("I am in false");
				_bIActive = false;
			}

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(BotNameModel.NamedQuery_BotInsertStoreProcedure_BotNameModel);

			entityManager.getTransaction().begin();
			procedureQuery.setParameter("iBotId", Integer.parseInt(BotId));
			procedureQuery.setParameter("BotName", BotName);
			procedureQuery.setParameter("iLocationId", Integer.parseInt(LocationId));
			procedureQuery.setParameter("iDepartmentId", Integer.parseInt(DepartmentId));
			procedureQuery.setParameter("CreatedBy", "Sakshi");
			procedureQuery.setParameter("UpdatedBy", "Priyanka");
			procedureQuery.setParameter("isActive", _bIActive);
			procedureQuery.setParameter("ActionType", "Update");

			procedureQuery.execute();
			entityManager.getTransaction().commit();
			entityManager.close();
			// @SuppressWarnings("unchecked");
			System.out.print("Edited successfully");

			return "{    \r\n" + "  			\"BotId\" : \"Edit Sucessfully\"\r\n" + "  			}";
			// }
		} catch (Exception e) {
			// TODO: handle exception
			return "Somthing went Wrong";
		}
	}



}

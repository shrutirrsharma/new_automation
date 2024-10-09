package com.example.automation.Controller;

import java.io.IOException
;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.example.automation.Model.LocationModel;
import com.example.automation.Model.LoginModel;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/LocationController")
public class LocationController {

	

	@GetMapping("/EditLocation")

	public ModelAndView Edit() {
		ModelAndView mav = new ModelAndView("LocationEdit");
		mav.addObject("user", new LoginModel());
		return mav;
	}

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("automation");

	@GetMapping(path = "/LocationDetails", produces = "application/json")
	public String getLocationString() {
		return "";
	}
	
	
	

	@PostMapping(path = "/importExcel", consumes = "multipart/form-data", produces = "text/plain")
	public String importExcel(@RequestParam("LocationData") MultipartFile file, HttpServletResponse response) {
		if (file.isEmpty()) {
			return "No file uploaded";
		}

		try (InputStream inputStream = file.getInputStream()) {
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0); // Assuming there's only one sheet

			EntityManager entityManager = entityManagerFactory.createEntityManager();

			// Begin transaction
			entityManager.getTransaction().begin();

			// Delete existing data if the table exists
		//	entityManager.createNativeQuery("DELETE FROM tbl_location ").executeUpdate();
			

            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Skip header row
                }
				// Extract data from cells
				Cell cell = row.getCell(0); // Assuming it's the first column
				int id = 0;
				if (cell.getCellType() == CellType.NUMERIC) {
					id = (int) cell.getNumericCellValue();
				} else if (cell.getCellType() == CellType.STRING) {
					// Handle string value, maybe parse or convert it to numeric
					id = Integer.parseInt(cell.getStringCellValue());
				} else {
					// Handle other cell types if needed
				}
				String LocationName = row.getCell(1).getStringCellValue();

				String CreatedBy = row.getCell(3).getStringCellValue();

				String UpdatedBy = row.getCell(5).getStringCellValue();

				String Active = row.getCell(6).getStringCellValue();
				cell = row.getCell(2); // Assuming it's the third column
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
				cell = row.getCell(4); // Assuming it's the third column
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
				
				/*
				 * // Create the native SQL query with a parameter placeholder and LIMIT 1
				 * String sqlQuery = "DELETE FROM tbl_location WHERE LocationId = ? LIMIT 1";
				 * 
				 * // Create a PreparedStatement with the SQL query Query query =
				 * entityManager.createNativeQuery(sqlQuery);
				 * 
				 * // Set the value of the id parameter query.setParameter(1, id);
				 * query.executeUpdate();
				 */

				// Create named stored procedure query for insertion

				StoredProcedureQuery procedureQuery = entityManager
						.createNamedStoredProcedureQuery(LocationModel.NamedQuery_LocationExcelInsertStoreProcedure);

				// Set parameters for the stored procedure
				procedureQuery.setParameter("id", 6);
				procedureQuery.setParameter("p_Location_Id", id);
				procedureQuery.setParameter("location_id", 1);
				procedureQuery.setParameter("LocationName", LocationName);
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

//Comment Post Method Start

	@PostMapping(path = "/PostInsertLocationData", consumes = "application/json", produces = "application/json")
	public String PostDataChartResponse(@RequestBody String postData) throws Exception {
		// System.out.print(postData);

		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {

			System.out.print(postData);
			String[] arrOfStr = postData.split(":");
			String LocationName = "" + "'" + arrOfStr[1].toString() + "'";
			LocationName = LocationName.replace("}", "").replace("\"", "").replace("'", "");

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(LocationModel.NamedQuery_LocationInsertStoreProcedure);
			Date date = new Date();

			final AtomicInteger counter = new AtomicInteger();
			int id = counter.incrementAndGet();

			entityManager.getTransaction().begin();
			procedureQuery.setParameter("id", 6);
			procedureQuery.setParameter("location_id", id);
			procedureQuery.setParameter("LocationName", LocationName);
			procedureQuery.setParameter("CreatedDate", date);
			procedureQuery.setParameter("CreatedBy", "Sakshi");
			procedureQuery.setParameter("UpdatedDate", date);
			procedureQuery.setParameter("UpdatedBy", "Sakshi");
			procedureQuery.setParameter("isActive", true);

			procedureQuery.execute();
			entityManager.getTransaction().commit();
			entityManager.close();
			// @SuppressWarnings("unchecked");
			System.out.print("inserted successfully");

			return "{    \r\n" + "  			\"LocationId\" : \"Inserted Sucessfully\"\r\n" + "  			}";
			// }
		} catch (Exception e) {

			return "{    \r\n" + "  			\"Error\" : \"Something Went Wrong\"\r\n" + e + "  			}";

		}

	}

	@GetMapping(path = "/GetLocationListData", produces = "application/json")
	public String getlocationString() {
		List<String> data = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(LocationModel.NamedQuery_LocationListStoreProcedure);
			procedureQuery.execute();
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = procedureQuery.getResultList();
			for (Object[] r : resultList) {
				// System.out.print(r[0]);
				// System.out.print(r[1]);
				JSONObject obj1 = new JSONObject();
				obj1.put("LocationId", r[0]);
				obj1.put("LocationName", r[1]);
				obj1.put("CreatedDate", r[2]);
				obj1.put("CreatedBy", r[3]);
				obj1.put("UpdatedDate", r[4]);
				obj1.put("UpdatedBy", r[5]);
				obj1.put("IsActive", r[6]);
				ja1.add(obj1);

			}
			return ja1.toString();
			// return "Developer";

		} catch (Exception e) {
			e.printStackTrace();

		} 
		return null;
	}

	// -- 20 feb 2024 Vaibhav--/
	@GetMapping(path = "/excel/export2", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public void exportToExcel2(HttpServletResponse response) throws IOException, Exception {
	    try (Workbook workbook = new XSSFWorkbook()) {

	        // Set response headers for Excel file
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename=LocationData.xlsx");

	        // Populate the workbook with data
	        List<String> data = new ArrayList<String>();
	        JSONArray ja2 = new JSONArray();
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            EntityManager entityManager = entityManagerFactory.createEntityManager();
	            StoredProcedureQuery procedureQuery = entityManager
	                    .createNamedStoredProcedureQuery(LocationModel.NamedQuery_LocationListStoreProcedure);
	            procedureQuery.execute();
	            @SuppressWarnings("unchecked")
	            List<Object[]> resultList = procedureQuery.getResultList();
	            for (Object[] r : resultList) {
	                JSONObject obj1 = new JSONObject();
	                obj1.put("LocationId", r[0]);
	                obj1.put("LocationName", r[1]);
	                obj1.put("CreatedDate", r[2]);
	                obj1.put("CreatedBy", r[3]);
	                obj1.put("UpdatedDate", r[4]);
	                obj1.put("UpdatedBy", r[5]);
	                obj1.put("IsActive", r[6]);
	                ja2.add(obj1);
	            }

	            try (OutputStream outputStream = response.getOutputStream()) {
	                Sheet sheet = workbook.createSheet("LocationData");

	                // Create header row
	                Row headerRow = sheet.createRow(0);
	                headerRow.createCell(0).setCellValue("LocationId");
	                headerRow.createCell(1).setCellValue("LocationName");
	                headerRow.createCell(2).setCellValue("CreatedDate");
	                headerRow.createCell(3).setCellValue("CreatedBy");
	                headerRow.createCell(4).setCellValue("UpdatedDate");
	                headerRow.createCell(5).setCellValue("UpdatedBy");
	                headerRow.createCell(6).setCellValue("IsActive");

	                // Populate data rows
	                int rowNum = 1; // Start from the second row
	                for (Object json : ja2) {
	                    JSONObject jsonObject = (JSONObject) json;
	                    Row dataRow = sheet.createRow(rowNum++);
	                    dataRow.createCell(0).setCellValue(String.valueOf(jsonObject.get("LocationId")));
	                    dataRow.createCell(1).setCellValue(String.valueOf(jsonObject.get("LocationName")));
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


	@PostMapping(path = "/lPostDeleteLocationData", consumes = "application/json", produces = "application/json")
	public String PostDeleteLocatioData1(@RequestBody String postData) throws Exception {
		// System.out.print(postData);

		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {

			// System.out.print(postData);
			String[] arrOfStr = postData.split(":");
			String LocationId = "" + "'" + arrOfStr[1].toString() + "'";
			LocationId = LocationId.replace("}", "").replace("\"", "").replace("'", "");
			System.out.print(LocationId);
			Date date = new Date();

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(LocationModel.NamedQuery_LocationDeleteStoreProcedure);
			entityManager.getTransaction().begin();

			final AtomicInteger counter = new AtomicInteger();
			int id = counter.incrementAndGet();

			procedureQuery.setParameter("p_LocationId", Integer.parseInt(LocationId));

			procedureQuery.execute();
			entityManager.getTransaction().commit();
			entityManager.close();

			return "{    \r\n" + "  			\"LocationId\" : \"Deleted Sucessfully\"\r\n" + "  			}";
			// }
		} catch (Exception e) {

			return "{    \r\n" + "  			\"Error\" : \"Something Went Wrong\"\r\n" + e + "  			}";

		}

	}

	@PostMapping(path = "/lPostEditLocationData", consumes = "application/json", produces = "application/json")
	public Object lPostEditLocationData1(@RequestBody String postData, HttpResponse response) throws Exception {
		// System.out.print(postData);

		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		String[] arrOfStr = postData.split(":");
		String LocationId = "" + "'" + arrOfStr[1].toString() + "'";
		LocationId = LocationId.replace("}", "").replace("\"", "").replace("'", "");
		System.out.print(LocationId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;

	}

	@RequestMapping(value = "/locationget/{id}", method = RequestMethod.GET)
	public ModelAndView EditLocation() {
		ModelAndView mav = new ModelAndView("EditLocation");
		return mav;
	}

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@PostMapping(path = "/PostEditLocationData", consumes = "application/json", produces = "application/json")
	public String PostLocationDataResponse(@RequestBody String postData) throws Exception {
		// System.out.print(postData);

		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {

			// System.out.print(postData);
			String[] arrOfStr = postData.split(":");
			String LocationId = "" + "'" + arrOfStr[1].toString() + "'";
			LocationId = LocationId.replace("}", "").replace("\"", "").replace("'", "");
			System.out.print(LocationId);
			// code
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(LocationModel.NamedQuery_LocationEditStoreProcedure);
			procedureQuery.setParameter("iLocationId", Integer.parseInt(LocationId));
			procedureQuery.execute();

			List<Object[]> resultList = procedureQuery.getResultList();
			for (Object[] r : resultList) {
				// System.out.print(r[1]);
				// System.out.print(r[2]);
				JSONObject obj1 = new JSONObject();
				// obj1.put("total_Bot_count",r[0]);
				obj1.put("LocationName", r[1]);
				obj1.put("IsActive", r[6]);
				ja1.add(obj1);
			}

			return ja1.toString();

			// }
		} catch (Exception e) {

			return "{    \r\n" + "  			\"Error\" : \"Something Went Wrong\"\r\n" + e + "  			}";

		}

	}

// Added by Vaibhav
	@PostMapping(path = "/PostUpdateDLocationData", consumes = "application/json", produces = "application/json")
	public String PostEditDLocationData(@RequestBody String postData) throws Exception {
		// System.out.print(postData);

		List<String> data1 = new ArrayList<String>();
		JSONArray ja1 = new JSONArray();
		try {

			// System.out.print(postData);
			String[] arrOfStr = postData.split(":");
			String LocationId = "" + "'" + arrOfStr[1].toString() + "'";
			LocationId = LocationId.replace("}", "").replace("\"", "").replace("'", "");
			LocationId = LocationId.replace("LocationName", "").replace(",", "").replace("'", "");
			// System.out.print(LocationId);

			String LocationName = "" + "'" + arrOfStr[2].toString() + "'";
			LocationName = LocationName.replace("}", "").replace("\"", "").replace("'", "");
			LocationName = LocationName.replace("IsActive", "").replace(",", "").replace("'", "");
			// System.out.print(LocationName);

			String IsActive = "" + "'" + arrOfStr[3].toString() + "'";
			IsActive = IsActive.replace("}", "").replace("\"", "").replace("'", "");
			// System.out.print(IsActive);

			boolean _IsActive = true;
			if (IsActive.equals("0")) {
				_IsActive = false;
			} else if (IsActive.equals("1")) {
				_IsActive = true;
			}

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			StoredProcedureQuery procedureQuery = entityManager
					.createNamedStoredProcedureQuery(LocationModel.NamedQuery_LocationUpdateStoreProcedure);

			entityManager.getTransaction().begin();
			procedureQuery.setParameter("p_LocationId", Integer.parseInt(LocationId));
			procedureQuery.setParameter("p_location_id", 1);
			procedureQuery.setParameter("p_LocationName", LocationName);
			procedureQuery.setParameter("p_CreatedBy", "Sakshi");
			procedureQuery.setParameter("p_UpdatedBy", "Sakshi");
			procedureQuery.setParameter("p_IsActive", _IsActive);

			procedureQuery.execute();
			entityManager.getTransaction().commit();
			entityManager.close();
			// @SuppressWarnings("unchecked");

			return "{    \r\n" + "  			\"LocationId\" : \"Edited Sucessfully\"\r\n" + "  			}";
			// }
		} catch (Exception e) {

			return "{    \r\n" + "  			\"Error\" : \"Something Went Wrong\"\r\n" + e + "  			}";

		}

	}

}

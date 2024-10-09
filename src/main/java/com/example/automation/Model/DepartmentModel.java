package com.example.automation.Model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;



@NamedStoredProcedureQuery(
	      name = DepartmentModel.NamedQuery_DepartmentNameStoreProcedure,
	      procedureName = "get_TblDepartment"
	      )

@NamedStoredProcedureQuery(
	      name = DepartmentModel.NamedQuery_DepartmentListStoreProcedure,
	      procedureName = "Usp_MstDepartment_Detail"
	      )
@NamedStoredProcedureQuery(
	      name = DepartmentModel.NamedQuery_DepartmentEditStoreProcedure,
	      procedureName = "Usp_EditDepartment",
	    		  parameters = {
	    				  @StoredProcedureParameter(mode = ParameterMode.IN, name = "iDepartmentId", type = Integer.class)
	      
	    		  })

@NamedStoredProcedureQuery(
	      name = DepartmentModel.NamedQuery_DepartmentDeleteStoreProcedure,
	      procedureName = "Usp_Delete_tbl_department",
	    		  parameters = {
	    				  @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_DepartmentId", type = Integer.class)
	      
	    		  })
@NamedStoredProcedureQuery(
		name = DepartmentModel.NamedQuery_DepartmentInsertStoreProcedure,
	      procedureName = "Usp_Insert_Department1",
	    		  parameters = {
	    				  @StoredProcedureParameter(mode = ParameterMode.IN, name = "department_id", type = Integer.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "DepartmentName", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedDate", type = Date.class) ,
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedBy", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedDate", type = Date.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedBy", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "IsActive", type = Boolean.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "typecal", type = String.class) ,
                      
                        
		})
//Added by 'Vaibhav'
@NamedStoredProcedureQuery(
		name = DepartmentModel.NamedQuery_DepartmentUpdateStoreProcedure,
	      procedureName = "Usp_Update_Department",
	    		  parameters = {
	    				  @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_DepartmentId", type = Integer.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_DepartmentName", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_CreatedDate", type = Date.class) ,
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_CreatedBy", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_UpdatedDate", type = Date.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_UpdatedBy", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_IsActive", type = Boolean.class),
                      
                        
		})
@NamedStoredProcedureQuery(name = DepartmentModel.NamedQuery_DepartmentExcelInsertStoreProcedure, procedureName = "Usp_InsertExcel_Department",
parameters = {

	
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "department_id", type = Integer.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "DepartmentName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedDate", type = Date.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedDate", type = Date.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "isActive", type = Boolean.class),

})
@Entity
@Table(name="tbl_Department")
public class DepartmentModel {
	
	public static final String NamedQuery_DepartmentNameStoreProcedure = "DepartmentStoreProcedure";
	public static final String NamedQuery_DepartmentStoreProcedure = "DepartmentAllStoreProcedure";
	public static final String NamedQuery_DepartmentInsertStoreProcedure = "DepartmentInsertStoreProcedure";
	public static final String NamedQuery_DepartmentListStoreProcedure = "DepartmentListStoreProcedure";
	public static final String NamedQuery_DepartmentDeleteStoreProcedure = "DepartmentDeleteStoreProcedure";
	public static final String NamedQuery_DepartmentEditStoreProcedure = "DepartmentEditStoreProcedure";
	public static final String NamedQuery_DepartmentUpdateStoreProcedure = "DepartmentUpdateStoreProcedure";
	public static final String NamedQuery_DepartmentExcelInsertStoreProcedure = "DepartmentExcelInsertStoreProcedure";

	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
	@Column(name = "DepartmentId")
	private int id;
	
	@Column(name = "DepartmentName")
	private String DepartmentName;
	
	public DepartmentModel(int id, String departmentName) {
		super();
		this.id = id;
		DepartmentName = departmentName;
		
	}

	public DepartmentModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	
	@Override
	public String toString() {
		return "DepartmentModel [id=" + id + ", DepartmentName=" + DepartmentName +"]";
	}
	
	
	
}

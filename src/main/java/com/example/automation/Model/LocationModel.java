package com.example.automation.Model;

import java.util.*;

import javax.persistence.*;

@NamedStoredProcedureQuery(name = LocationModel.NamedQuery_LOCATIONStoreProcedure, procedureName = "get_Tbllocation1")

@NamedStoredProcedureQuery(name = LocationModel.NamedQuery_LocationListStoreProcedure, procedureName = "Usp_LocationMst1")

@NamedStoredProcedureQuery(name = LocationModel.NamedQuery_LocationEditStoreProcedure, procedureName = "Usp_EditLocation", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "iLocationId", type = Integer.class)

})

@NamedStoredProcedureQuery(name = LocationModel.NamedQuery_LocationDeleteStoreProcedure, procedureName = "Usp_Delete_Location", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_LocationId", type = Integer.class)

})

@NamedStoredProcedureQuery(name = LocationModel.NamedQuery_LocationExcelInsertStoreProcedure, procedureName = "Usp_InsertExcel_Location", parameters = {

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "location_id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_Location_Id", type = Integer.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "LocationName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedDate", type = Date.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedDate", type = Date.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "isActive", type = Boolean.class),

})
@NamedStoredProcedureQuery(name = LocationModel.NamedQuery_LocationInsertStoreProcedure, procedureName = "Usp_Insert_Location", parameters = {

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "location_id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "LocationName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedDate", type = Date.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedDate", type = Date.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "isActive", type = Boolean.class),

})

// Added by 'Vaibhav'
@NamedStoredProcedureQuery(name = LocationModel.NamedQuery_LocationUpdateStoreProcedure, procedureName = "Usp_Update_Location", parameters = {

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_LocationId", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_location_id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_LocationName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_CreatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_UpdatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_IsActive", type = Boolean.class)

})





@Entity
@Table(name = "tbl_location")
public class LocationModel {

	public static final String NamedQuery_LOCATIONStoreProcedure = "LocationStoreProcedure";
	public static final String NamedQuery_LocationStoreProcedure = "LocationAllStoreProcedure";
	public static final String NamedQuery_LocationInsertStoreProcedure = "LocationInsertStoreProcedure";
	public static final String NamedQuery_LocationExcelInsertStoreProcedure = "LocationExcelInsertStoreProcedure";
	public static final String NamedQuery_LocationListStoreProcedure = "LocationListStoreProcedure";
	public static final String NamedQuery_LocationEditStoreProcedure = "LocationEditStoreProcedure";
	public static final String NamedQuery_LocationUpdateStoreProcedure = "LocationUpdateStoreProcedure";
	public static final String NamedQuery_LocationDeleteStoreProcedure = "LocationDeleteStoreProcedure";


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LocationId")
	private int id;

	@Column(name = "LocationName")
	private String LocationName;

	@Column(name = "CreatedDate")
	private Date CreatedDate;

	@Column(name = "CreatedBy")
	private String CreatedBy;

	@Column(name = "UpdatedDate")
	private Date UpdatedDate;

	@Column(name = "UpdatedBy")
	private String UpdatedBy;

	@Column(name = "IsActive")
	private boolean IsActive;

	public LocationModel(int id, String locationName, Date createdDate, String createdBy, Date updatedDate,
			String updatedBy, boolean isActive) {
		super();
		this.id = id;
		LocationName = locationName;
		CreatedDate = createdDate;
		CreatedBy = createdBy;
		UpdatedDate = updatedDate;
		UpdatedBy = updatedBy;
		IsActive = isActive;
	}

	public LocationModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationName() {
		return LocationName;
	}

	public void setLocationName(String locationName) {
		LocationName = locationName;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public Date getUpdatedDate() {
		return UpdatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		UpdatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

	@Override
	public String toString() {
		return "LocationModel [id=" + id + ", LocationName=" + LocationName + ", CreatedDate=" + CreatedDate
				+ ", CreatedBy=" + CreatedBy + ", UpdatedDate=" + UpdatedDate + ", UpdatedBy=" + UpdatedBy
				+ ", IsActive=" + IsActive + "]";
	}

}

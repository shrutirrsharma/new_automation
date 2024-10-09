package com.example.automation.Model;


import java.util.Date;

import javax.persistence.*;

@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotNameStoreProcedure_BotNameModel, procedureName = "get_TblBotName1")
@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotAllStoreProcedure_BotNameModel, procedureName = "get_BotDetails")

@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotListStoreProcedure_BotNameModel, procedureName = "Usp_MstBotDetail")


@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotDurationStoreProcedure_BotNameModel, procedureName = "Usp_BotDuration", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "Botname", type = String.class) })



@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotDeleteStoreProcedure_BotNameModel, procedureName = "Usp_Delete_BotName", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_BotId", type = Integer.class) })
@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotInsertStoreProcedure_BotNameModel, procedureName = "Usp_Insert_BotName1", parameters = {

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "BotName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "LocationId", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "DepartmentId", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedBy", type = String.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedBy", type = String.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "isActive", type = Boolean.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "ActionType", type = String.class),
		//@StoredProcedureParameter(mode = ParameterMode.IN, name = "DurationtId", type = Integer.class),
})

@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotIdtoreProcedure_BotNameModel, procedureName = "Usp_get_BotData", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "iBotId", type = Integer.class) })


//Added by 'Vaibhav'
@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotUpdateStoreProcedure_BotNameModel, procedureName = "Usp_Update_BotName", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_BotId", type = Integer.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_BotName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_LocationId", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_DepartmentId", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_CreatedBy", type = String.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_UpdatedBy", type = String.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_isActive", type = Boolean.class), })

@NamedStoredProcedureQuery(name = BotNameModel.NamedQuery_BotnameExcelInsertStoreProcedure, procedureName = "Usp_InsertExcel_Bot", parameters = {

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "LocationId", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "DepartmentId", type = Integer.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "BotName", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedDate", type = Date.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedDate", type = Date.class),

		@StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedBy", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "isActive", type = Boolean.class),

})
@Entity
@Table(name = "Tbl_BotName")
public class BotNameModel {

	public static final String NamedQuery_BotNameStoreProcedure_BotNameModel = "BotNameStoreProcedure";
	public static final String NamedQuery_BotAllStoreProcedure_BotNameModel = "BotAllStoreProcedure";
	public static final String NamedQuery_BotInsertStoreProcedure_BotNameModel = "BotInsertStoreProcedure";
	public static final String NamedQuery_BotListStoreProcedure_BotNameModel = "BotListStoreProcedure";
	public static final String NamedQuery_BotIdtoreProcedure_BotNameModel = "BotIdStoreProcedure";
	public static final String NamedQuery_BotDeleteStoreProcedure_BotNameModel = "BotDeleteStoreProcedure";
	public static final String NamedQuery_BotUpdateStoreProcedure_BotNameModel = "Usp_Update_BotName1";
	public static final String NamedQuery_BotnameExcelInsertStoreProcedure = "BotnameExcelInsertStoreProcedure";
	public static final String NamedQuery_BotDurationStoreProcedure_BotNameModel = "BotDurationStoreProcedure";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BotId")
	private int id;
	@Column(name = "BotName")
	private String BotName;

	public BotNameModel(int id, String botName) {
		super();
		this.id = id;
		BotName = botName;
	}

	public BotNameModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBotName() {
		return BotName;
	}

	public void setBotName(String botName) {
		BotName = botName;
	}

	@Override
	public String toString() {
		return "BotModel [id=" + id + ", BotName=" + BotName + "]";
	}

}

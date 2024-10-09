//package com.example.automation.Model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedStoredProcedureQuery;
//import javax.persistence.ParameterMode;
//import javax.persistence.StoredProcedureParameter;
//import javax.persistence.Table;
//
//@NamedStoredProcedureQuery(
//	      name = BotModel.NamedQuery_BotNameStoreProcedure_BotModel,
//	      procedureName = "get_TblBotName"
//	      )
//@NamedStoredProcedureQuery(
//	      name = BotModel.NamedQuery_BotAllStoreProcedure_BotModel,
//	      procedureName = "get_BotDetails"
//	      )
//
//
//
//@NamedStoredProcedureQuery(
//		name = BotModel.NamedQuery_BotInsertStoreProcedure_BotModel,
//	      procedureName = "Usp_Insert_BotName",
//	    		  parameters = {
//	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "BotId", type = Integer.class),
//	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "BotName", type = String.class),
//	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "LocationId", type = Integer.class),
//	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "DepartmentId", type = Integer.class),
//	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedBy", type = String.class),
//	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedBy", type = String.class),
//	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "ActionType", type = String.class) })
//	      
//
//
//@Entity
//@Table(name="Tbl_BotName")
//
//public class BotModel {
//	
//	public static final String NamedQuery_BotNameStoreProcedure_BotModel = "BotNameStoreProcedure";
//	public static final String NamedQuery_BotAllStoreProcedure_BotModel = "BotAllStoreProcedure";
//	public static final String NamedQuery_BotInsertStoreProcedure_BotModel = "BotInsertStoreProcedure";
//	
//	@Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY) 
//	 @Column(name = "BotId")
//	private int id;
//	@Column(name = "BotName")
//	private String BotName;
//	public BotModel(int id, String botName) {
//		super();
//		this.id = id;
//		BotName = botName;
//	}
//	public BotModel() {
//		super();
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getBotName() {
//		return BotName;
//	}
//	public void setBotName(String botName) {
//		BotName = botName;
//	}
//	@Override
//	public String toString() {
//		return "BotModel [id=" + id + ", BotName=" + BotName + "]";
//	}
//
//}

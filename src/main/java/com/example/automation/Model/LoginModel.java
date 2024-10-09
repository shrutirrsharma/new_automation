package com.example.automation.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQuery(
	      name = LoginModel.NamedQuery_LoginStoreProcedure,
	      procedureName = "Usp_LoginD",
	    		  parameters = {
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "pusername", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "ppassword", type = String.class)
	    	              })

//added by kiran
@NamedStoredProcedureQuery(
	      name = LoginModel.NamedQuery_RoleStoreProcedure,
	      procedureName = "get_TblRolemst"
	      )

//added by kiran
@NamedStoredProcedureQuery(
		name = LoginModel.NamedQuery_RoleInsertStoreProcedure,
	      procedureName = "Usp_Insert_UserRegister",
	    		  parameters = {
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "role_id", type = Integer.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "CreatedBy", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "UpdatedBy", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "retry_pass", type = String.class),
	    	              @StoredProcedureParameter(mode = ParameterMode.IN, name = "ActionType", type = String.class) 
	    	              })


@NamedStoredProcedureQuery(
	      name = LoginModel.NamedQuery_UserListStoreProcedure,
	      procedureName = "Usp_UserDetail"
	      )

@Entity
@Table(name="login")
public class LoginModel {
	 public static final String NamedQuery_LoginStoreProcedure = "LoginStoreProcedure";
	 public static final String NamedQuery_RoleStoreProcedure = "RoleStoreProcedure";
	 public static final String NamedQuery_RoleInsertStoreProcedure = "RoleInsertStoreProcedure";
	 public static final String NamedQuery_UserListStoreProcedure = "UserListStoreProcedure";

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private int role_id;
    private String retry_pass;    
    
    
	public String getRetry_pass() {
		return retry_pass;
	}

	public void setRetry_pass(String retry_pass) {
		this.retry_pass = retry_pass;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public LoginModel(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public LoginModel(){
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LoginModel [id=" + id + ", username=" + username + ", password=" + password + ", role_id=" + role_id
				+ ", retry_pass=" + retry_pass + "]";
	}

	
	
	
	
}

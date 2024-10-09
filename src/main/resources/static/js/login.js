
	var _varurl = window.location.host;

function Redirect()
{
    var _url = "http://" +_varurl +"/RealTimeDataValues/function-call";
	$.ajax({  
                    type : 'GET',  
                    url : _url,  
 
                });
}

function validateLogin(Obj)
{
	var _vartxtusername =  document.getElementById("txtusername").value;
	var _vartxtpassword =  document.getElementById("txtpassword").value;
	//alert(_vartxtusername);
	//return false;
	if(_vartxtusername == "")
	{
		alert("Username cannot be blank");
		document.getElementById("txtusername").focus();
		return false;
	}
	
	if(_vartxtpassword == "")
	{
		alert("Password cannot be blank");
		document.getElementById("txtpassword").focus();
		return false;
	}
	//debugger;
	var postData= {
			    "Username" : _vartxtusername,
      			"Password" :_vartxtpassword
   			}
	
	var _url = "http://" +_varurl+"/RealTimeDataValues/PostGetLoginData1";
		//var _url = "http://localhost:9082/RealTimeDataValues/PostGetLoginData1";

	//start ajax
	$.ajax({
	        url: _url,
    		type: "POST",
    		contentType : 'application/json; charset=utf-8',
       		dataType : 'json',
    		data: JSON.stringify(postData),
	        success: function(resultB) {
				///alert("I am in Success");				
				//alert(resultB[0].NotFound);
				if(resultB[0].NotFound == "Found")
				{
					//alert(resultB[0].Name);
					//sessionStorage.removeItem('sessionname');
					window.sessionStorage.setItem('sessionname',resultB[0].Name);
					window.sessionStorage.setItem('sessionRoleId',resultB[0].role_Id);
					window.location.href = "http://"+_varurl+"/Dashboard";
				}
				else
				{
					alert(resultB[0].NotFound);
				}
	        	
	        },
	        error: function(jqXHR, textStatus, errorThrown){
	        	alert(errorThrown);
	            alert(jqXHR.status + ' ' + jqXHR.responseText);
	        }
	   });
	//end ajax
	
	
}

 

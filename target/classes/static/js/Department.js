var _varurl = window.location.host;

function OpenDepart(Obj)
{
	var _url = "http://" +_varurl +"/DepartmentController1/AddDepartment";
	//window.location.href="http://localhost:9082/DepartmentController1/AddDepartment";
	window.location.href = _url;
}

/*//Added by 'Vaibhav'*/
function CreateXLSSHEETData2(Obj)
{
	var _url = "http://" +_varurl +"/DepartmentController1/excel/export2";
	//window.location.href="http://localhost:9082/DepartmentController1/excel/export2";
	window.location.href = _url;
}

function editData(Obj)
{
	//alert("Edit");
	var id = Obj.parentNode.parentNode.childNodes[0].innerHTML;
	var postData= {
						    "DepartmentId" : id
			   	  }
	var _url = "http://" +_varurl +"/DepartmentController1/EditDepartment?id=";
	//window.location.href = "http://localhost:9082/DepartmentController1/EditDepartment?id=" + id;
	window.location.href = _url + id;
}

function DeleteData(Obj)
{
	if (confirm("Are You Sure You want to Delete?") == true) {
  				//alert(Obj.parentNode.parentNode    rd\     hildNodes[0].innerHTML);
			var _id = Obj.parentNode.parentNode.childNodes[0].innerHTML;
	//alert(_id);
	
					var postData= {
						    "DepartmentId" : _id
			   			}
			   			//alert(_id);	
					var _url = "http://" +_varurl +"/DepartmentController1/1PostDeleteDepartmentData";
			    		$.ajax({
				        //url: "http://localhost:9082/DepartmentController1/1PostDeleteDepartmentData",
			    		url: _url,
			    		type: "POST",
			    		contentType : 'application/json; charset=utf-8',
			       		dataType : 'json',
			    		data: JSON.stringify(postData),
				        success: function(resultB) {
							alert("Deleted Sucessfully !!");
				        	window.location.reload();
				        	
				          
				        },
				        error: function(jqXHR, textStatus, errorThrown){
				        	alert(errorThrown);
				            alert(jqXHR.status + ' ' + jqXHR.responseText);
				        }
				   })
	
		} else {
  			 //cancel
			}
	}

 function importExcelFile2() {
	 var _url = "http://" +_varurl +"/DepartmentController1/importExcel";
            var fileInput = document.getElementById('fileInput');
            var formData = new FormData();
            formData.append('DepartmentData', fileInput.files[0]);

            var xhr = new XMLHttpRequest();
            xhr.open('POST', _url, true);
          
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        alert("Excel file imported successfully.");
                    //    window.location.href="http://localhost:9082/GetDepartmentlist";
                    } else {
                        alert("File cannot be left blank!!");
                    }
                }
            };
            xhr.send(formData);
        }

function InvokeDepartment(Obj){

	var _varname = window.sessionStorage.getItem('sessionname');
	//alert(_varname);
	document.getElementById("SpnSession").innerHTML = _varname;
	
	 //var userListUrl = /*[[@{${@environment.getProperty('path.user.list')}}]]*/ '';
	 var _url = "http://" +_varurl +"/DepartmentController1/GetDepartmentListData";
		$.ajax({
			        type: 'GET',
			        url:  _url,    
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			         // console.log(result[0]);
			         
			         // New record
    $('a.editor-create').on('click', function (e) {
        e.preventDefault();
 
        editor.create( {
            title: 'Create new record',
            buttons: 'Add'
        } );
    });
 
    // Edit record
    $('#DepartmentGridData_dt').on('click', 'td.editor-edit', function (e) {
        e.preventDefault();
 
        editor.edit( $(this).closest('tr'), {
            title: 'Edit record',
            buttons: 'Update'
        } );
    } );
 
    // Delete a record
    	
			
			
			$('#DepartmentGridData_dt').on('click', 'td.editor-delete', function (e) {
        e.preventDefault();
 
        editor.remove( $(this).closest('tr'), {
            title: 'Delete record',
            message: 'Are you sure you wish to remove this record?',
            buttons: 'Delete'
        } );
    } );
    
  
			        
			          $('#DepartmentGridData_dt').DataTable({
				        data: result,				        
				        columns: [		
						    { data: 'DepartmentId' },
				            { data: 'DepartmentName' },		
				            { data: 'CreatedDate' },
				            { data : 'CreatedBy'},
				            { data : 'UpdatedDate'},
				            { data : 'IsActive'},		           
		           
				            {
                			data: null,
			                className: "dt-center editor-edit",
			                defaultContent: '<a type="button" class="btn btn-primary"  data-toggle="modal" data-target="#editMemberModal"  onclick="editData(this)"> <span class="glyphicon glyphicon-edit"></span>Edit</a>',
			                orderable: false
            				},
				            {
				                data: null,
				                className: "dt-center editor-delete",
				                defaultContent: '<a type="button" class="btn btn-primary"  data-toggle="modal" data-target="#DeleteMemberModal"  onclick="DeleteData(this)"> <span class="glyphicon glyphicon-edit"></span>Delete</a>',
				                orderable: false
				            }
				            
				        ],
				    });
			        	
			        },
			        error: function(jqXHR, textStatus, errorThrown){
			        	alert(errorThrown);
			            alert(jqXHR.status + ' ' + jqXHR.responseText);
			        }
			   });
			   
}

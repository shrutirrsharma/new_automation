
var _varurl = window.location.host;

function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}


function DashboardCreationExcel2(Obj) {

	window.location.href = "http://" + _varurl + "/RealTimeDataValues/PostGetDashboardGridToExcel2";

}



function OpenPopup(obj) {

	//alert(document.getElementById('spn').innerText);
	const myWindow = window.open("http://" + _varurl + "/GetLocationlist", "", "width=500,height=100");
	myWindow.opener.document.getElementById("spn").style = '';
	myWindow.opener.document.getElementById("Enc").style = 'display:none';
	//myWindow.close();

}


function BotNameData() {

	//alert("I m IN BotName");
	$.ajax({
		type: 'GET',
		url: "http://" + _varurl + "/GetBotNamelist",
		dataType: 'json',
		async: true,
		success: function(resultB) {
			//alert(result.length);



			// Add default option
			$('#idBotName').append('<option value="0">Select BotName</option>');
			idBotName
			for (var i = 0; i < resultB.length; i++) {
				$('#idBotName').append('<option value="' + resultB[i].BotId + '">' + resultB[i].BotName + '</option>');


			}
			DashboardCreation("1");
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
			alert(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});
}

function RealTimeData() {
	var _varurl = window.location.host;

	// alert("I m IN Department");
	var _varBotId = document.getElementById("idBotName").value;

	var postData = { "BotId": _varBotId };
	//sleep(8000);
	$.ajax({

		url: 'http://' + _varurl + '/GetDurationTime',
		type: "POST",

		contentType: 'application/json; charset=utf-8',

		dataType: 'json',

		data: JSON.stringify(postData),


		success: function(resultR) {

			var _Duration = data[0].Duration; /*20 feb 2024 line chart */
			var _BotName = data[0].BotName;

			var Timer = _Duration;
			/* code to display new tab */


			//document.getElementById("pieChart1").innerHTML="";

			alert("Duration displayed !!!");

			document.getElementById("Duration_Timer").innerHTML = _Duration;
			//var _varDurationId = document.getElementById("Duration_Timer");

			//_varDurationId.textContent=_Duration  ;
			//	DepartmentData();

		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
			alert(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});

	//Deaprtment
}


function DepartmentData() {
	// alert("I m IN Department");

	//sleep(8000);
	$.ajax({
		type: 'GET',
		url: 'http://' + _varurl + '/GetDepartmentlist',
		dataType: 'json',
		async: true,
		success: function(resultR) {
			//alert(result.length);



			// Add default option
			$('#idDepartmant').append('<option value="0">Select Deparment</option>');
			for (var i = 0; i < resultR.length; i++) {
				$('#idDepartmant').append('<option value="' + resultR[i].DepartmentId + '">' + resultR[i].DepartmentName + '</option>' + resultR[i].C);

			}

			BotNameData();

		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
			alert(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});

	//Deaprtment
}

function startTimer() {
	let t = window.setInterval(() => { this.Timer--; }, 1000);
}

function geformattedtime() {
	let mm = Math.floor(this.Timer / 60);
	let ss = this.Timer - mm;
	return '${mm} min : ${ss} sec';
}

function InvokeMethod(Obj) {

	console.log("inside invoke");
	var _varname = window.sessionStorage.getItem('sessionname');
	var _roleId = window.sessionStorage.getItem('sessionRoleId');

	if (_roleId === '1' || _roleId == 1) {
		document.getElementById("checkMasters").style.display = 'none';
	}
	//alert(_varname);
	document.getElementById("SpnSession").innerHTML = _varname;
	$.ajax({
		type: 'GET',
		url: 'http://' + _varurl + '/GetLocationlist',
		dataType: 'json',
		async: true,
		success: function(result) {
			//alert(result.length);
			// Clear existing options before appending new ones
			// Clear existing options before appending new ones
			$('#idLocation').empty();

			// Add default option
			$('#idLocation').append('<option value="0">Select Location</option>');

			for (var i = 0; i < result.length; i++) {
				//alert(result[i].LocationId);
				//alert(result[i].LocationName);

				$('#idLocation').append('<option value="' + result[i].LocationId + '">' + result[i].LocationName + '</option>');
			}
			DepartmentData(); //call
			//	RealTimeData("1"); //call


		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
			alert(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});


	//alert("Hello Piechart");
	//DashboardCreation("1");
}


function DashboardCreation(obj) {


	// Clear the container where the chart is displayed
	document.getElementById("pieChart11").innerHTML = "";

	var _varlocationid = document.getElementById("idLocation").value;
	var _vardepartmentId = document.getElementById("idDepartmant").value;
	var _varBotId = document.getElementById("idBotName").value;
	var _varChart = document.getElementById("idChart").value;

	var postData = {
		"LocationId": _varlocationid,
		"DepartmentId": _vardepartmentId,
		"BotId": _varBotId
	};

	// Make your AJAX call to fetch new chart data
	$.ajax({
		url: "http://" + _varurl + "/PostGetDashboardChart",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		data: JSON.stringify(postData),
		async: false,
		//timeout: 60000,
		success: function(data, textStatus, jqXHR) {


			// alert('Success!');



			var _total = data[0].total_Bot_count; /* 20 feb 2024 line chart */
			console.log("Total====" + _total);
			var _processed = data[0].ProcesedCount;
			console.log("Total====" + _processed);
			var _Unprocessed = data[0].UnProcessedCount;
			console.log("Total====" + _Unprocessed);
			document.getElementById("Total_Bot").value = _total;

			document.getElementById("Processed_Data").value = _processed;

			document.getElementById("Unprocessed_Data").value = _Unprocessed;


			/*
			 * proc = (_processed * 100)/(_total); 20 feb 2024 line chart
			 * 
			 * unProc = (_Unprocessed * 100)/(_total); 20 feb 2024 line chart
			 */


			if (_processed == 0 && _Unprocessed == 0) {

				document.getElementById("pieChart11").innerHTML = "";
				$('#GridData_dt').hide(); // Hide the table
				// document.getElementById("pieChart1").innerHTML="";

				//	alert("No Record to be Displayed !!!");




				var tbl = $('#GridData_dt').DataTable();

				tbl.destroy();

				// alert("Terminate");



				$('#GridData_dt').dataTable(

					{

						// logic

						paging: false,

						searching: false

					}

				);

				// alert("Below");



				if ($.fn.dataTable.isDataTable('#GridData_dt')) {

					table = $('#GridData_dt').DataTable();

					table.destroy();

				}

				else {

					// alert("Not Exists");

					table = $('#GridData_dt').DataTable({

						paging: false

					});

				}



				return false;

			} else {
				// Show the table
				$('#GridData_dt').show();
			}


			/* condition for charts start */

			pie11 = new d3pie("pieChart11", {

				"header": {

					"title": {

						"fontSize": 34,

						"font": "courier"

					},

					"subtitle": {

						"color": "#8A084B",

						"fontSize": 10,

						"font": "courier"

					},

					"location": "top-left",

					"titleSubtitlePadding": 10

				},

				"footer": {

					"text": "",

					"color": "#8A084B",

					"fontSize": 10,

					"font": "open sans",

					"location": "bottom-left"

				},

				"size": {

					"canvasHeight": 350,

					"canvasWidth": 450,

					"pieInnerRadius": "68%",

					"pieOuterRadius": "54%"

				},

				"data": {

					"sortOrder": "label-desc",

					"content": [

						{

							"label": "Processed",

							"value": _processed,

							"color": "#FE2E9A"

						},



						{

							"label": "Un-Processed",

							"value": _Unprocessed,

							"color": "#FAAC58"

						}

					]

				},

				"labels": {

					"outer": {

						"format": "label-percentage1",

						"pieDistance": 20

					},

					"inner": {

						"format": "none"

					},

					"mainLabel": {

						"fontSize": 11

					},

					"percentage": {

						"color": "#8A084B",

						"fontSize": 11,

						"decimalPlaces": 0

					},

					"value": {

						"color": "#08088A",

						"fontSize": 11

					},

					"lines": {

						"enabled": true,

						"color": "#0B615E"

					},

					"truncation": {

						"enabled": true

					}

				},

				"effects": {

					"pullOutSegmentOnClick": {

						"effect": "linear",

						"speed": 400,

						"size": 8,

					}

				}

			});



			$('#idChart').on('change', function() {

				var chart = $('#idChart').val();

				console.log(chart);

				if (chart === 'Bar_Chart') {

					console.log("inside bar chart");

					$('#container').show(); // Show the bar chart canvas

					$('#pieChart11').hide(); // Hide the pie chart container


					Highcharts.chart('container', {

						chart: {

							type: 'column'

						},

						yAxis: {

							min: 0,



						},

						plotOptions: {

							column: {

								pointPadding: 0.2,

								borderWidth: 0

							}

						},

						series: [

							{

								name: 'Procesed',

								data: [_processed]

							},

							{

								name: 'Unprocessed',

								data: [_Unprocessed]

							}



						]

					});



				} else if (chart === 'Pie_Chart') {

					console.log("inside pie chart");



					$('#pieChart11').show(); // Show the pie chart container

					$('#container').hide();

				}

			});

			DashboardGridCreation(1); // Code for Chart End




			$('#pieChart11').show();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Error occurred!');
		}
	});




	//	DashboardGridCreation(1); //Code for Chart End





}

function Countdown(obj) {
	var timerDisplay = document.getElementById('Duration_Timer');
	var totalSeconds = 60; // 3 minutes
	var interval;
	interval2 = setInterval(function() {
		UpadteDashboardCreation(this)
		console.log("updatePieChart");
	}, 20000);
	function startTimer() {
		interval = setInterval(updateTimer, 1000);
	}

	function updateTimer() {
		var minutes = Math.floor(totalSeconds / 60);
		var seconds = totalSeconds % 60;

		// Add leading zeros if necessary
		minutes = minutes < 10 ? '0' + minutes : minutes;
		seconds = seconds < 10 ? '0' + seconds : seconds;

		timerDisplay.textContent = minutes + ':' + seconds;

		if (totalSeconds <= 0) {

			clearInterval(interval);
			clearInterval(interval2);



			console.log('Email sent successfully');

			emailjs.init("6UM6u3ACvBwLVHxke");
			// Define email data
			const emailData = {
				service_id: 'service_r5q49ek', // Service ID from your Email.js account
				template_id: 'template_rr8ws3q', // Template ID from your Email.js account
				user_id: 'dhoktevaibhav1234@gmail.com', // User ID from your Email.js account
				template_params: {
					to_email: 'dhoktevaibhav404@gmail.com', // Recipient's email address
					from_email: 'dhoktevaibhav1234@gmail.com', // Sender's email address
					subject: 'Your new Bot Data is processed', // Email subject
					message: 'Your new Bot Data is processed' // Email message
				}
			};

			// Send email
			emailjs.send(emailData.service_id, emailData.template_id, emailData.template_params)
				.then(function(response) {
					alert('Email sent successfully');
					console.log(response);
				})
				.catch(function(error) {
					console.error('Error sending email:', error);
				});



		} else {
			totalSeconds--;
		}
	}


	startTimer();


}



/*setInterval(function() {
	 UpadteDashboardCreation(this)
	console.log("updatePieChart");
}, 20000);*/
//-------------------added code by kiran end---------------------------------------


/*condition for charts end*/



function DashboardGridCreation(Obj) {

	//alert("Bind Grid");

	//GridData_dt.attribute("display"," ");
	var _varlocationid = document.getElementById("idLocation").value;
	//alert("Location :-" + _varlocationid);
	var _vardepartmentId = document.getElementById("idDepartmant").value;
	//alert("Department" +_vardepartmentId);
	var _varBotId = document.getElementById("idBotName").value;

	//var selectedDate = document.getElementById("Test_DatetimeLocal").value;
	//alert("Bot" + _varBotId);
	var postData = _varlocationid + ',' + _vardepartmentId + ',' + _varBotId;



	var postData = {
		"LocationId": _varlocationid,
		"DepartmentId": _vardepartmentId,
		"BotId": _varBotId,
		//	"SelectedDate": selectedDate
	}
	//alert(postData);
	$.ajax({
		url: "http://" + _varurl + "/RealTimeDataValues/PostGetDashboardGrid",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		data: JSON.stringify(postData),
		success: function(resultB) {
			// Check if the result is valid
			if (resultB && resultB.length > 0) {
				// Destroy existing DataTable if it exists
				var existingDataTable = $('#GridData_dt').DataTable();
				if (existingDataTable !== undefined) {
					existingDataTable.destroy();
				}

				// DataTable Code
				$('#GridData_dt').DataTable({
					data: resultB,
					columns: [
						{ data: 'ProcessId' },
						{ data: 'BotName' },
						{ data: 'LocationName' },
						{ data: 'DepartmentName' },
						{ data: 'Process_Name' },
						{ data: 'Status' },
						{ data: 'Remarks' },
						{ data: 'CreatedBy' },
						{ data: 'StartTime' },
					],
				});
			} else {
				// No data returned from the stored procedure
				// You can display a message or handle it as needed
				// For example, clear the existing DataTable
				var existingDataTable = $('#GridData_dt').DataTable();
				if (existingDataTable !== undefined) {
					existingDataTable.clear().draw();
				}
				// Display a message to the user
				// For example:
				// $('#GridData_dt').html('<p>No data available for the selected criteria.</p>');

			}
			//	RealTimeData();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
			alert(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});
}

/*
const emailjs = require('emailjs-com');
function sebdEmail(){
 console.log('Email sent successfully');

	emailjs.init("6UM6u3ACvBwLVHxke");
// Define email data
const emailData = {
	service_id: 'service_r5q49ek', // Service ID from your Email.js account
	template_id: 'template_rr8ws3q', // Template ID from your Email.js account
	user_id: 'dhoktevaibhav1234@gmail.com', // User ID from your Email.js account
	template_params: {
		to_email: 'dhoktevaibhav404@gmail.com', // Recipient's email address
		from_email: 'dhoktevaibhav1234@gmail.com', // Sender's email address
		subject: 'Your new Bot Data is processed', // Email subject
		message: 'Your new Bot Data is processed' // Email message
	}
};

// Send email
emailjs.send(emailData.service_id, emailData.template_id, emailData.template_params)
	.then(function(response) {
		console.log('Email sent successfully');
		console.log(response);
	})
	.catch(function(error) {
		console.error('Error sending email:', error);
	});

}*/

function funcSearch(Obj) {

	//alert("Bind Grid");
	//GridData_dt.attribute("display"," ");
	var _varlocationid = document.getElementById("idLocation").value;
	//alert("Location :-" + _varlocationid);
	var _vardepartmentId = document.getElementById("idDepartmant").value;
	//alert("Department" +_vardepartmentId);
	var _varBotId = document.getElementById("idBotName").value;
	//alert("Bot" + _varBotId);
	var postData = _varlocationid + ',' + _vardepartmentId + ',' + _varBotId;

	//search Textbox value
	var _varsearch = document.getElementById("txtSearch").value;
	//alert(_varsearch);

	var postData = {
		"LocationId": _varlocationid,
		"DepartmentId": _vardepartmentId,
		"BotId": _varBotId,
		"search": _varsearch
	}
	//alert(postData);

	$.ajax({
		url: "http://" + _varurl + "/RealTimeDataValues/PostGetDashboardGridSearch",
		type: "POST",
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		data: JSON.stringify(postData),
		success: function(resultB) {
			//alert("I am in Success");
			//alert(resultB[0].ProcessId);
			//alert(resultB[0].BotName);
			//document.getElementById("GridData_dt").style.display="block";

			//Loop to remove table row start
			var tableHeaderRowCount = 1;
			var table = document.getElementById('GridData_dt');
			var rowCount = table.rows.length;
			for (var i = tableHeaderRowCount; i < rowCount; i++) {
				table.deleteRow(tableHeaderRowCount);
			}
			//loop end

			for (var i = 0; i < resultB.length; i++) {
				let noteRow = '<tr>' +
					'<td>' + resultB[i].ProcessId + '</td>' +
					'<td>' + resultB[i].BotName + '</td>' +
					'<td>' + resultB[i].LocationName + '</td>' +
					'<td>' + resultB[i].DepartmentName + '</td>' +
					'<td>' + resultB[i].Process_Name + '</td>' +
					'<td>' + resultB[i].Status + '</td>' +
					'<td>' + resultB[i].Remarks + '</td>' +
					'<td>' + resultB[i].CreatedBy + '</td>' +
					'</tr>';

				$('#GridData_dt tbody').append(noteRow);
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
			alert(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});
}

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="text/html"
	charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Mappletech - Bokning</title>

<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/resources/UI/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome CSS -->
<link
	href="<%=request.getContextPath()%>/resources/UI/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Select2-->
<link
	href="<%=request.getContextPath()%>/resources/UI/css/select2.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/resources/UI/css/style.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/UI/css/animate.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link href="http://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet" type="text/css">

<!-- Bootstrap time Picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/UI/css/bootstrap-timepicker.min.css">

<!-- Daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/UI/css/daterangepicker-bs3.css">

<!-- Template js -->
<script
	src="<%=request.getContextPath()%>/resources/UI/js/jquery-2.1.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/jquery.appear.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/moment.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/daterangepicker.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/bootstrap-timepicker.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/bootstrap/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/jquery.appear.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/jqBootstrapValidation.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/modernizr.custom.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/select2.full.min.js"></script>

<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body style="background-image: none">

	<!-- Start Main Body Section -->
	<div class="text-center section-modal">

		<div class="modal-content">
			<div class="back-modal" data-dismiss="modal">
				<a href="/mappletech/">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</a>
			</div>
		</div>

		<div class="container">
			<div class="row">

				<div class="container">

					<div class="row">
						<div class="section-title text-center">
							<h3>Boka</h3>
							<p>Skapa ny bokning h�r</p>
						</div>
					</div>

					<div class="row">
						<div class="col-md-offset-3 col-md-6 col-md-offset-3">
							<form:form commandName="resvm" id="res" class="form-change"
								method="post">

								<div class="form-group">
									<label>Lokaler</label>
									<form:select path="facilityID"
										class="form-control select2list"
										data-placeholder="V�lj lokal" style="width: 100%;">
										<form:options items="${facilities}"/>
									</form:select>

								</div>

								<div class="form-group">
									<label>Tid (fr�n)</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-clock-o"></i>
										</div>
										<form:input path="timeFrom" type="text"
											class="form-control pull-right" id="reservationtimeFirst" required="" />	
									</div>

								</div>
                                
                                <div class="bootstrap-timepicker">     
                                  <div class="form-group">
                                    <label>Starttid</label>
                                    <div class="input-group">
                                      <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                      </div>
                                   
                                      <form:input type="text" class="form-control" path="timeFrom" id="timepickerFirst" placeholder="hh:mm" />
                                      
                                    </div>
                                  </div>
                                </div>

								<div class="form-group">
									<label>Tid (till)</label>

									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-clock-o"></i>
										</div>
										<form:input path="timeTo" type="text"
											class="form-control pull-right" id="reservationtimeSecond" required=""/>
									</div>

								</div>
                                
								<div class="bootstrap-timepicker">     
                                  <div class="form-group">
                                    <label>Starttid</label>
                                    <div class="input-group">
                                      <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                      </div>
                                   
                                      <form:input type="text" class="form-control" path="timeFrom" id="timepickerSecond" placeholder="hh:mm" />
                                      
                                    </div>
                                  </div>
                                </div>

								<div class="row">
									<div class="col-md-offset-3 col-md-6 col-md-offset-3">
									<p id="testdate" onclick="getCurrentDate()"></p>
										<button type="submit" class="btn-lg btn btn-success"
											name="facility">Boka anl�ggning</button>
									</div>
									
								</div>
							</form:form>
						</div>
					</div>


				</div>
			</div>
		</div>

	</div>
	<!-- End Main Body Section -->

	<script>
		$(function() {
			// Date range picker with time picker 1
			$('#reservationtimeFirst').daterangepicker({
			    "singleDatePicker": true,
			    "showDropdowns": true,
			    "autoApply": true,
			    "opens": "left",
				locale: {
					format: 'YYYY-MM-DD',
					customRangeLabel: 'Custom',
					daysOfWeek: ['M�n','Tis', 'Ons', 'Tors', 'Fre', 'L�r','S�n'],
					monthNames: ['Januari', 'Februari', 'Mars', 'April', 'Maj', 'Juni', 'Juli', 'Augusti', 'September', 'Oktober', 'November', 'December'],
					firstDay: 1
				}
			});

			// Date range picker with time picker 2
			$('#reservationtimeSecond').daterangepicker({
			    "singleDatePicker": true,
			    "showDropdowns": true,
			    "autoApply": true,
			    "opens": "left",
				locale: {
					format: 'YYYY-MM-DD',
					customRangeLabel: 'Custom',
					daysOfWeek: ['M�n','Tis', 'Ons', 'Tors', 'Fre', 'L�r','S�n'],
					monthNames: ['Januari', 'Februari', 'Mars', 'April', 'Maj', 'Juni', 'Juli', 'Augusti', 'September', 'Oktober', 'November', 'December'],
					firstDay: 1
				}
			});
			
			// Timepicker 1
			$("#timepickerFirst").timepicker({
			  minuteStep: 30, 
			  showMeridian: false,
			  defaultTime: false,
			  showInputs: false
			});
			
			// Timepicker 2
			$("#timepickerSecond").timepicker({
			  minuteStep: 30, 
			  showMeridian: false,
			  defaultTime: false,
			  showInputs: false
			});
		});
	</script>

	<script>
		$(document).ready(function() {
			// Select2
			$(".select2list").select2({
			  placeholder: "V�lj lokal",
			  allowClear: true
			});
		});
    </script>
    
    <!-- Function for getting current time and date in format YYYY-MM-DD HH:MM:DD -->
    <script type="text/javascript">
    function getCurrentDate(){
    	var date = new Date();
    	var d = date.getUTCDate();
    	var month = date.getUTCMonth();
    	var y = date.getUTCFullYear();
    	
    	h = h+1;
    	month = month+1;
    	if(d<10){d = "0"+d;}
    	if(month<10){month = "0"+month;}
    	
    	
    	return y+"-"+month+"-"+d+" "+h+":"+m+":"+s;
    }
    document.getElementById("reservationtimeFirst").value = getCurrentDate();
	document.getElementById("reservationtimeSecond").value = getCurrentDate();
    </script>

</body>
</html>
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
<title>Mappletech - Grupper</title>

<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/resources/UI/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome CSS -->
<link
	href="<%=request.getContextPath()%>/resources/UI/css/font-awesome.min.css"
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
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/jquery-2.1.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/jquery.appear.js"></script>
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
				<div class="section-title text-center">
					<h3>Grupper</h3>
					<p>Skapa ny grupp</p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-offset-3 col-md-6 col-md-offset-3">
					<form:form commandName="newgroupview" id="res" class="form-change"
						method="post">
						<div class="form-group">
							<label>Gruppens Namn</label>
							<form:input type="text" path="groupName" class="form-control"
								placeholder="Namn..." required="" />
						</div>

						<div class="form-group">
							<label>Beskrivning</label>
							<form:textarea path="description" rows="5" cols="30" type="text"
								class="form-control" placeholder="Beskrivning..." required="" />
						</div>
					
                    	<br />
                    
                    	<button type="submit" class="btn-lg btn btn-success" name="event">Lägg till grupp</button>
                    </form:form>
				</div>

			</div>
		</div>

	</div>
	<!-- End Main Body Section -->


</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="text/html"
	charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Mappletech - Login</title>

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

<!--EXTRA CSS/JS FOR TABLE-->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<script
	src="http://vitalets.github.io/angular-xeditable/dist/js/xeditable.js"></script>
<script src="https://code.angularjs.org/1.0.8/angular-mocks.js"></script>
<link
	href="http://vitalets.github.io/angular-xeditable/dist/css/xeditable.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/resources/UI/css/angular-extra-table.css"
	rel="stylesheet">

<!-- Template js -->
<script
	src="<%=request.getContextPath()%>/resources/UI/js/jquery-2.1.1.min.js"></script>
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

				<div class="container">
					<div class="row">
						<div class="section-title text-center">
							<h3>Användare</h3>
							<p>Hantera användare här</p>
						</div>
					</div>
					
						<form:form method="POST" action=".">

						<table id="thetable" class="table table-bordered table-striped">

						<thead>
							<tr>
								<th>Användarnamn</th>
								<th>Namn</th>
								<th>Adress</th>
								<th>Tel.nummer</th>
								<th>Mobilnummer</th>
								<th>e-post</th>
								<th>Ta bort</th>
								<th></th>
							</tr>
						</thead>

							<tbody>
								<c:if test="${not empty list}">
									<c:forEach var="r" items="${list}">
										<tr>
											<td>${r.username}</td>
											<td>${r.fullName}</td>
											<td>${r.address}</td>
											<td>${r.phoneNumber}</td>
											<td>${r.mobileNumber}</td>
											<td>${r.email}</td>

											<td><button type="submit" name="remove" value="${r.username}" class="glyphicon glyphicon-trash"></button></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</form:form>
					</table>
</body>
</html>
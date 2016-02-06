<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
<spring:url value="<c:url value="UI/bootstrap/css/bootstrap.min.css"/>" var="mappleTechCSS_bootst" />
<spring:url value="<c:url value="UI/css/font-awesome.min.css" />" var="mappleTechCSS_font" />
<spring:url value="<c:url value="UI/css/style.css"/>" var="mappleTechCSS_style" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Mappletech - Login</title>



<!-- Bootstrap Core CSS -->
<link href="${mappleTechCSS_bootst}" rel="stylesheet">

<!-- Font Awesome CSS -->
<link href="${mappleTechCSS_font}" rel="stylesheet">

<!-- Custom CSS -->
<link href="${mappleTechCSS_style}" rel="stylesheet">

<!-- Custom Fonts -->
<link href="http://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet" type="text/css">


<!-- Template js -->
<script src='<c:url value="./UI/js/jquery-2.1.1.min.js"/>'> </script>
<script src='<c:url value="./UI/bootstrap/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="./UI/js/jquery.appear.js"/>'></script>

<script src='<c:url value="./UI/js/jqBootstrapValidation.js"/>'> </script>
<script src='<c:url value="./UI/js/modernizr.custom.js"/>'> </script>

<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<body style="background-image: url(./UI/images/background.png)">
	<!-- Start Logo Section -->
	<p>&nbsp;</p>
	<section id="logo-section" class="text-center">
		<div class="container">
			<div class="row">
				<div class="col-md-offset-4 col-md-4 col-md-offset-4"
					style="background: rgba(120, 120, 140, 0.5)">
					<div class="logo text-center">
						<h1>Mappletech</h1>
						<p>
							<span style="color: #eee">Föreningsplattform</span>
						</p>
						<p>&nbsp;</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Logo Section -->


	<!-- Start Main Body Section -->
	<div class="mainbody-section text-center">
		<div class="container">
			<div class="row">
				<div class="container">
					<div class="col-md-offset-4 col-md-4 col-md-offset-4"
						style="background-color: rgba(120, 120, 140, 0.5)">
						<form:form modelAttribute="uservm" id="user" class="form-signin"
							method="POST">

							<p>&nbsp;</p>


							<form:input path="username" id="inputUsername"
								class="form-control" placeholder="Användarnamn" required=""
								autofocus="true" />

							<form:input path="password" type="password" id="inputPassword"
								class="form-control" placeholder="Lösenord" required="" />

							<div class="checkbox">
								<label>
									<p style="color: #eee">
										<input type="checkbox" value="remember-me">Kom ihåg
										mig
									</p>
								</label>
							</div>

							<button type="submit" value="Submit"
								class="btn btn-lg btn-default btn-block">Logga in</button>
							<br>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Main Body Section -->

	<!-- Start Copyright Section -->
	<div class="copyright text-center" style="display: none">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<!-- Copyright -->
				</div>
			</div>
		</div>
	</div>
	<!-- End Copyright Section -->


</body>
</html>
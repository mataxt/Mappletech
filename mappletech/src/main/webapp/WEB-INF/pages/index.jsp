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
<script src="<%=request.getContextPath()%>/resources/UI/js/script.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/UI/js/select2.full.min.js"></script>

<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<!-- Start Logintop Section -->
	<div class="logintop text-center">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div style="text-align: left">
						<strong>Inloggad som: </strong> <a href="/mappletech/profil/"
							style="font-size: 20px">${sessUser.fullName}</a>
					</div>
				</div>

				<div class="col-md-5"></div>

				<div class="col-md-1">
					<div class="col-sm">
						<a href="#">
							<button class="btn btn-sm btn-danger btn-block">Logga ut</button>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Copyright Section -->

	<p>&nbsp;</p>

	<!-- Start Logo Section -->
	<section id="logo-section" class="text-center">
		<div class="container">
			<div class="row">
				<div class="col-md-offset-4 col-md-4 col-md-offset-4"
					style="background: rgba(120, 120, 140, 0.5)">
					<a href="#">
						<div class="logo text-center">
							<h1>Mappletech</h1>
							<p>
								<span style="color: #eee">Föreningsplattform</span>
							</p>

							<p>&nbsp;</p>

						</div>
					</a>
				</div>
			</div>
		</div>
	</section>
	<!-- End Logo Section -->

	<!-- Start Home Slider Section -->
	<div class="home-slider container">
		<!-- NEWS HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
	</div>
	<!-- Start Home Slider Section -->

	<!-- Start Main Body Section -->
	<div class="mainbody-section text-center">
		<div class="container">
			<div class="row">

				<div class="col-md-4">

					<div class="menu-item blue">
						<a href="#bokning-modal" data-toggle="modal"> <i
							class="fa fa-calendar"></i>
							<p>Bokning</p>
						</a>
					</div>

					<div class="menu-item green">
						<a href="#grupper-modal" data-toggle="modal"> <i
							class="fa fa-users"></i>
							<p>Grupper</p>
						</a>
					</div>

				</div>

				<div class="col-md-4">
					<div class="menu-item light-red">
						<a href="#felanmalan-modal" data-toggle="modal"> <i
							class="fa fa-exclamation-triangle"></i>
							<p>Felanmälan</p>
						</a>
					</div>

					<div class="menu-item light-orange">
						<a href="#kontakta-oss-modal" data-toggle="modal"> <i
							class="fa fa-comment"></i>
							<p>Kontakta oss</p>
						</a>
					</div>

				</div>

				<div class="col-md-4">

					<div class="menu-item purple">
						<a href="#handalser-modal" data-toggle="modal"> <i
							class="fa fa-file-text"></i>
							<p>Händelser</p>
						</a>
					</div>

<c:choose>
						<c:when test="${sessUser.privilege < '2'}">
							<div id="disabledDiv" class="menu-item red">
								<a> <i
									class="fa fa-cog"></i>
									<p>Administratör</p>
								</a>
							</div>
							<br />

						</c:when>
						<c:otherwise>
							<div id="adminDiv" class="menu-item color">
								<a href="#administrator-modal" data-toggle="modal"> <i
									class="fa fa-cog"></i>
									<p>Administratör</p>
								</a>
							</div>
							<br />
						</c:otherwise>
					</c:choose>

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

	<!-- Start Bokning Section -->
	<div class="section-modal modal fade" id="bokning-modal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="section-title text-center">
						<h3>Bokning</h3>
						<p>Hantera och skapa bokningar här</p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="bokning/boka/"> <i class="fa fa-calendar blue"></i>
								<div class="feature-content">
									<h4>Boka</h4>
									<p>Boka tid här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="bokning/mina-bokningar/"> <i
								class="fa fa-pencil-square-o blue"></i>
								<div class="feature-content">
									<h4>Mina bokningar</h4>
									<p>Hantera dina bokningar här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
				</div>

			</div>
		</div>
	</div>
	<!-- End Bokning Section -->


	<!-- Start Grupper Section -->
	<div class="section-modal modal fade" id="grupper-modal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="section-title text-center">
						<h3>Grupper</h3>
						<p>Hantera och skapa grupper här</p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="grupper/visa-alla-grupper/"> <i
								class="fa fa-users green"></i>
								<div class="feature-content">
									<h4>Visa alla grupper</h4>
									<p>Lista över grupper här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="grupper/mina-grupper/"> <i class="fa fa-bars green"></i>
								<div class="feature-content">
									<h4>Mina grupper</h4>
									<p>Hantera dina grupper här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="grupper/skapa-grupper/"> <i
								class="fa fa-plus-circle green"></i>
								<div class="feature-content">
									<h4>Skapa grupp</h4>
									<p>Skapa ny grupp här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
				</div>

			</div>

		</div>
	</div>
	<!-- End Grupper Section -->


	<!-- Start FelanmÃ¤lan Section -->
	<div class="section-modal modal fade" id="felanmalan-modal"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="section-title text-center">
						<h3>Felanmälan</h3>
						<p>Felanmälan här</p>
					</div>
				</div>

				<div class="row">

					<div class="col-md-12">
						<div class="footer-contact-info light-red">
							<h4>Information</h4>

							<ul>
								<li><strong>2016-01-29: </strong>Just nu är det problem
									med avloppssystemen.</li>
							</ul>

							<ul>
								<li><strong>2016-01-01: </strong>Gott nytt år.</li>
							</ul>
						</div>
					</div>

				</div>
				<!--/.row -->

				<div class="row" style="padding-top: 40px;">
					<div class="col-md-12">
						<form:form modelAttribute="repVm" action="report" method="POST" name="sentMessage" id="reportForm" novalidate="">

							<div class="row">

								<div class="col-md-12">
									<div class="form-group">
										<form:select path="reason" class="form-control select2dropdown"
											data-placeholder="Välj kategori"
											style="width: 100%;">
											<option>Trapphus</option>
											<option>Soprum</option>
											<option>Tvättstuga</option>
											<option>Lokaler</option>
											<option>Hushåll</option>
											<option>Parkering</option>
											<option>Förråd</option>
											<option>Övrigt</option>
										</form:select>
									</div>
								</div>

								<div class="col-md-12">
									<div class="form-group">
										<form:textarea path="description" class="form-control"
											placeholder="Utförlig beskrivning" id="message"
											style="height: 150px" required=""
											data-validation-required-message="Skriva in en utförlig beskrivning."></form:textarea>
										<p class="help-block text-danger"></p>
									</div>
								</div>

								<div class="clearfix"></div>

								<div class="col-lg-12 text-center">
									<div id="success"></div>
									<button type="submit" class="btn btn-primary light-red">Skicka
										felanmälan</button>
								</div>
							</div>
						</form:form>

					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- End FelanmÃ¤lan Section -->


	<!-- Start Kontakta-oss Section -->
	<div class="section-modal modal fade" id="kontakta-oss-modal"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="section-title text-center">
						<h3>Kontakta oss</h3>
						<p>Kontakta oss här</p>
					</div>
				</div>

				<div class="row">

					<div class="col-md-6">
						<div class="footer-contact-info light-orange">
							<h4>Kontakt Info</h4>
							<ul>
								<li><strong>E-post: </strong>mail@epost.se</li>
								<li><strong>Telefon: </strong>+46-234567896</li>
								<li><strong>Adress: </strong>Föreningsvägen 1</li>
							</ul>
						</div>
					</div>

					<div class="col-md-6">
						<div class="footer-contact-info light-orange">
							<h4>Ö–ppettider</h4>
							<ul>
								<li><strong>Måndag-Fredag: </strong>9 - 15</li>
								<li><strong>Lördag: </strong>12 - 15</li>
								<li><strong>Söndag: </strong>Stängt</li>
							</ul>
						</div>
					</div>

				</div>
				<!--/.row -->
				<div class="row" style="padding-top: 40px;">
					<div class="col-md-12">
						<form name="sentMessage" id="contactForm" novalidate>
							<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<textarea class="form-control" placeholder="Meddelande"
											id="message" style="height: 150px" required
											data-validation-required-message="Skriv in ett meddelande."></textarea>
										<p class="help-block text-danger"></p>
									</div>
								</div>

								<div class="clearfix"></div>

								<div class="col-lg-12 text-center">
									<div id="success"></div>
									<button type="submit" class="btn btn-primary light-orange">Skicka
										meddelande</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- End Kontakta-oss Section -->


	<!-- Start HÃ¤ndelser Section -->
	<div class="section-modal modal fade contact" id="handalser-modal"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="section-title text-center">
						<h3>Händelser</h3>
						<p>Händelser här</p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="feature" style="height: 150px">
							<a href="handelser/mina-handelser/"> <i
								class="fa fa-bars purple"></i>
								<div class="feature-content">
									<h4>Mina händelse</h4>
									<p>Hantera dina händelse här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="feature" style="height: 150px">
							<a href="handelser/skapa-ny-handelse/"> <i
								class="fa fa-rss purple"></i>
								<div class="feature-content">
									<h4>Skapa händelse</h4>
									<p>Skapa ny händelse här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="footer-contact-info purple">
							<h4>Senaste nytt</h4>
							<ul>
								<c:forEach var="event" items="${eventlist}">
									<li>${event.date}${event.title}</li>
								</c:forEach>
							</ul>
						</div>
					</div>

				</div>
				<!--/.row -->

			</div>
		</div>
	</div>
	<!-- End HÃ¤ndelser Section -->


	<!-- Start AdministratÃ¶r Section -->
	<div class="section-modal modal fade contact" id="administrator-modal"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="section-title text-center">
						<h3>Administratör</h3>
						<p>Administratör inställningar här</p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="administrator/anvandare/"> <i
								class="fa fa-user color"></i>
								<div class="feature-content">
									<h4>Användare</h4>
									<p>Skapa och hantera användare här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="administrator/grupper/"> <i
								class="fa fa-users color"></i>
								<div class="feature-content">
									<h4>Grupper</h4>
									<p>Skapa och hantera grupper här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="administrator/bokningar/"> <i
								class="fa fa-calendar color"></i>
								<div class="feature-content">
									<h4>Bokningar</h4>
									<p>Hantera bokningar här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="administrator/handelser/"> <i
								class="fa fa-file-text color"></i>
								<div class="feature-content">
									<h4>Händelser</h4>
									<p>Skapa och hantera händelser här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="feature">
							<a href="administrator/felanmalan/"> <i
								class="fa fa-exclamation-triangle color"></i>
								<div class="feature-content">
									<h4>Felanmälan</h4>
									<p>Hantera felanmälningar här</p>
								</div>
							</a>
						</div>
					</div>
					<!-- /.col-md-3 -->

				</div>
				<!-- /.row -->

			</div>

		</div>
	</div>
	<!-- End AdministratÃ¶r Section -->

	<script>
		$(document).ready(function() {
			// Select2
			$(".select2dropdown").select2({
				placeholder : "Välj kategori",
				allowClear : true
			});
		});
	</script>

</body>
</html>
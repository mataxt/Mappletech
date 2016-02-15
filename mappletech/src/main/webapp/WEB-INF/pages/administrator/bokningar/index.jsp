<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="text/html" charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
        <title>Mappletech - Bokning</title>
    
    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/UI/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome CSS -->
    <link href="<%=request.getContextPath()%>/resources/UI/css/font-awesome.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/resources/UI/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/UI/css/animate.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Lobster"
        rel="stylesheet" type="text/css">
   
    <!--EXTRA CSS/JS FOR TABLE-->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
    <script src="http://vitalets.github.io/angular-xeditable/dist/js/xeditable.js"></script>
    <script src="https://code.angularjs.org/1.0.8/angular-mocks.js"></script>
    <link href="http://vitalets.github.io/angular-xeditable/dist/css/xeditable.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/resources/UI/css/angular-extra-table.css" rel="stylesheet">


    <!-- Template js -->
    <script src="<%=request.getContextPath()%>/resources/UI/js/jquery-2.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/UI/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/UI/js/jquery.appear.js"></script>
    <script src="<%=request.getContextPath()%>/resources/UI/js/jqBootstrapValidation.js"></script>
    <script src="<%=request.getContextPath()%>/resources/UI/js/modernizr.custom.js"></script>
   

    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
    
    <body style="background-image:none">
        
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
                            <h3>Bokningar</h3>
                            <p>Hantera bokningar h�r</p>
                        </div>
                    </div>
                        
                        <!-- form:form -->
                    
                <form:form commandName="reservationVM" method="POST">
					<div class="fieldset">
						<fieldset>
							<nav>
								<ul style="list-style: none;">
									<li><br>Bokningar:</li>
									<li>
										<form:select path="commandAttribute" title="results" size="8" style="width: 500px;">
											<form:option value="-" label="V�lj bokning"/>
											<form:options items="${reservList}"  selected="selected" ondblclick="onDbClickFunction()"/>
										</form:select>
								<li><br><input type="submit" value="Ta bort"/></li>
								</ul>
							</nav><!-- end navigation menu -->
						</fieldset>
					</div>
				</form:form>
                       
                  </div>
                </div>
              </div>
              
            </div>
 
        <!-- End Main Body Section -->
        
      <!-- Script for table-->
    <script>
		function onDbClickFunction() {
			document.forms["reservationVM"].submit();
		}
	</script>
      
    </body>
</html>
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
                            <p>Hantera bokningar här</p>
                        </div>
                    </div>
                    
                    <div class="row">
						<div ng-app="app" ng-controller="Ctrl">
                         <table class="table table-bordered table-hover table-condensed">
                          <tr style="font-weight: bold">
                            <td style="width:17%">Bokare</td>
                            <td style="width:25%">Lokal</td>
                            <td style="width:18%">Tid (från)</td>
                            <td style="width:18%">Tid (till)</td>
                          </tr>
      					  <tr ng-repeat="user in users">
                          
        				<td>
                          <!-- editable username (text with validation) -->
                          <span e-name="name" e-form="rowform" onbeforesave="checkName($data, user.id)" e-required>
                           {{ user.name || 'empty' }}
                          </span>
                    	</td>
                        
                        <td>
                          <!-- editable status (select-local) -->
                          <span editable-text="user.status" e-name="status" e-form="rowform" e-ng-options="s.value as s.text for s in statuses">
                            {{ showStatus(user) }}
                          </span>
            		   </td>
                       
                        <td>
                          <!-- editable group (select-remote) -->
                          <span editable-text="user.group" e-name="group" onshow="loadGroups()" e-form="rowform" e-ng-options="g.id as g.text for g in groups">
                            {{ showGroup(user) }}
                          </span>
                        </td>
                        
                        <td>
                          <!-- editable status (select-local) -->
                          <span editable-text="user.status" e-name="status" e-form="rowform" e-ng-options="s.value as s.text for s in statuses">
                            {{ showStatus(user) }}
                          </span>
            		   </td>
                        
                        <!-- change and remove buttons -->
                        <td style="white-space: nowrap">
                        
                        <!-- form -->
                    
                <form:form commandName="reservationVm" method="POST">
					<div class="fieldset">
						<fieldset>
							<nav>
								<ul style="list-style: none;">
									<li><br>Bokningar:</li>
									<li>
										<form:select path="reservationId" title="results" size="8" style="width: 350px;">
											<form:options items="${reservationList}" itemValue="host" itemLabel="title" selected="selected" ondblclick="onDbClickFunction()"/>
										</form:select>
								<li><br><input type="submit" value="Ta bort"/></li>
								</ul>
							</nav><!-- end navigation menu -->
						</fieldset>
					</div>
				</form:form>
                       
                        </td> 
                      </tr>
                    </table>
                  </div>
                </div>
              </div>
              
            </div>
          </div>
        </div>
        <!-- End Main Body Section -->
        
      <!-- Script for table-->
    <script>
		function onDbClickFunction() {
			document.forms["modifyForm"].submit();
		}
	</script>
      
    </body>
</html>
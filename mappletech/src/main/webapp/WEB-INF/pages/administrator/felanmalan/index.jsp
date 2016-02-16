<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="text/html" charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
        <title>Mappletech - Felanmälning</title>
    
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
                            <h3>Felanmälning</h3>
                            <p>Hantera felanmälningar här</p>
                        </div>
                    </div>
                    
                    <div class="row">
						<div ng-app="app" ng-controller="Ctrl">
                         <table class="table table-bordered table-hover table-condensed">
                          <tr style="font-weight: bold">
                            <td style="width:16%">Rapporterare</td>
                            <td style="width:17%">Orsak</td>
                            <td style="width:17%">Beskrivning</td>
                            <td style="width:17%">Status</td>
                            <td style="width:17%">Datum</td>
                            <td style="width:16%">Ändra</td>
                          </tr>
      					  <tr ng-repeat="user in users">
                          
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
                        
                        <td>
                          <!-- editable status (select-local) -->
                          <span editable-select="user.status" e-name="status" e-form="rowform" e-ng-options="s.value as s.text for s in statuses">
                            {{ showStatus(user) }}
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
                        <form:form method="POST" action=".">
                  
                                <table id="thetable" class="table table-bordered table-striped">
                              
                                <thead>
                                    <tr>
                                    	<th>Anm�ld av</th>
                                        <th>Orsak</th>
                                        <th>Beskrivning</th>
                                         <th>Status</th>
                                          <th>Datum</th>
                                           <th>Ta bort</th>
                                        <th></th>
                                    </tr>
                                </thead> 

									<tbody>
										<c:if test="${not empty list}">
											<c:forEach var="r" items="${list}">
												<tr>
													<td>${r.reporter}</td>
													<td>${r.reason}</td>
													<td>${r.description}</td>
													<td>${r.status}</td>
													<td>${r.date}</td>
													
													<td><button type="submit" name="remove" value="${r.reportId }"
															class="btn-md btn btn-danger">Ta bort</button></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<br />

							</form:form>
                          
                            <div class="buttons" ng-show="!rowform.$visible">
                              <button class="btn btn-info" ng-click="rowform.$show()">Ändra</button>
                              <button class="btn btn-danger" ng-click="removeUser($index)">Ta bort</button>
                            </div>  
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
    
      
    </body>
</html>
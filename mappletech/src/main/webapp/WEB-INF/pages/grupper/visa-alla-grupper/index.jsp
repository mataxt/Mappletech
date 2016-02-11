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
        <title>Mappletech - Grupper</title>
    
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
                            <h3>Grupper</h3>
                            <p>Här visas alla tillgängliga grupper</p>
                        </div>
                    </div>
                    
                    <div class="row">
						<div ng-app="app" ng-controller="Ctrl">
                         <table class="table table-bordered table-hover table-condensed">
                          <tr style="font-weight: bold">
                            <td style="width:33%">Gruppnamn</td>
                            <td style="width:34%">Beskrivning</td>
                            <td style="width:33%">Skapare</td>
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
                          <!-- editable status (select-local) -->
                          <span editable-text="user.status" e-name="status" e-form="rowform" e-ng-options="s.value as s.text for s in statuses">
                            {{ showStatus(user) }}
                          </span>
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
	  var app = angular.module("app", ["xeditable", "ngMockE2E"]);

		app.run(function(editableOptions) {
		  editableOptions.theme = 'bs3';
		});

		app.controller('Ctrl', function($scope, $filter, $http) {
		  $scope.users = [
			{id: 1, name: 'awesome user1', status: 2, group: 4, groupName: 'admin'},
			{id: 2, name: 'awesome user2', status: undefined, group: 3, groupName: 'vip'},
			{id: 3, name: 'awesome user3', status: 2, group: null}
		  ]; 

		  $scope.statuses = [
			{value: 1, text: 'status1'},
			{value: 2, text: 'status2'},
			{value: 3, text: 'status3'},
			{value: 4, text: 'status4'}
		  ]; 

		  $scope.groups = [];
		  $scope.loadGroups = function() {
			return $scope.groups.length ? null : $http.get('/groups').success(function(data) {
			  $scope.groups = data;
			});
		  };

		  $scope.showGroup = function(user) {
			if(user.group && $scope.groups.length) {
			  var selected = $filter('filter')($scope.groups, {id: user.group});
			  return selected.length ? selected[0].text : 'Not set';
			} else {
			  return user.groupName || 'Not set';
			}
		  };

		  $scope.showStatus = function(user) {
			var selected = [];
			if(user.status) {
			  selected = $filter('filter')($scope.statuses, {value: user.status});
			}
			return selected.length ? selected[0].text : 'Not set';
		  };

		  $scope.checkName = function(data, id) {
			if (id === 2 && data !== 'awesome') {
			  return "Username 2 should be `awesome`";
			}
		  };

		  $scope.saveUser = function(data, id) {
			//$scope.user not updated yet
			angular.extend(data, {id: id});
			return $http.post('/saveUser', data);
		  };

		  // remove user
		  $scope.removeUser = function(index) {
			$scope.users.splice(index, 1);
		  };
		});

	// --------------- mock $http requests ----------------------
		app.run(function($httpBackend) {
		  $httpBackend.whenGET('/groups').respond([
			{id: 1, text: 'user'},
			{id: 2, text: 'customer'},
			{id: 3, text: 'vip'},
			{id: 4, text: 'admin'}
		  ]);
		
		  $httpBackend.whenPOST(/\/saveUser/).respond(function(method, url, data) {
			data = angular.fromJson(data);
			return [200, {status: 'ok'}];
		  });
		});
	  </script>
      
    </body>
</html>
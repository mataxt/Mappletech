<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
        <title>Mappletech - Login</title>
    
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
   
    <!-- Template js -->
    <script src="<%=request.getContextPath()%>/resources/UI/js/jquery-2.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/UI/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/UI/js/jquery.appear.js"></script>
    <script src="<%=request.getContextPath()%>/resources/UI/js/jqBootstrapValidation.js"></script>
    <script src="<%=request.getContextPath()%>/resources/UI/js/modernizr.custom.js"></script>
    
    <script>
		$(document).ready(function() {
			$(".select2dropdown").select2()({
				maximumSelectionLength: 1
			});
		});
    </script>
    
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
                    <a href="/">
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
                                <h3>Profil</h3>
                                <p>Hantera din profil här</p>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-6">
                                <form class="form-change" action="/profil/index.php" method="post">
                       
                            		<div class="form-group">
                                    	<label>Namn</label>
                                    	<input type="text" class="form-control" placeholder="Test Test" value="Test Test" disabled>
                                 	</div>
                                    
                                    <div class="form-group">
                                    	<label>E-post</label>
                                    	<input type="text" class="form-control" placeholder="test@test.se" value="test@test.se" disabled>
                                 	</div>
                                    
                                    <div class="form-group">
                                    	<label>Telefon</label>
                                    	<input type="text" class="form-control" placeholder="08-1234567" value="08-1234567" disabled>
                                 	</div>
                                    
                                    <div class="form-group">
                                    	<label>Mobil</label>
                                    	<input type="text" class="form-control" placeholder="+4612345678" value="+4612345678" disabled>
                                 	</div>
                                    
                                    <div class="form-group">
                                    	<label>Adress</label>
                                    	<input type="text" class="form-control" placeholder="Föreningsvägen 12 tr1" value="Föreningsvägen 12 tr1" disabled>
                                 	</div>  
                                </form>
                                
                            </div>
                            
                            <div class="col-md-6">
                            	<form class="form-change" action="/admin/andra-anvandare/index.php" method="post">
                   
                                  <label>Epost</label>
                                  <div class="form-group">
                                     
                                     <select class="form-control" name="mail">
                                         <option value="test@test.com">test@test.com</option>
                                         <option value="test2@test2.com">test2@test2.com</option>
                                         <option value="test3@test3.com">test3@test3.com</option>
                                     </select>
                                  </div> 
                                    
                                 <div class="form-group ">
                                 <label>Namn</label>
                                    
                                     <div class="input-group input-group-sm ">
                                       
                                        <input type="text" class="form-control" placeholder="Förnamn och Efternamn..." name="nametext" maxlength="250">
                                        
                                        <span class="input-group-btn">
                                          <button type="submit" class="btn btn-info btn-flat" name="name">Ändra</button>
                                        </span>
                                     </div>
                                     
                                     <span style="color:#f56954"></span>
                                 </div>
                                  
                                  <div class="form-group ">
                                  <label>Lösenord</label>
                                  
                                      <div class="input-group input-group-sm ">
                                       
                                        <input type="password" class="form-control" placeholder="Minst 6 tecken..." name="passwordtext1" maxlength="128">
                                        <input type="password" class="form-control" placeholder="Bekräfta lösenord..." name="passwordtext2" maxlength="128">
                    
                                        <span class="input-group-btn">
                                          <button type="submit" class="btn btn-info btn-flat" name="password">Ändra</button>
                                        </span>
                                      </div>
                                      
                                      <span style="color:#f56954"></span>
                                      <span style="color:#f56954"></span>
                                      <span style="color:#f56954"></span>
                                  </div>
                                  
                                  <div class="form-group ">
                                  <label>Telefon</label>
                                    
                                      <div class="input-group input-group-sm ">
                                       
                                        <input type="text" class="form-control" placeholder="Telefon..." name="phonetext" maxlength="250">
                                        
                                        <span class="input-group-btn">
                                          <button type="submit" class="btn btn-info btn-flat" name="phone">Ändra</button>
                                        </span>
                                      </div>
                                      
                                      <span style="color:#f56954"></span>
                                  </div>
                                  
                                  <div class="form-group">
                                  <label>Rättigheter</label>
                                  
                                      <div class="input-group input-group-sm">
                                          
                                          <select class="form-control" name="privilegeOption">
                                              <option value="2">Användare</option>
                                              <option value="1">Avancerad användare</option>
                                            <option value="0">Administratör</option>
                                          </select>
                                          
                                          <span class="input-group-btn">
                                              <button type="submit" class="btn btn-info btn-flat" name="privilege">Ändra</button>
                                          </span>
                                          
                                      </div>
                                  </div>
                                  
                                  <span style="color:#00a65a"></span>
                                  
                                  </form>
                            </div>
                        </div>
                        
                        <p>&nbsp;</p>
                        
                        <div class="row">
                        	<div class="col-md-12">
                  				 <button type="submit" class="btn btn-success" name="privilege">Uppdatera profil</button>
                  			</div>
                        </div>
                        
                  </div>
                </div>
              </div>
              
            </div>
        <!-- End Main Body Section -->
      
    </body>
</html>
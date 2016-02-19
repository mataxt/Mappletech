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
        <title>Mappletech</title>
    
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
                            <h3>Användare</h3>
                            <p>Lägg till användare</p>
                        </div>
                    </div>
                    
                    <div class="row">
                    	<div class="col-md-offset-3 col-md-6 col-md-offset-3">
                            <form class="form-register" action="" method="post">
                       
                            <div class="form-group ">
                              <label>Användarnamn</label>
                              <input type="text" class="form-control" name="username" placeholder="Användarnamn..." maxlength="250">
                            </div>
                            
                            <div class="form-group ">
                              <label>Namn</label>
                              <input type="text" class="form-control" name="name" placeholder="FÃ¶rnamn och efternamn..." maxlength="250">
                            </div>
                           
                            <div class="form-group ">
                              <label>Epost</label>
                              <input type="text" class="form-control" name="email" placeholder="Ex. namn@epost.se" maxlength="250">
                              <span style="color:#f56954"></span>
                              <span style="color:#f56954"></span>
                            </div>
                            
                            <div class="form-group ">
                              <label>Telefon</label>
                              <input type="text" class="form-control" name="phone" placeholder="Telefon..." maxlength="250">
                            </div>
                            
                            <div class="form-group ">
                              <label>Mobil</label>
                              <input type="text" class="form-control" name="mobile" placeholder="Mobil..." maxlength="250">
                            </div>
                            
                            <div class="form-group ">
                              <label>Adress</label>
                              <input type="text" class="form-control" name="address" placeholder="Adress..." maxlength="250">
                            </div>
        
                            <div class="form-group">
                              <label>Rättigheter</label>
        
                              <select class="form-control" name="priv">
                                  <option value="0">Användare</option>
                                  <option value="1">Avancerad användare</option>
                                <option value="2">Administratör</option>
                              </select>
                            </div>
                            
                            <div class="box-footer">
                              <button type="submit" class="btn-lg btn btn-success" name="submit">Registrera ny användare</button>
                            </div>
        
                          </form>
                  	</div>
                    
                  </div>
                </div>
              </div>
              
            </div>
          </div>
        <!-- End Main Body Section -->
        
    </body>
</html>
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
        <title>Mappletech - Uppdatera profil</title>
    
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
    <script type="text/javascript">
    function checkPass()
    {
        //Store the password field objects into variables ...
        var pass1 = document.getElementById('passwordtext1');
        var pass2 = document.getElementById('passwordtext2');
        //Store the Confimation Message Object ...
        var message = document.getElementById('confirmMessage');
        var button = document.getElementById('passwordButton');
        //Set the colors we will be using ...
        var goodColor = "#66cc66";
        var badColor = "#ff6666";
        //Compare the values in the password field 
        //and the confirmation field
        if(pass1.value == pass2.value){
            //The passwords match. 
            //Set the color to the good color and inform
            //the user that they have entered the correct password 
            pass2.style.backgroundColor = goodColor;
            message.style.color = goodColor;
            message.innerHTML = "Passwords Match!"
            $("#passwordButton").prop('disabled', false)
        }else{
            //The passwords do not match.
            //Set the color to the bad color and
            //notify the user.
            pass2.style.backgroundColor = badColor;
            message.style.color = badColor;
            message.innerHTML = "Passwords Do Not Match!"
            $("#passwordButton").prop('disabled', true)
        }
    } 
    </script>
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
                                <h3>Profil</h3>
                                <p>Ändra din profil här</p>
                            </div>
                        </div>
                        
                        <div class="row"> 
                            <div class="col-md-offset-3 col-md-6 col-md-offset-3">
                            	<form:form modelAttribute="uservm" id="user" class="form-group"
							method="POST">
                                        
                                     <div class="form-group">
                                         <label>Namn</label>
                                            
                                         <div class="input-group input-group-sm ">  
                                            <form:input path="fullName" type="text" class="form-control" />
                        
                                            <span class="input-group-btn">
                                              <button type="submit" class="btn btn-info btn-flat" name="name">Ändra</button>
                                              
                                            </span>
                                         </div>
                                         
                                         <span style="color:#f56954"></span>
                                     </div>
                                
                                     <div class="form-group">
                                         <label>E-post</label>
                                            
                                         <div class="input-group input-group-sm ">  
                                            <form:input path="email" type="text" class="form-control" />
                        
                                            <span class="input-group-btn">
                                              <button type="submit" class="btn btn-info btn-flat" name="emailButton">Ã„ndra</button>
                                              
                                            </span>
                                         </div>
                                         
                                         <span style="color:#f56954"></span>
                                     </div>
                                
                                      <div class="form-group">
                                          <label>Telefon</label>
                                            
                                          <div class="input-group input-group-sm ">
                                            <form:input path="phoneNumber" type="text" class="form-control" />
                                            
                                            <span class="input-group-btn">
                                              <button type="submit" class="btn btn-info btn-flat" name="phone">ÃÄndra</button>
                                            </span>
                                            
                                          </div>
                                          
                                          <span style="color:#f56954"></span>
                                      </div>
                                
                                      <div class="form-group">
                                          <label>Mobil</label>
                                            
                                          <div class="input-group input-group-sm ">
                                            <form:input path="mobileNumber" type="text" class="form-control" placeholder="Nytt mobil nummer..." name="mobiletext"/>
                                            
                                            <span class="input-group-btn">
                                              <button type="submit" class="btn btn-info btn-flat" name="mobile">Ändra</button>
                                            </span>
                                            
                                          </div>
                                          
                                          <span style="color:#f56954"></span>
                                      </div>
                                      
                                      <br />
                                
                                      <div class="form-group">    
                                          <label>Lösenord</label>
                                          
                                          <div class="form-group"></div>
                                          
                                          <div class="input-group input-group-sm ">
                                            <input type="password" class="form-control" placeholder="Nytt lÃ¶senord..." name="passwordtext1" id="passwordtext1">
                                            <form:input path="password" type="password" class="form-control" placeholder="BekrÃ¤fta lÃ¶senord..." name="passwordtext2" id="passwordtext2" onkeyup="checkPass(); return false;"/>
                                            <span id="confirmMessage" class="confirmMessage"></span>
                                            <span class="input-group-btn">
                                              <button type="submit" class="btn btn-info btn-flat" name="passwordButton" id="passwordButton" disabled>Ã„ndra</button>
                                            </span>
                                          </div>
                                          
                                          <span style="color:#f56954"></span>
                                      </div>

                                  </form:form>
                            </div>
                        </div>
                        
                  </div>
                </div>
              </div>
              
            </div>
        <!-- End Main Body Section -->
      
    </body>
</html>
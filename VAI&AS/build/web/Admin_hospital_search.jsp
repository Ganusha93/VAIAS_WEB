<%-- 
    Document   : Admin - hosptal - search
    Created on : Apr 25, 2017, 8:38:12 PM
    Author     : Dilum
--%>

<%@page import="com.ucsc.vaias.model.Hospital"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hospital</title>
	<!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
     <!-- MORRIS CHART STYLES-->
    <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
   
   <script type="text/javascript">
            
        function showData(value){
            
            $.ajax({
                url : "HospitalAdminController?query="+value,
                type : "POST",
                async : false,
                success : function(data) {
                    $("#autocomplete").fadeIn();
                    $("#autocomplete").html(data);
                }
                
            });
          
         $(document).on('click','li',function(){
             $("#name").val($(this).text());
             $("#autocomplete").fadeOut();
         });  
         
            
        }
        
        </script>
</head>
    
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="Admin - hosptal - search.jsp">Hospital</a> 
            </div>
  <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;"> 26 April 2017 &nbsp; <a style="color: white;" href="Admin_dashboard.jsp" class="btn btn-info square-btn-adjust">Admin Panel</a><a href="index.jsp" class="btn btn-info square-btn-adjust">Logout</a> </div>
        </nav>   
           <!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
				<li class="text-center">
                    <img src="assets/img/hospital.png" class="user-image img-responsive"/>
					</li>
				
					
                    <li>
                        <a   href="Admin_hospital.jsp"><i class="fa fa-dashboard fa-3x"></i> Dashboard</a>
                    </li>
                     <li>
                         <a class="active-menu" href="Admin_hospital_search.jsp"><i class="fa fa-desktop fa-3x"></i> Search</a>
                    </li>
                    <li>
                        <a  href="Admin_hospital_register.jsp"><i class="fa fa-qrcode fa-3x"></i> Registration</a>
                    </li>
		    <li  >
                        <a   href="Admin_hospital_update.jsp"><i class="fa fa-bar-chart-o fa-3x"></i> Update</a>
                    </li>	
                      	
                </ul>
               
            </div>
            
        </nav>  
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                        <% Hospital hospital=(Hospital)request.getAttribute("hospitalNames");
                        try{
                        if(hospital.getHID()==null){
                          %> <script type="text/javascript">
                            alert("No Data Found");
                        </script><%
                                }}catch(Exception e){}   
                            
                        
                        %>
                            
                    <div class="col-md-8 col-sm-8 scrollpoint sp-effect1">
                        <form name="sel" style="margin-left: 15%; width: 90%;"class="form-horizontal" action="HospitalAdminController?type=selbyName" method="post"  role="form" id="search">
                                            <div class="form-group ">
                                                <label class="control-label col-sm-2" for="user_id"></label>
                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control" name="hospitalName" id="name" placeholder="Enter User Name to Search" onkeyup="showData(this.value);">
                                                        <div style="position: absolute; z-index: 2; list-style: none; background-color: #F0F8FF; width: 92%;" id="autocomplete" ></div>
                                                       
                                                </div>
                                                <input type="submit"  class="btn btn-primary" class="fa fa-edit " value="search">
                                                 </form>
                                                 <br><br><br>
                                        <%try{ %>
                                        <div style="z-index: 4;" id="big">
                                                <div class="form-group" >
                                                <label class="control-label col-sm-2" for="fname">PID:</label>
                                                <div class="col-sm-10">
                                                    <div class="form-control">  <% try{ if(hospital.getHID()==null){out.println("");}else{out.println(hospital.getHID());}}catch(Exception e){} %></div>
                                                </div>
                                            </div  >
                                                <div class="form-group">
                                                <label class="control-label col-sm-2" for="fname">HOSPITAL NAME:</label>
                                                <div class="col-sm-10">
                                                    <div class="form-control"><% try{ if(hospital.getPROVINCE()==null){out.println("");}else{out.println(hospital.getPROVINCE());}}catch(Exception e){} %></div>
                                                </div>
                                            </div>
                                               
                                                <div class="form-group">
                                                <label class="control-label col-sm-2" for="fname">PROVINCE:</label>
                                                <div class="col-sm-10">
                                                    <div class="form-control"><% try{ if(hospital.getHID()==null){out.println("");}else{out.println(hospital.getPROVINCE());}}catch(Exception e){} %></div>
                                                </div>
                                            </div>
                                                <div class="form-group">
                                                <label class="control-label col-sm-2" for="fname">DISTRICT:</label>
                                                <div class="col-sm-10">
                                                    <div class="form-control"><% try{ if(hospital.getHID()==null){out.println("");}else{out.println(hospital.getDISTRICT());}}catch(Exception e){} %></div>
                                                </div>
                                            </div>
                                                <div class="form-group">
                                                <label class="control-label col-sm-2" for="fname">CITY</label>
                                                <div class="col-sm-10">
                                                    <div class="form-control"><% try{ if(hospital.getHID()==null){out.println("");}else{out.println(hospital.getCITY());}}catch(Exception e){} %></div>
                                                </div>
                                            </div>
                                                <div class="form-group">
                                                <label class="control-label col-sm-2" for="fname">TELEPHONE NO</label>
                                                <div class="col-sm-10">
                                                    <div class="form-control"><% try{ if(hospital.getHID()==null){out.println("");}else{out.println(hospital.getTP());}}catch(Exception e){} %></div>
                                                </div>
                                            </div>
                                                <div class="form-group">
                                                <label class="control-label col-sm-2" for="fname">LATITUDE</label>
                                                <div class="col-sm-10">
                                                    <div class="form-control"><% try{ if(hospital.getHID()==null){out.println("");}else{out.println(hospital.getLAT());}}catch(Exception e){} %></div>
                                                </div>
                                            </div>
                                                <div class="form-group">
                                                <label class="control-label col-sm-2" for="fname">LONGITUDE</label>
                                                <div class="col-sm-10">
                                                    <div class="form-control"><% try{ if(hospital.getHID()==null){out.println("");}else{out.println(hospital.getLON());}}catch(Exception e){} %></div>
                                                </div>
                                            
                                               
                                        </div>
                                                <% }catch(Exception e){
                                                        
                                                        } %>
                                            </div>                 

                    

                </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.10.2.js"></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
     <!-- MORRIS CHART SCRIPTS -->
     <script src="assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/js/morris/morris.js"></script>
      <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
    
   
</body>
</html>

<%-- 
    Document   : Admin_Reports_accidents
    Created on : Jul 19, 2017, 6:03:32 AM
    Author     : Shaii99
--%>

<%@page import="com.ucsc.vaias.model.PostAccident"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Accident Reports</title>
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
    </head>
    <body>
        <jsp:include page="//VehicleController?type=accTable"/>
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="Admin_Reports.jsp">Reports</a> 
                </div>
                <div style="color: white;
                     padding: 15px 50px 5px 50px;
                     float: right;
                     font-size: 16px;"> Last access : 26 April 2017 &nbsp; <a style="color: white;" href="Admin_dashboard.jsp" class="btn btn-info square-btn-adjust">Admin Panel</a><a href="index.jsp" class="btn btn-info square-btn-adjust">Logout</a> </div>
            </nav>   
            <!-- /. NAV TOP  -->
            <nav class="navbar-default navbar-side" role="navigation">
                <div class="sidebar-collapse">
                    <ul class="nav" id="main-menu">
                        <li class="text-center">
                            <img src="assets/img/accident.png" class="user-image img-responsive"/>
                        </li>


                        <li>
                            <a href="Admin_Reoprts.jsp"><i class="fa fa-dashboard fa-3x"></i> Dashboard</a>
                        </li>
                        <li>
                            <a class="active-menu" href="Admin_Reports_accidents.jsp"><i class="fa fa-desktop fa-3x"></i> Accidents</a>
                        </li>
                        <li>
                            <a  href="Admin_Reports_areas.jsp"><i class="fa fa-qrcode fa-3x"></i> Areas</a>
                        </li>
                        <li  >
                            <a   href="Admin_Reports_customized.jsp"><i class="fa fa-bar-chart-o fa-3x"></i> Customized Reports</a>
                        </li>	

                    </ul>

                </div>

            </nav>

            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Accident Reports</h2>   

                        </div>
                    </div>              
                    <!-- /. ROW  -->
                    <hr />

                    <!-- /. ROW  -->
                    <hr />                

                    <!-- /. ROW  -->

                    <!-- /. ROW  -->
                    <div class="row" style="width: 135%">

                        <div class="col-md-9 col-sm-12 col-xs-12" >

                            <div class="panel panel-default" >
                                <div class="panel-heading">

                                </div>
                                <div class="panel-body" style="width: 100%">
                                    <div class="table-responsive" >
                                        <table class="table table-striped table-bordered table-hover" >
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Time</th>
                                                    <th>LAT</th>
                                                    <th>LON</th>
                                                    <th>User_ID</th>
                                                    <th>Vehicle_ID</th>
                                                    <th>Police Station Informed</th>
                                                    <th>Hospital Informed</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    try{
                                                    ArrayList<PostAccident> list = (ArrayList<PostAccident>) request.getAttribute("list");
                                                    for (PostAccident accident : list) {
                                                %>
                                                <tr>
                                                    <td><% out.println(accident.getDATE()); %></td>
                                                    <td><% out.println(accident.getTIME()); %></td>
                                                    <td><% out.println(accident.getLAT()); %></td>
                                                    <td><% out.println(accident.getLON()); %></td>
                                                    <td><% out.println(accident.getUID()); %></td>
                                                    <td><% out.println(accident.getVID()); %></td>
                                                    <td><% out.println(accident.getHID()); %></td>
                                                    <td><% out.println(accident.getPID()); %></td>
                                                   
                                                </tr>

                                                <%     }
}catch(Exception e){
System.out.print("sdsad");
}
                                                %>

                                            </tbody>
                                        </table>

                                        <div class="form-group"> 
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button type="button" class="btn btn-primary" id="mapAdd" >MAP</button>
                                                <button type="submit" class="btn btn-default">Generate Report</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- /. ROW  -->

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

        <script>
            $('#mapAdd').click(function () {
                $('#mapcontainer').toggle("slow");
                document.getElementById("wrapper").style.opacity = "0.5";

            });

            $('#ok').click(function () {
                $('#mapcontainer').toggle("slow");
                document.getElementById("wrapper").style.opacity = "1";

            });

            $('#cancel').click(function () {
                $('#mapcontainer').toggle("slow");
                document.getElementById("wrapper").style.opacity = "1";
            });
        </script>


    </body>
</html>

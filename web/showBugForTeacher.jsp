<%-- 
    Document   : index
    Created on : Jul 1, 2018, 9:32:36 PM
    Author     : Showrov
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!--================================================================================
          Item Name: Materialize - Material Design Admin Template
          Version: 4.0
          Author: PIXINVENT
          Author URL: https://themeforest.net/user/pixinvent/portfolio
    ================================================================================ -->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="msapplication-tap-highlight" content="no">
        <title> Admin Panel </title>
        <!-- Favicons-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" href="https://himel007.github.io/projectCSS/img/favicon-32x32.png" sizes="32x32">
        <!-- Favicons-->
        <link rel="apple-touch-icon-precomposed" href="https://himel007.github.io/projectCSS/img/apple-touch-icon-152x152.png">
        <!-- For iPhone -->
        <meta name="msapplication-TileColor" content="#00bcd4">
        <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
        <!-- For Windows Phone -->
        <!-- CORE CSS-->
        <link href="https://himel007.github.io/projectCSS/css/perfect-scrollbar.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/materialize.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/style.css" type="text/css" rel="stylesheet">

    </head>
    <body>
        <!-- Start Page Loading -->
        <div id="loader-wrapper">
            <div id="loader"></div>
            <div class="loader-section section-left"></div>
            <div class="loader-section section-right"></div>
        </div>
        <!-- End Page Loading -->
        <!-- //////////////////////////////////////////////////////////////////////////// -->
        <!-- START HEADER -->
        <header id="header" class="page-topbar">
            <!-- start header nav-->
            <div class="navbar-fixed">
                <nav class="navbar-color gradient-45deg-indigo-blue">
                    <div class="nav-wrapper">
                        <ul class="right hide-on-med-and-down">

                            <li>
                                <a href="javascript:void(0);" class="tooltipped waves-effect waves-block waves-light toggle-fullscreen" data-tooltip="full screen">
                                    <i class="material-icons">settings_overscan</i>
                                </a>
                            </li>

                            <li>
                                <a href="javascript:void(0);" class="waves-effect waves-block waves-light profile-button" data-activates="profile-dropdown">
                                    <i class="material-icons"> account_circle </i>
                                </a>
                            </li>
                        </ul>
                        <ul id="profile-dropdown" class="dropdown-content">
                            
                           <li>
                                <a href="chooseRoutineTeacher.jsp" class="teal-text text-darken-1"><i class="material-icons teal-text">face</i> Teacher's Routine</a>
                            </li>
                            <li>
                                <a href="ChooseRoutineStudent.jsp" class="teal-text text-darken-1"><i class="material-icons teal-text">school</i> Student's Routine</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="index.jsp" class="teal-text text-darken-1"><i class="material-icons">keyboard_tab</i> Logout</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>
        <!-- END HEADER -->


        <div id="main">
            <!-- START WRAPPER -->
            <div class="wrapper">
                <!-- START LEFT SIDEBAR NAV-->
                <aside id="left-sidebar-nav" class="nav-expanded nav-lock nav-collapsible">
                    <div class="brand-sidebar">
                        <h1 class="logo-wrapper gradient-45deg-indigo-blue">
                            <a href="azqQPo1Ad3min00Log12WTcin.jsp" class="brand-logo darken-1">
                                <img src="https://pixinvent.com/materialize-material-design-admin-template/images/logo/materialize-logo.png" alt="materialize logo">
                                <span class="logo-text hide-on-med-and-down">Main Menu</span>
                            </a>
                            <a href="#" class="navbar-toggler">
                                <i class="material-icons">radio_button_checked</i>
                            </a>
                        </h1>
                    </div>
                    <ul id="slide-out" class="side-nav fixed leftside-navigation">
                        <li class="bold">
                            <a href="UploadCourseOffer.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons cyan-text">attach_file</i>
                                <span class="nav-text">Upload Course Offer</span>
                            </a>
                        </li>
                        <li class="bold">
                            <a href="UploadRoutine.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons indigo-text">attach_file</i>
                                <span class="nav-text">Upload Routine</span>
                            </a>
                        </li>

                        <li class="active bold">
                            <a href="ViewBugForTeacher.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons red-text">find_in_page</i>
                                <span class="nav-text">Check Bug for Teacher</span>
                            </a>
                        </li>

                        <li class="bold">
                            <a href="ViewBugForStudent.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons teal-text">find_in_page</i>
                                <span class="nav-text">Check Bug for Student</span>
                            </a>
                        </li>
                        <li class="bold">
                            <a href="publishRoutine.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons green-text">event_available</i>
                                <span class="nav-text"> Publish Routine</span>
                            </a>
                        </li>
                        <li class="bold">
                            <a href="unpublishRoutine.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons red-text">event_busy</i>
                                <span class="nav-text">Unpublish Routine</span>
                            </a>
                        </li>
                        <li class="bold">
                            <a href="RoutineForAllTeacher.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons teal-text">description</i>
                                <span class="nav-text">All Teachers Routine</span>
                            </a>
                        </li>
                    </ul>
                </aside>

                <!--  Continuous classes for a teacher -->
                <center><h4 class="blue-grey-text"><u>Continuous classes for a teacher</u></h4></center>
                <table class="highlight centered responsive-table">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Teacher Initial</th>
                            <th data-field="price">Day</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> teacherList = (ArrayList<String>) request.getAttribute("tNameCC");
                            ArrayList<String> dayCC = (ArrayList<String>) request.getAttribute("dayCC");
                            for (int i = 0; i < teacherList.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(teacherList.get(i)); %></td>
                            <td><% out.println(dayCC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                

                <!-- double classes for a teaccher -->
                <br><br>
                <center><h4 class="blue-grey-text"><u>Double classes for a teacher in a single slot</u></h4></center>
                <table class="highlight centered">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Teacher Initial</th>
                            <th data-field="price">Day</th>
                            <th data-field="time">Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> tNameDC = (ArrayList<String>) request.getAttribute("tNameDC");
                            ArrayList<String> dayDC = (ArrayList<String>) request.getAttribute("dayDC");
                            ArrayList<String> timeDC = (ArrayList<String>) request.getAttribute("timeDC");
                            for (int i = 0; i < tNameDC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(tNameDC.get(i)); %></td>
                            <td><% out.println(dayDC.get(i)); %></td>
                            <td><% out.println(timeDC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                
                <br><br>
                <!-- whole day classes -->
                <center><h4 class="blue-grey-text"><u>Teacher having classes from 8:30 am to 5:30 pm</u></h4></center>
                <table class="highlight centered">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Teacher Initial</th>
                            <th data-field="price">Day</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> tNameWC = (ArrayList<String>) request.getAttribute("tNameWC");
                            ArrayList<String> dayWC = (ArrayList<String>) request.getAttribute("dayWC");
                            for (int i = 0; i < tNameWC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(tNameWC.get(i)); %></td>
                            <td><% out.println(dayWC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                
                <br><br>
                <!-- Both campus classes -->
                <center><h4 class="blue-grey-text"><u>Classes in both main and permanent campus</u></h4></center>
                <table class="highlight centered">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Teacher Initial</th>
                            <th data-field="price">Day</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> tNameBC = (ArrayList<String>) request.getAttribute("tNameBC");
                            ArrayList<String> dayBC = (ArrayList<String>) request.getAttribute("dayBC");
                            for (int i = 0; i < tNameBC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(tNameBC.get(i)); %></td>
                            <td><% out.println(dayBC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <br><br>
            </div>
        </div>
         
    </body>
</html>

<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/jquery-3.2.1.min.js"></script>
<!--materialize js-->
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/materialize.min.js"></script>
<!--scrollbar-->
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/plugins.js"></script>
<script>
    document.onkeyup = function (e) {
        if (e.ctrlKey && e.which === 77) {
            window.location.href = "./azqQPo1Ad3min00Log12WTcin.jsp";
        } 
    };
</script>
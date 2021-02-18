<%-- 
    Document   : index
    Created on : Jul 1, 2018, 9:32:36 PM
    Author     : Showrov
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
                        <!-- translation-button -->


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

                        <li class="bold">
                            <a href="ViewBugForTeacher.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons red-text">find_in_page</i>
                                <span class="nav-text">Check Bug for Teacher</span>
                            </a>
                        </li>

                        <li class="active bold">
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
                <!-- table starts here -->
                <!--  Continuous classes for a teacher -->
                <center><h4 class="blue-grey-text"><u>Continuously more than three classes</u></h4></center>
                <table class="highlight centered">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Semester</th>
                            <th data-field="price">Section</th>
                            <th data-field="day">Day</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> sNameCC = (ArrayList<String>) request.getAttribute("sNameCC");
                            ArrayList<String> dNameCC = (ArrayList<String>) request.getAttribute("dNameCC");
                            ArrayList<String> sectionCC = (ArrayList<String>) request.getAttribute("sectionCC");
                            for (int i = 0; i < sNameCC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(sNameCC.get(i)); %></td>
                            <td><% out.println(sectionCC.get(i)); %></td>
                            <td><% out.println(dNameCC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>

                <br><br>

                <center><h4 class="blue-grey-text"><u>Classes on 8:30 am and 4:00 pm</u></h4></center>
                <table class="highlight centered responsive-table">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Semester</th>
                            <th data-field="price">Section</th>
                            <th data-field="day">Day</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> sNameWC = (ArrayList<String>) request.getAttribute("sNameWC");
                            ArrayList<String> dNameWC = (ArrayList<String>) request.getAttribute("dNameWC");
                            ArrayList<String> sectionWC = (ArrayList<String>) request.getAttribute("sectionWC");
                            for (int i = 0; i < sNameWC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(sNameWC.get(i)); %></td>
                            <td><% out.println(sectionWC.get(i)); %></td>
                            <td><% out.println(dNameWC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>

                <br><br>
                <center><h4 class="blue-grey-text"><u>5 day classes in a week</u></h4></center>
                <table class="highlight centered">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Semester</th>
                            <th data-field="price">Section</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> sNameFC = (ArrayList<String>) request.getAttribute("sNameFC");
                            ArrayList<String> sectionFC = (ArrayList<String>) request.getAttribute("sectionFC");
                            for (int i = 0; i < sNameFC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(sNameFC.get(i)); %></td>
                            <td><% out.println(sectionFC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>


                <br><br>
                <center><h4 class="blue-grey-text"><u>Single class in a day</u></h4></center>
                <table class="highlight centered">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Semester</th>
                            <th data-field="price">Section</th>
                            <th data-field="price">Day</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> sNameSC = (ArrayList<String>) request.getAttribute("sNameSC");
                            ArrayList<String> sectionSC = (ArrayList<String>) request.getAttribute("sectionSC");
                            ArrayList<String> dNameSC = (ArrayList<String>) request.getAttribute("dNameSC");
                            for (int i = 0; i < sNameSC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(sNameSC.get(i)); %></td>
                            <td><% out.println(sectionSC.get(i)); %></td>
                            <td><% out.println(dNameSC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>


                <br><br>
                <center><h4 class="blue-grey-text"><u>Gap more than 1:30 hours</u></h4></center>
                <table class="highlight centered">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Semester</th>
                            <th data-field="price">Section</th>
                            <th data-field="price">Day</th>
                            <th data-field="price">Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> sNameGC = (ArrayList<String>) request.getAttribute("sNameGC");
                            ArrayList<String> sectionGC = (ArrayList<String>) request.getAttribute("sectionGC");
                            ArrayList<String> dNameGC = (ArrayList<String>) request.getAttribute("dNameGC");
                            ArrayList<String> timeGC = (ArrayList<String>) request.getAttribute("timeGC");
                            for (int i = 0; i < sNameGC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(sNameGC.get(i)); %></td>
                            <td><% out.println(sectionGC.get(i)); %></td>
                            <td><% out.println(dNameGC.get(i)); %></td>
                            <td><% out.println(timeGC.get(i)); %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>


                <br><br>
                <center><h4 class="blue-grey-text"><u>Missing class for a course</u></h4></center>
               
                
                
                <div class="fixed-action-btn">
                <a href="#modal1" class="tooltipped modal-trigger pulse btn-floating btn-large indigo z-depth-3" data-position="top" data-tooltip="course list">
                    <i class="large material-icons">info</i>
                </a>
            </div>
                

                <%
                    String latestCourseOfferName = "";
                    Connection connection = null;
                    String path = this.getClass().getClassLoader().getResource("").getPath();
                    try {
                        Class.forName("org.sqlite.JDBC");
                        String ConnectionURL = "jdbc:sqlite:" + path + "com/FilesPack/" + "RoutineExample.sqlite";
                        connection = DriverManager.getConnection(ConnectionURL);
                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println(e.toString());
                    }

                    
                    Statement st1 = null;
                    ResultSet rs1 = null;
                    st1 = connection.createStatement();
                    rs1 = st1.executeQuery("SELECT * FROM ExcelCourseOffer");

                    while (rs1.next()) {
                        latestCourseOfferName = rs1.getString("FileNameCourse");
                    }
                    st1.close();
                    rs1.close();
                    
                    Statement st2 = connection.createStatement();
                    ResultSet rs2 = st2.executeQuery("SELECT DISTINCT courseCode, Semester FROM " + latestCourseOfferName);
                %>
                <!-- Modal Structure -->
                <div id="modal1" class="modal">
                    <div class="modal-content">
                        <center><h4 class="blue-text">All semester courses</h4></center>
                        <table class="highlight centered">
                            <thead>
                                <tr>
                                    <th class="teal-text">Semester</th>
                                    <th class="teal-text">Course code</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    while(rs2.next()){  
                                %>
                                <tr>
                                    <td><% out.println(rs2.getString("Semester")); %></td>
                                    <td><% out.println(rs2.getString("courseCode")); %></td>
                                </tr>
                                <%
                                    }st2.close();
                                    rs2.close();
                                %>
                            </tbody>
                        </table>

                    </div>
                    <div class="modal-footer">
                        <a href="#!" class="modal-close btn-floating btn-small waves-effect waves-light gradient-45deg-purple-deep-orange"><i class="material-icons">clear</i>
                        </a>
                    </div>
                </div>
                <table class="highlight centered">
                    <thead class="cyan darken-1 white-text">
                        <tr>
                            <th data-field="id">Serial</th>
                            <th data-field="name">Course Code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<String> courseMC = (ArrayList<String>) request.getAttribute("courseMC");
                            for (int i = 0; i < courseMC.size(); i++) {
                        %>
                        <tr>
                            <td><% out.println(i + 1); %></td>
                            <td><% out.println(courseMC.get(i)); %></td>
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
        if (e.ctrlKey && e.which == 77) {
            window.location.href = "./azqQPo1Ad3min00Log12WTcin.jsp";
        }
    };
</script>

<script>
    $(document).ready(function () {
        $('.modal').modal();
    });
</script>
<%-- 
    Document   : index
    Created on : Jul 1, 2018, 9:32:36 PM
    Author     : Showrov
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
        <link rel="apple-touch-icon-precomposed" href="https://himel007.github.io/projectCSS/img/apple-touch-icon-152x152.png" sizes="32x32">
        <!-- For iPhone -->
        <meta name="msapplication-TileColor" content="#00bcd4">
        <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
        <!-- For Windows Phone -->
        <!-- CORE CSS-->
        <link href="https://himel007.github.io/projectCSS/css/materialize.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/style.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/perfect-scrollbar.css" type="text/css" rel="stylesheet">
        <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
        <link href="https://himel007.github.io/projectCSS/css/dashboardCustom.css" type="text/css" rel="stylesheet">
        <link href="assets/css/dropDown.css" type="text/css" rel="stylesheet">
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
                                    <i class="material-icons">account_circle</i>
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
        <div id="main">
            <div class="wrapper">
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
                        <li class="bold">
                            <a href="ViewBugForStudent.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons teal-text">find_in_page</i>
                                <span class="nav-text">Check Bug for Student</span>
                            </a>
                        </li>
                        <li class="active bold">
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
                                <span class="nav-text">All teachers routine</span>
                            </a>
                        </li>
                    </ul>
                    
                </aside>                

                
                <%

                    Connection connection = null;
                    String path = this.getClass().getClassLoader().getResource("").getPath();
                    try {
                        Class.forName("org.sqlite.JDBC");
                        String ConnectionURL = "jdbc:sqlite:" + path + "com/FilesPack/" + "RoutineExample.sqlite";
                        connection = DriverManager.getConnection(ConnectionURL);
                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println(e.toString());
                    }

                    Statement st = null;
                    ResultSet rs = null;
                    st = connection.createStatement();
                    rs = st.executeQuery("select FileName from ExcelFileName WHERE Status LIKE 'Unpublished' ORDER BY FileID DESC ;");
                %>

                <form name="myForm" action="publishRoutine" method="post">      
                    <div class="row">
                        <div class="input-field col s12">
                            <select class="selectBox" name ="SelectedRoutine" style="width: 250px !important; min-width: 250px; max-width: 250px;">
                                <%while (rs.next()) {%>
                                <option class="option"
                                        value="<%=rs.getString("FileName")%>"> <%=rs.getString("FileName")%>
                                </option>
                                <%}%>           
                            </select> 
                            <label class="teal-text" style="font-size: 15px; font-weight: bolder; margin-left: 1%;"> Select routine version to publish </label>
                        </div>
                    </div>
                    <br>

                 
                    <center>
                        <button class="btn waves-effect waves-light cyan darken-1 border-round z-depth-1" id="swal-button3" style="font-weight: bolder" type="submit" name="submit">submit
                            <i class="material-icons right">keyboard_arrow_right</i>
                        </button>
                    </center>
                </form>
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
    $('#swal-button3').on('click', function() {
    swal("Good job!", "You clicked the button!", "success");
});
    
</script>
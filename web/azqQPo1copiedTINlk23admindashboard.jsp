<%-- 
    Document   : index
    Created on : Jul 1, 2018, 9:32:36 PM
    Author     : Showrov
--%>

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
                                <a href="chooseRoutineTeacher.jsp" class="teal-text text-darken-2"><i class="material-icons teal-text">face</i> Teacher's Routine</a>
                            </li>
                            <li>
                                <a href="ChooseRoutineStudent.jsp" class="teal-text text-darken-2"><i class="material-icons teal-text">school</i> Student's Routine</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="index.jsp" class="teal-text text-darken-2"><i class="material-icons">keyboard_tab</i> Logout</a>
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
                                <i class="material-icons orange-text">find_in_page</i>
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
                     <a href="#" data-activates="slide-out" class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only gradient-45deg-light-blue-cyan gradient-shadow">
            <i class="material-icons">menu</i>
                </aside>

                <div class="row">
                    <ul class="collapsible popout">  
                        <li>
                            <div class="collapsible-header"><i class="material-icons circle default">grade</i>About upload course offer </div>
                            <div class="collapsible-body"><span>In <a href="UploadCourseOffer.jsp"><b class="blue-text"><u>Upload Course Offer</u></b></a> page administrator can upload <b>.xlsx</b> file of the course offer of the respected semester. <b>.xlsx</b> file should be in a required format.
                                    If the file successfully uploads then administrator will be redirected to main menu/ dashboard with a successful toast. For demonstration purpose <a href="https://github.com/himel007/projectCSS/blob/master/Demo%20Files/Course_Offer_Summer_2018.xlsx"><b class="blue-text"><u>click</u></b></a> here.</span></div>
                        </li>
                        
                        <center class="blue-grey-text"><h4>Overview about this application</h4></center><br>
                        
                       <li>
                            <div class="tooltipped collapsible-header" data-tooltip="click me"><i class="material-icons circle default">grade</i>About upload course offer </div>
                            <div class="collapsible-body"><span>In <a href="UploadCourseOffer.jsp"><b class="blue-text"><u>Upload Course Offer</u></b></a> page administrator can upload <b>.xlsx</b> file of the course offer of the respected semester. <b>.xlsx</b> file should be in a required format.
                                    If the file successfully uploads then administrator will be redirected to main menu / dashboard with a successful toast. For demonstration purpose a .xlsx file of course offer file is <a href="https://github.com/himel007/projectCSS/blob/master/Demo%20Files/Course_Offer_Summer_2018.xlsx"><b class="blue-text"><u>linked</u></b></a> here.</span></div>
                        </li>
                        <li>
                            <div class="tooltipped collapsible-header" data-tooltip="click me"><i class="material-icons blue-text">grade</i>About upload routine</div>
                            <div class="collapsible-body"><span>In <a href="UploadRoutine.jsp"><b class="blue-text"><u>Upload Routine</u></b></a> page administrator can upload <b>.xlsx</b> file of the routine of the respected semester. <b>.xlsx</b> file should be in a required format. 
                                    If the file successfully uploads then administrator will be redirected to main menu / dashboard with a successful toast. For demonstration purpose a .xlsx file of routine version is <a href="https://github.com/himel007/projectCSS/blob/master/Demo%20Files/Spring_2018.xlsx"><b class="blue-text"><u>linked</u></b></a> here</span></div>
                        </li>
                        <li>
                            <div class="tooltipped collapsible-header" data-tooltip="click me"><i class="material-icons red-text">grade</i>About bug for teachers</div>
                            <div class="collapsible-body"><span>In <a href="ViewBugForTeacher.jsp"><b class="blue-text"><u>Check Bug for Teacher</u></b></a> page there is a drop down which contains all uploaded routine versions. Administrator can select any version. After submissioion a page will be shown up containing all bugs
                                    for teachers of the selected routine version. </span></div>
                        </li>
                        <li>
                            <div class="tooltipped collapsible-header" data-tooltip="click me"><i class="material-icons indigo-text darken-1">grade</i>About bug for students</div>
                            <div class="collapsible-body"><span>In <a href="ViewBugForStudent.jsp"><b class="blue-text"><u>Check Bug for Student</u></b></a> page there is a drop down which contains all uploaded routine versions. Administrator can select any version. After submission a page will be shown up containing all bugs
                                    for students of the selected routine version. </span></div>
                        </li>
                        <li>
                            <div class="tooltipped collapsible-header" data-tooltip="click me"><i class="material-icons teal-text">grade</i>About publish routine</div>
                            <div class="collapsible-body"><span>In <a href="publishRoutine.jsp"><b class="blue-text"><u>Publish Routine</u></b></a> page there is a drop down containing all uploaded but unpublished routine versions. Administrator can select any version. When he clicks on publish button the selected routine version will be available for the students and teachers.
                                    If the selected routine version successfully published then administrator will be redirected to main menu / dashboard with a successful toast.</span></div>
                        </li>
                        <li>
                            <div class="tooltipped collapsible-header" data-tooltip="click me"><i class="material-icons orange-text">grade</i>About unpublish routine</div>
                            <div class="collapsible-body"><span>In <a href="unpublishRoutine.jsp"><b class="blue-text"><u>Unpublish Routine</u></b></a> page there is a drop down containing all uploaded and published routine versions which have already been available for teachers and students. Administrator can select any version. When he clicks on unpublish button the selected routine version will not be available for the students and teachers.
                                    If the selected routine version successfully unpublished then administrator will be redirected to main menu / dashboard with a successful toast.</span></div>
                        </li>
                        <li>
                            <div class="tooltipped collapsible-header" data-tooltip="click me"><i class="material-icons green-text">grade</i>About all teachers routine</div>
                            <div class="collapsible-body"><span>In <a href="RoutineForAllTeacher.jsp"><b class="blue-text"><u>All Teachers Routine</u></b></a> page there is a drop down containing all uploaded and published routine versions. Administrator can select any version. When he clicks on submit button then a page will be shown up containing all teachers routine with the teacher initial of each teacher's routine in a table.</span></div>
                        </li>
                    </ul>
                </div>
                
            </div>                     
        </div>
        
       <footer id="index-footer-box" class="page-footer indigo">
                <div class="footer-copyright">
                    <div class="container">
                        &copy; Copyright 2018 <a href="https://github.com/TeamTigers-IT" class="white-text">TeamTigers</a> All rights reserved.
                    </div>
                </div>
            </footer>
    </body>
</html>

<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/jquery-3.2.1.min.js"></script>
<!--materialize js-->
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/materialize.min.js"></script>
<!--scrollbar-->
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/plugins.js"></script>
<script>
    $(window).on('load', function () {
        setTimeout(function () {
            Materialize.toast('<span> Change successfully done 😃 </span>', 2000, 'rounded');
        }, 900);
    });
</script>

<script>

   $(document).ready(function(){
    $('.collapsible').collapsible();
  });
</script>
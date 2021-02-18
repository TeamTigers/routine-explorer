<%-- 
    Document   : index
    Created on : Jul 1, 2018, 9:32:36 PM
    Author     : Showrov
--%>

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
        <link rel="apple-touch-icon-precomposed" href="https://himel007.github.io/projectCSS/img/apple-touch-icon-152x152.png" sizes="32x32">
        <!-- For iPhone -->
        <meta name="msapplication-TileColor" content="#00bcd4">
        <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
        <!-- For Windows Phone -->
        <!-- CORE CSS-->
        <link href="https://himel007.github.io/projectCSS/css/materialize.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/style.css" type="text/css" rel="stylesheet">
        <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
        <link href="https://himel007.github.io/projectCSS/css/perfect-scrollbar.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/custom.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/customToast.css" type="text/css" rel="stylesheet">
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
                        <li class="active bold">
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
                <section id="content">
                    <div class="container">
                        <div class="card">
                            <div class="card-content">
                                <center>
                                    <h5>Things you should keep in mind before uploading <strong class="red-text">Routine File</strong>.</h5>
                                </center>
                            </div>
                            <div class="card-tabs blue-grey darken-1">
                                <ul class="tabs tabs-fixed-width">
                                    <li class="tab"><a class="active" href="#test4">Nota Bene</a></li>
                                    <li class="tab"><a href="#test5">Uploading Procedure</a></li>
                                </ul>
                            </div>
                            <div class="card-content white">
                                <div id="test4">
                                    <ul>
                                        <li>File name should not start with numbers <b>e.g. 1RoutineVersion</b>.</li><br>
                                        <li>File should not contain hyphen <b>e.g. Routine-version1</b></li><br>
                                        <li>You can't upload more than one file at a time.</li><br>
                                        <li>The file you are uploading should not follow the same name as the <b>previous</b> file.</li>
                                    </ul>
                                </div>
                                <div id="test5">
                                    <ul>
                                        <li>All the working data should be in <b>Sheet0</b></li><br>
                                        <li>File must be in <b>.xlsx</b> (Excel  2013-16) format </li><br>
                                        <li>File name <b>e.g. Spring2018V1</b></li>
                                    </ul>
                                </div>
                            </div>
                        </div><br><br>
                        <form  action="UploadRoutine" method="post" enctype="multipart/form-data">
                            <div class="file-field input-field">
                                <div class="btn">
                                    <span>File</span>
                                    <i class="material-icons right">launch</i>
                                    <input type="file" name="file">
                                </div>
                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                            <br>
                            <center>
                                <button id="login" class="btn waves-effect waves-light cyan darken-1 border-round" type="submit">Upload
                                    <i class="material-icons right">file_upload</i>
                                </button>
                            </center>
                        </form>    
                    </div>
                </section>
            </div>
        </div><br><br><br>
        
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
<script>
    $(window).on('load', function (){
        setTimeout(function (){
            Materialize.toast('<span>Sorry 😭 Something went wrong while uploading file</span>', 2000, 'rounded');
        }, 900);
    });
</script>
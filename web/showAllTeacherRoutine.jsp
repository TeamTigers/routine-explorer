<%-- 
    Document   : index
    Created on : Jul 1, 2018, 9:32:36 PM
    Author     : Showrov
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
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
                        <li class="active bold">
                            <a href="RoutineForAllTeacher.jsp" class="waves-effect waves-cyan">
                                <i class="material-icons teal-text">description</i>
                                <span class="nav-text">All teachers routine</span>
                            </a>
                        </li>
                    </ul>
                     </aside>

                <div id="HTMLtoPDF" style="margin-left: 9%; margin-right: 9%;">
                    <%
                        ArrayList<String> teacherList = (ArrayList<String>) request.getAttribute("teacherList");
                        ArrayList<String> tRoutine = (ArrayList<String>) request.getAttribute("tRoutine");

                    %>
                    <%                    int x = 0;
                        for (int i = 0; i < teacherList.size(); i++) {

                    %>
                    <h5 class="center" style="
                        width: 300px;
                        padding: 5px;
                        border: 2px solid green;
                        margin-left: 33%;"> Teacher Initial : <strong><% out.println(teacherList.get(i)); %> </strong></h5>
                    <br>
                    <table class="bordered centered">
                        <thead>
                            <tr>
                                <th class="teal-text">Day/Time</th>
                                <th class="teal-text">8:30 - 10:00</th>
                                <th class="teal-text">10:00 - 11:30</th>
                                <th class="teal-text">11:30 - 1:00</th>
                                <th class="teal-text">1:00 - 2:30</th>
                                <th class="teal-text">2:30 - 4:00</th>
                                <th class="teal-text">4:00 - 5:30</th>
                            </tr>

                        </thead>
                        <tbody>       
                            <tr>
                                <td class="teal-text"><b>Saturday<b/></td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++;%>
                                </td>
                            </tr>

                            <tr>
                                <td class="teal-text"><b>Sunday<b/></td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++;%>
                                </td>
                            </tr>
                            <tr>
                                <td class="teal-text"><b>Monday<b/></td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++;%>
                                </td>
                            </tr>
                            <tr>
                                <td class="teal-text"><b>Tuesday<b/></td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++;%>
                                </td>
                            </tr>
                            <tr>
                                <td class="teal-text"><b>Wednesday<b/></td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++;%>
                                </td>
                            </tr>
                            <tr>
                                <td class="teal-text"><b>Thursday<b/></td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++; %>
                                </td>
                                <td>
                                    <% out.println(tRoutine.get(x));
                                    x++;%>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br><br>

                    <%
                        }
                    %>
                </div>
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
<script src="assets/js/html2canvas.js"></script>
<script src="assets/js/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js" integrity="sha384-THVO/sM0mFD9h7dfSndI6TS0PgAGavwKvB5hAxRRvc0o9cPLohB0wb/PTA7LdUHs" crossorigin="anonymous"></script>


<script>
    document.onkeyup = function (e) {
        if (e.ctrlKey && e.which === 77) {
            window.location.href = "./azqQPo1Ad3min00Log12WTcin.jsp";
        }
    };
</script>


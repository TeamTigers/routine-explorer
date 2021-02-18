<%-- 
    Document   : index
    Created on : Jul 1, 2018, 9:32:36 PM
    Author     : Showrov
--%>

<%@page import="java.util.ArrayList"%>
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
        <title> Routine Explorer</title>
        <!-- Favicons-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" href="https://pixinvent.com/materialize-material-design-admin-template/images/favicon/favicon-32x32.png" sizes="32x32">
        <!-- Favicons-->
        <link rel="apple-touch-icon-precomposed" href="https://himel007.github.io/projectCSS/img/apple-touch-icon-152x152.png">
        <!-- For iPhone -->
        <meta name="msapplication-TileColor" content="#00bcd4">
        <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
        <!-- For Windows Phone -->
        <!-- CORE CSS-->
        <link href="https://himel007.github.io/projectCSS/css/materialize.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/style.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/perfect-scrollbar.css" type="text/css" rel="stylesheet">
        <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
        <link href="https://himel007.github.io/projectCSS/css/perfect-scrollbar.css" type="text/css" rel="stylesheet">
        <link href="https://himel007.github.io/projectCSS/css/custom.css" type="text/css" rel="stylesheet">
        <link href="assets/css/chooseStyle.css" type="text/css" rel="stylesheet">

    </head>

    <body class="white">
        <header id="header" class="page-topbar">
            <!-- start header nav-->
            <div class="navbar-fixed">
                <nav class="navbar-color gradient-45deg-indigo-blue">
                    <div class="nav-wrapper">
                        <span class="brand-logo center" style="margin-left: 4%;">Routine</span>
                        <ul class="right hide-on-med-and-down">
                            <li>
                                <a href="index.jsp" class="tooltipped" data-tooltip="home">
                                    <i class="material-icons">home</i></a>
                            </li>
                            <li>
                                <a href="ChooseRoutineStudent.jsp" class="tooltipped" data-tooltip="student's routine" >
                                    <i class="material-icons">assignment</i></a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="tooltipped waves-effect waves-block waves-light toggle-fullscreen" data-tooltip="full screen">
                                    <i class="material-icons">settings_overscan</i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>


        <div  id="HTMLtoPDF" style="margin-left: 9%; margin-right: 9%;">
            <%

                ArrayList<String> tRoutine = (ArrayList<String>) request.getAttribute("tRoutine");
            %>
            <h6 class="center">Routine version  : <b><% out.println(request.getAttribute("RoutineVersion")); %></b> || Teacher initial : <b><% out.println(request.getAttribute("CourseTaken[0]"));%></b></h6> 

            <br>

            <table class="bordered centered white">
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
                        <td> <%= tRoutine.get(0)%> </td>
                        <td><%= tRoutine.get(1)%></td>
                        <td><%= tRoutine.get(2)%></td>
                        <td><%= tRoutine.get(3)%></td>
                        <td><%= tRoutine.get(4)%></td>
                        <td><%= tRoutine.get(5)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Sunday</b></td>
                        <td> <%= tRoutine.get(6)%> </td>
                        <td><%= tRoutine.get(7)%></td>
                        <td><%= tRoutine.get(8)%></td>
                        <td><%= tRoutine.get(9)%></td>
                        <td><%= tRoutine.get(10)%></td>
                        <td><%= tRoutine.get(11)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Monday</b></td>
                        <td> <%= tRoutine.get(12)%> </td>
                        <td><%= tRoutine.get(13)%></td>
                        <td><%= tRoutine.get(14)%></td>
                        <td><%= tRoutine.get(15)%></td>
                        <td><%= tRoutine.get(16)%></td>
                        <td><%= tRoutine.get(17)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Tuesday</b></td>
                        <td> <%= tRoutine.get(18)%> </td>
                        <td><%= tRoutine.get(19)%></td>
                        <td><%= tRoutine.get(20)%></td>
                        <td><%= tRoutine.get(21)%></td>
                        <td><%= tRoutine.get(22)%></td>
                        <td><%= tRoutine.get(23)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Wednesday</b></td>
                        <td> <%= tRoutine.get(24)%> </td>
                        <td><%= tRoutine.get(25)%></td>
                        <td><%= tRoutine.get(26)%></td>
                        <td><%= tRoutine.get(27)%></td>
                        <td><%= tRoutine.get(28)%></td>
                        <td><%= tRoutine.get(29)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Thursday</b></td>
                        <td> <%= tRoutine.get(30)%> </td>
                        <td><%= tRoutine.get(31)%></td>
                        <td><%= tRoutine.get(32)%></td>
                        <td><%= tRoutine.get(33)%></td>
                        <td><%= tRoutine.get(34)%></td>
                        <td><%= tRoutine.get(35)%></td>
                    </tr>
                </tbody>
            </table><br>
        </div>
        
    <div class="fixed-action-btn">
  <a href="javascript: genPDF()" class="tooltipped btn-floating btn-large gradient-45deg-indigo-purple z-depth-3" data-position="top" data-tooltip="download as pdf">
    <i class="large material-icons">file_download</i>
  </a>
    </div>
        
    </div>
   <footer id="index-footer-box" class="page-footer indigo">
                <div class="footer-copyright">
                    <center><div class="container">
                        &copy; Copyright 2018 <a href="https://github.com/TeamTigers-IT" class="white-text">TeamTigers</a>
                    </div></center>
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

<script src="assets/js/html2canvas.js"></script>

<script src="assets/js/html2canvas.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js" integrity="sha384-THVO/sM0mFD9h7dfSndI6TS0PgAGavwKvB5hAxRRvc0o9cPLohB0wb/PTA7LdUHs" crossorigin="anonymous"></script>

<script>
    function genPDF() {
        html2canvas(document.getElementById("HTMLtoPDF"), {
            onrendered: function (canvas) {
                var img = canvas.toDataURL("image/png");
                var doc = new jsPDF('landscape');

                doc.addImage(img, 'PNG', 0, 0);
                doc.setFontSize(20);
                doc.save('Teachers_Routine.pdf');
            }
        }
        );
    }
</script>
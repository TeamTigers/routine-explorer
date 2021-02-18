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
                        <span class="brand-logo center">Routine</span>
                        <ul class="right hide-on-med-and-down">
                            <li>
                                <a href="index.jsp" class="tooltipped" data-tooltip="home">
                                    <i class="material-icons">home</i></a>
                            </li>
                            <li>
                                <a href="chooseRoutineTeacher.jsp" class="tooltipped" data-tooltip="teacher's routine" >
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

        <div id="HTMLtoPDF" style="margin-left: 9%; margin-right: 9%;">
            <h5 class="center">Routine version  : <b><% out.println(request.getAttribute("RoutineVersion")); %></b></h5>
            <h6 class="center indigo-text"><% out.println(request.getAttribute("tList")); %></h6><br>
            <%
                ArrayList<String> sRoutine = (ArrayList<String>) request.getAttribute("sRoutine");
            %>
            <table class="bordered centered white">
                <thead>
                    <tr>
                        <th class="teal-text" >Day/Time</th>
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
                        <td> <%= sRoutine.get(0)%> </td>
                        <td><%= sRoutine.get(1)%></td>
                        <td><%= sRoutine.get(2)%></td>
                        <td><%= sRoutine.get(3)%></td>
                        <td><%= sRoutine.get(4)%></td>
                        <td><%= sRoutine.get(5)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Sunday</b></td>
                        <td> <%= sRoutine.get(6)%> </td>
                        <td><%= sRoutine.get(7)%></td>
                        <td><%= sRoutine.get(8)%></td>
                        <td><%= sRoutine.get(9)%></td>
                        <td><%= sRoutine.get(10)%></td>
                        <td><%= sRoutine.get(11)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Monday</b></td>
                        <td> <%= sRoutine.get(12)%> </td>
                        <td><%= sRoutine.get(13)%></td>
                        <td><%= sRoutine.get(14)%></td>
                        <td><%= sRoutine.get(15)%></td>
                        <td><%= sRoutine.get(16)%></td>
                        <td><%= sRoutine.get(17)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Tuesday</b></td>
                        <td> <%= sRoutine.get(18)%> </td>
                        <td><%= sRoutine.get(19)%></td>
                        <td><%= sRoutine.get(20)%></td>
                        <td><%= sRoutine.get(21)%></td>
                        <td><%= sRoutine.get(22)%></td>
                        <td><%= sRoutine.get(23)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Wednesday</b></td>
                        <td> <%= sRoutine.get(24)%> </td>
                        <td><%= sRoutine.get(25)%></td>
                        <td><%= sRoutine.get(26)%></td>
                        <td><%= sRoutine.get(27)%></td>
                        <td><%= sRoutine.get(28)%></td>
                        <td><%= sRoutine.get(29)%></td>
                    </tr>
                    <tr>
                        <td class="teal-text"><b>Thursday</b></td>
                        <td> <%= sRoutine.get(30)%> </td>
                        <td><%= sRoutine.get(31)%></td>
                        <td><%= sRoutine.get(32)%></td>
                        <td><%= sRoutine.get(33)%></td>
                        <td><%= sRoutine.get(34)%></td>
                        <td><%= sRoutine.get(35)%></td>
                    </tr>
                </tbody>
            </table>

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
                doc.save('Student_Routine.pdf');
            }
        }
        );
    }
</script>
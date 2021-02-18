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
        <meta name="description" content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
        <meta name="keywords" content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
        <title> Routine Explorer </title>
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
    </head>

    <body>
        <header id="header" class="page-topbar">
            <!-- start header nav-->
            <div class="navbar-fixed">
                <nav class="navbar-color gradient-45deg-indigo-blue">
                    <div class="nav-wrapper">
                        <span class="brand-logo center">Teacher</span>
                        <ul class="right hide-on-med-and-down">
                            <li>
                                <a href="index.jsp" class="tooltipped" data-tooltip="home">
                                    <i class="material-icons">home</i></a>
                            </li>
                            <li>
                                <a href="ChooseRoutineStudent.jsp" class="tooltipped" data-tooltip="students routine" >
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
            rs = st.executeQuery("select FileName from ExcelFileName WHERE Status LIKE 'Published' ORDER BY FileID DESC");
        %>

        <form autocomplete="off" class="col s12" name="myForm" action="GenerateRoutineForTeachers" method="post">
            <div class="row"></div><div class="row"></div><div class="row"></div>
            <div class="row">
                <div class="input-field col s12">
                    <select name ="SelectedRoutine" class="selectBox">
                        <%while (rs.next()) {%>
                        <option class="option" value="<%=rs.getString("FileName")%>"> <%=rs.getString("FileName")%> </option>
                        <%}%>  
                    </select>
                    <label class="teal-text" style="font-size: 15px; font-weight: bold; margin-left: 1%;">Select version</label>
                </div>
            </div>
            <div class="row"></div><div class="row"></div>
            <div class="row">
                <div class="input-field col s12">
                    <input placeholder="e.g. DTB" type="text" class="validate" name="TeacherInitialInput">
                    <label class="teal-text" style="font-size: 19px; font-weight: bold; margin-left: 1%;">Teacher Initial</label>
                </div>
            </div>
            <div class="row"></div><div class="row"></div>
            <div class="row">
                <center>
                    <button class="btn gradient-45deg-indigo-blue white-text border-round waves-effect waves-light" type="submit" name="submit">Submit 
                        <i class="large material-icons right">keyboard_arrow_right</i>
                    </button>
                </center>
            </div>
        </div>
    </form>
    <%connection.close();%>

</body>

</html>

<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/jquery-3.2.1.min.js"></script>
<!--materialize js-->
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/materialize.min.js"></script>
<!--scrollbar-->
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/perfect-scrollbar.min.js"></script>

<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/plugins.js"></script>



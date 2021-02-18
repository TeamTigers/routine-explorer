<%-- 
    Document   : StudentsRoutine
    Created on : May 7, 2018, 11:25:06 PM
    Author     : Showrov
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Routine Explorer</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="msapplication-tap-highlight" content="no">
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
        <link href="https://himel007.github.io/projectCSS/css/custom.css" type="text/css" rel="stylesheet">
    </head>
    
    
    <body>

         <header id="header" class="page-topbar">
            <!-- start header nav-->
            <div class="navbar-fixed">
                <nav class="navbar-color gradient-45deg-indigo-blue">
                    <div class="nav-wrapper">
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

        <%!
            Connection connection = null;
            String[] SemesterCourse = new String[5];
        %>
        <%!
            String latestCourseOffer = "";

        %>

        <%
            Connection connection1 = null;
            String path1 = this.getClass().getClassLoader().getResource("").getPath();
            try {
                Class.forName("org.sqlite.JDBC");
                String ConnectionURL = "jdbc:sqlite:" + path1 + "com/FilesPack/" + "RoutineExample.sqlite";
                connection1 = DriverManager.getConnection(ConnectionURL);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.toString());
            }

            Statement st0 = null;
            ResultSet rs0 = null;
            st0 = connection1.createStatement();
            rs0 = st0.executeQuery("select * from ExcelCourseOffer");
            while (rs0.next()) {
                latestCourseOffer = rs0.getString("FileNameCourse");
            }
        %>


        <%
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

            Statement st1 = null;
            ResultSet rs1 = null;
            st1 = connection.createStatement();
            rs1 = st1.executeQuery("select DISTINCT courseCode from " + latestCourseOffer + " Where Semester LIKE '" + request.getParameter("SelectedSemester") + "';");
            int k = 0;

            while (rs1.next()) {
                SemesterCourse[k] = rs1.getString("courseCode") + request.getParameter("section_id");
                ++k;
            }

            for (int i = 0; i < SemesterCourse.length; ++i) {
                if (SemesterCourse[i] == null) {
                    SemesterCourse[i] = "";
                }
            }
        %>

        <form autocomplete="off" name="myForm" action="GenerateRoutineForStudents" method="post">
            <label class="teal-text left" style="font-size: 15px; font-weight: bolder; margin-left: 1%;">Routine version</label>
                <div class="input-field col s12">
                    <select name ="SelectedRoutine">
                        <%while (rs.next()) {%>
                        <option value="<%=rs.getString("FileName")%>"> <%=rs.getString("FileName")%> </option>
                        <%}%>  
                    </select>
                    
                </div>
                <div class="input-field col s12">
                    <input type="text" class="validate" name="Course1" value='<%=SemesterCourse[0]%>'>
                </div>
                <div class="input-field col s12">
                    <input type="text" class="validate" name="Course2" value='<%=SemesterCourse[1]%>'>
                </div>
                <div class="input-field col s12">
                    <input type="text" class="validate" name="Course3" value='<%=SemesterCourse[2]%>'>
                </div>
                <div class="input-field col s12">
                    <input type="text" class="validate" name="Course4" value='<%=SemesterCourse[3]%>'>
                </div>
                <div class="input-field col s12">
                    <input type="text" class="validate" name="Course5" value='<%=SemesterCourse[4]%>'>
                </div>
                <center>
                 <button class="btn gradient-45deg-indigo-blue border-round white-text waves-effect waves-light" type="submit" name="submit">Submit
                     <i class="large material-icons right">keyboard_arrow_right</i></button>
                </center>
            
        </form>
        <%connection.close();
            SemesterCourse[0] = "";
            SemesterCourse[1] = "";
            SemesterCourse[2] = "";
            SemesterCourse[3] = "";
            SemesterCourse[4] = "";
        %>
  
    </body>
</html>

<script>
     document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems, options);
  });
</script>

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/jquery-3.2.1.min.js"></script>
<!--materialize js-->
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/materialize.min.js"></script>
<!--scrollbar-->
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/perfect-scrollbar.min.js"></script>

<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/plugins.js"></script>

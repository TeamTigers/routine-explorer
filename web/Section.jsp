<%-- Document : state Created on : 15 Feb, 2018, 11:15:40 AM Author : Zubayer--%>
<%@page import="java.sql.SQLException"%><%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%><%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html> <head> <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> <title>Routine Explorer</title> <meta name="viewport" content="width=device-width, initial-scale=1"> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <meta name="msapplication-tap-highlight" content="no"> <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css"> <link rel="stylesheet" href="./assets/css/navbar.css"> <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> </head> <body> <%! String latestCourseOffer=""; String semester_id=""; %> 
<% Connection connection1=null; String path1=this.getClass().getClassLoader().getResource("").getPath(); try{Class.forName("org.sqlite.JDBC"); String ConnectionURL="jdbc:sqlite:" + path1 + "com/FilesPack/" + "RoutineExample.sqlite"; connection1=DriverManager.getConnection(ConnectionURL);}catch (ClassNotFoundException | SQLException e){System.out.println(e.toString());}Statement st1=null; ResultSet rs1=null; st1=connection1.createStatement(); rs1=st1.executeQuery("select * from ExcelCourseOffer"); while (rs1.next()){latestCourseOffer=rs1.getString("FileNameCourse");}%> <select class="selectBox" name="section_id" id="section_id"> <option>-- select --</option> 
<% semester_id=request.getParameter("semester_id"); Connection connection=null; String path=this.getClass().getClassLoader().getResource("").getPath(); try{Class.forName("org.sqlite.JDBC"); String ConnectionURL="jdbc:sqlite:" + path + "com/FilesPack/" + "RoutineExample.sqlite"; connection=DriverManager.getConnection(ConnectionURL);}catch (ClassNotFoundException | SQLException e){out.println(e.toString());}Statement st=connection.createStatement(); ResultSet rs=st.executeQuery("SELECT DISTINCT Section FROM " + latestCourseOffer + " WHERE Semester LIKE '" + semester_id + "'"); while (rs.next()){%> <option value="<%=rs.getString("Section")%>"><%=rs.getString("Section")%></option> <%}%> </select> <label class="teal-text" style="font-size: 15px; font-weight: bolder; margin-left: 0%;">Select your section</label> 
<div id="success"></div></body></html>
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/materialize.min.js">
</script><script type="text/javascript" src="https://himel007.github.io/projectCSS/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="https://himel007.github.io/projectCSS/js/plugins.js"></script>
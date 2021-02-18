package com.FilesPack;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class publishRoutine extends HttpServlet {

    static Connection connection = null;
    static Statement st;
    static ResultSet rs;
    static String latestCourseOfferName = "";
    static String RoutineVersion;
    static PrintWriter out;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        out = response.getWriter();

        

        
       RoutineVersion = request.getParameter("SelectedRoutine");
       String path = this.getClass().getClassLoader().getResource("").getPath();
        try {
            Class.forName("org.sqlite.JDBC");
            String ConnectionURL = "jdbc:sqlite:" + path +"com/FilesPack/"+ "RoutineExample.sqlite";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException | SQLException e) {
            out.println(e.toString());
        }
        
        PreparedStatement pst;
        pst = connection.prepareStatement("UPDATE ExcelFileName SET Status = 'Published' WHERE FileName LIKE '"+RoutineVersion+"'");
        pst.execute();
        
        response.sendRedirect("azqQPo1copiedTINlk23admindashboard.jsp");
        
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Bug_Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Bug_Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

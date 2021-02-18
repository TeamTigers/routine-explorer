package com.FilesPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginValidateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>This is a webpage</title>\n"
                + "    <style type=\"text/css\">\n"
                + "        table\n"
                + "        {\n"
                + "            border: 1px black;\n"
                + "			border-spacing: 0px 0;\n"
                + "			width: 50%;\n"
                + "            margin-left: auto;\n"
                + "            margin-right: auto;\n"
                + "        }\n"
                + "body\n"
                + "        {\n"
                + "width: 100%;  \n"
                + "                min-height: 100vh;\n"
                + "\n"
                + "                background: #9053c7;\n"
                + "                background: -webkit-linear-gradient(-135deg, #4158d0, #00897B);\n"
                + "                background: -o-linear-gradient(-135deg, #4158d0, #00897B);\n"
                + "                background: -moz-linear-gradient(-135deg, #4158d0, #00897B);\n"
                + "                background: linear-gradient(-135deg, #4158d0, #00897B);"
                + "        }\n"
                + "        h1\n"
                + "        {\n"
                + "            text-align: center;\n"
                + "            font-style: plain;\n"
                + "            color: white;\n"
                + "            background-color: #1976D2;\n"
                + "        }"
                + "        h2\n"
                + "        {\n"
                + "            text-align: center;\n"
                + "            font-style: Bold;\n"
                + "            color: white;\n"
                + "            \n"
                + "        }"
                + "        tr:nth-child(odd)\n"
                + "        {\n"
                + "		    border: 0;\n"
                + "			border-spacing: 10px 10;\n"
                + "            background-color: #80DEEA;\n"
                + "        }\n"
                + "        \n"
                + "		tr:nth-child(even)\n"
                + "        {\n"
                + "		    border: 0;\n"
                + "			border-spacing: 10px 10;\n"
                + "            background-color: white;\n"
                + "        }\n"
                + "		\n"
                + "		th\n"
                + "		{\n"
                + "		   background-color : #000000\n"
                + "		   \n"
                + "		}\n"
                + "		\n"
                + "		.borderless th\n"
                + "		 {\n"
                + "         border: none;\n"
                + "         }\n"
                + "		 \n"
                + "		 td\n"
                + "		 {\n"
                + "		 \n"
                + "		 }\n"
                + "		 \n"
                + "		 \n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>");

        try {
            GetConnectionClass obj = new GetConnectionClass();
            Connection con = obj.getConnectiontoSqlite();
            String loginSqlQuery = "SELECT *from AdminLogin WHERE AdminEmail=? AND AdminPassword=?";

            PreparedStatement pst = con.prepareStatement(loginSqlQuery);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rst = pst.executeQuery();

            if (rst.next()) {
                response.sendRedirect("azqQPo1Ad3min00Log12WTcin.jsp");
                
            } else {
                response.sendRedirect("daffodil123CopIEdswe321login.jsp");
            }
            con.close();
        } catch (UnsupportedEncodingException e) {
            out.println(e.toString());
        }

        out.println(" </body>\n"
                + "</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginValidateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginValidateServlet.class.getName()).log(Level.SEVERE, null, ex);
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

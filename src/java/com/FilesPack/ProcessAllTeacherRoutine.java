package com.FilesPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 *
 * @author Showrov
 */
public class ProcessAllTeacherRoutine extends HttpServlet {

    public static ArrayList<String> tRoutine = new ArrayList<>();
    public static ArrayList<String> teacherList = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        tRoutine.clear();
        teacherList.clear();
        
        
        
        
        Statement st0;
        ResultSet rs0;
        String RoutineVersion = request.getParameter("SelectedRoutine");

        Connection connection = null;
        String path = this.getClass().getClassLoader().getResource("").getPath();
        try {
            Class.forName("org.sqlite.JDBC");
            String ConnectionURL = "jdbc:sqlite:" + path + "com/FilesPack/" + "RoutineExample.sqlite";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }

        try {
            st0 = connection.createStatement();

            rs0 = st0.executeQuery("SELECT DISTINCT TeacherIni FROM " + RoutineVersion);
            String teacher = "";

            while (rs0.next()) {

                if (rs0.getString("TeacherIni").equals("Null") || rs0.getString("TeacherIni").equals(" ") || rs0.getString("TeacherIni").equals("  ") || rs0.getString("TeacherIni").equals("   ")) {
                    // do nothing    
                } else {
                    teacherList.add(rs0.getString("TeacherIni"));
                }
            }
        } catch (Exception e) {
            out.println(e.toString());
        }

        String[] timeArray = {"8:30-10:00", "10:00-11:30", "11:30-1:00", "1:00-2:30", "2:30-4:00", "4:00-5:30"};
        String[] dayNameString = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};

        Statement st;
        ResultSet rs;
        String[] VirtualCourseCode = new String[36];
        int k = 0;

        for (int i = 0; i < teacherList.size(); i++) {
            try {
                st = connection.createStatement();
                String SQLQuery
                        = "SELECT * FROM " + RoutineVersion + " Where"
                        + " TeacherIni LIKE '"+teacherList.get(i)+"'";

                rs = st.executeQuery(SQLQuery);

                while (rs.next()) {

                    //Saturday
                    if (rs.getString("DayName").equals(dayNameString[0])) {
                        if (rs.getString("TimeHour").equals(timeArray[0])) {
                            if (VirtualCourseCode[0] == null) {
                                VirtualCourseCode[0] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[0] = VirtualCourseCode[0] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[0])) {
                        if (rs.getString("TimeHour").equals(timeArray[1])) {
                            if (VirtualCourseCode[1] == null) {
                                VirtualCourseCode[1] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[1] = VirtualCourseCode[1] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[0])) {
                        if (rs.getString("TimeHour").equals(timeArray[2])) {
                            if (VirtualCourseCode[2] == null) {
                                VirtualCourseCode[2] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[2] = VirtualCourseCode[2] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[0])) {
                        if (rs.getString("TimeHour").equals(timeArray[3])) {
                            if (VirtualCourseCode[3] == null) {
                                VirtualCourseCode[3] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[3] = VirtualCourseCode[3] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[0])) {
                        if (rs.getString("TimeHour").equals(timeArray[4])) {
                            if (VirtualCourseCode[4] == null) {
                                VirtualCourseCode[4] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[4] = VirtualCourseCode[4] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[0])) {
                        if (rs.getString("TimeHour").equals(timeArray[5])) {
                            if (VirtualCourseCode[5] == null) {
                                VirtualCourseCode[5] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[5] = VirtualCourseCode[5] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    //Sunday
                    if (rs.getString("DayName").equals(dayNameString[1])) {
                        if (rs.getString("TimeHour").equals(timeArray[0])) {
                            if (VirtualCourseCode[6] == null) {
                                VirtualCourseCode[6] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[6] = VirtualCourseCode[6] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[1])) {
                        if (rs.getString("TimeHour").equals(timeArray[1])) {
                            if (VirtualCourseCode[7] == null) {
                                VirtualCourseCode[7] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[7] = VirtualCourseCode[7] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[1])) {
                        if (rs.getString("TimeHour").equals(timeArray[2])) {
                            if (VirtualCourseCode[8] == null) {
                                VirtualCourseCode[8] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[8] = VirtualCourseCode[8] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[1])) {
                        if (rs.getString("TimeHour").equals(timeArray[3])) {
                            if (VirtualCourseCode[9] == null) {
                                VirtualCourseCode[9] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[9] = VirtualCourseCode[9] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[1])) {
                        if (rs.getString("TimeHour").equals(timeArray[4])) {
                            if (VirtualCourseCode[10] == null) {
                                VirtualCourseCode[10] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[10] = VirtualCourseCode[10] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[1])) {
                        if (rs.getString("TimeHour").equals(timeArray[5])) {
                            if (VirtualCourseCode[11] == null) {
                                VirtualCourseCode[11] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[11] = VirtualCourseCode[11] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    //Monday
                    if (rs.getString("DayName").equals(dayNameString[2])) {
                        if (rs.getString("TimeHour").equals(timeArray[0])) {
                            if (VirtualCourseCode[12] == null) {
                                VirtualCourseCode[12] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[12] = VirtualCourseCode[12] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[2])) {
                        if (rs.getString("TimeHour").equals(timeArray[1])) {
                            if (VirtualCourseCode[13] == null) {
                                VirtualCourseCode[13] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[13] = VirtualCourseCode[13] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[2])) {
                        if (rs.getString("TimeHour").equals(timeArray[2])) {
                            if (VirtualCourseCode[14] == null) {
                                VirtualCourseCode[14] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[14] = VirtualCourseCode[14] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[2])) {
                        if (rs.getString("TimeHour").equals(timeArray[3])) {
                            if (VirtualCourseCode[15] == null) {
                                VirtualCourseCode[15] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[15] = VirtualCourseCode[15] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[2])) {
                        if (rs.getString("TimeHour").equals(timeArray[4])) {
                            if (VirtualCourseCode[16] == null) {
                                VirtualCourseCode[16] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[16] = VirtualCourseCode[16] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[2])) {
                        if (rs.getString("TimeHour").equals(timeArray[5])) {
                            if (VirtualCourseCode[17] == null) {
                                VirtualCourseCode[17] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[17] = VirtualCourseCode[17] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    //Tuesday
                    if (rs.getString("DayName").equals(dayNameString[3])) {
                        if (rs.getString("TimeHour").equals(timeArray[0])) {
                            if (VirtualCourseCode[18] == null) {
                                VirtualCourseCode[18] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[18] = VirtualCourseCode[18] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[3])) {
                        if (rs.getString("TimeHour").equals(timeArray[1])) {
                            if (VirtualCourseCode[19] == null) {
                                VirtualCourseCode[19] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[19] = VirtualCourseCode[19] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[3])) {
                        if (rs.getString("TimeHour").equals(timeArray[2])) {
                            if (VirtualCourseCode[20] == null) {
                                VirtualCourseCode[20] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[20] = VirtualCourseCode[20] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[3])) {
                        if (rs.getString("TimeHour").equals(timeArray[3])) {
                            if (VirtualCourseCode[21] == null) {
                                VirtualCourseCode[21] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[21] = VirtualCourseCode[21] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[3])) {
                        if (rs.getString("TimeHour").equals(timeArray[4])) {
                            if (VirtualCourseCode[22] == null) {
                                VirtualCourseCode[22] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[22] = VirtualCourseCode[22] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[3])) {
                        if (rs.getString("TimeHour").equals(timeArray[5])) {
                            if (VirtualCourseCode[23] == null) {
                                VirtualCourseCode[23] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[23] = VirtualCourseCode[23] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    //Wednesday
                    if (rs.getString("DayName").equals(dayNameString[4])) {
                        if (rs.getString("TimeHour").equals(timeArray[0])) {
                            if (VirtualCourseCode[24] == null) {
                                VirtualCourseCode[24] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[24] = VirtualCourseCode[24] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[4])) {
                        if (rs.getString("TimeHour").equals(timeArray[1])) {
                            if (VirtualCourseCode[25] == null) {
                                VirtualCourseCode[25] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[25] = VirtualCourseCode[25] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[4])) {
                        if (rs.getString("TimeHour").equals(timeArray[2])) {
                            if (VirtualCourseCode[26] == null) {
                                VirtualCourseCode[26] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[26] = VirtualCourseCode[26] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[4])) {
                        if (rs.getString("TimeHour").equals(timeArray[3])) {
                            if (VirtualCourseCode[27] == null) {
                                VirtualCourseCode[27] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[27] = VirtualCourseCode[27] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[4])) {
                        if (rs.getString("TimeHour").equals(timeArray[4])) {
                            if (VirtualCourseCode[28] == null) {
                                VirtualCourseCode[28] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[28] = VirtualCourseCode[28] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[4])) {
                        if (rs.getString("TimeHour").equals(timeArray[5])) {
                            if (VirtualCourseCode[29] == null) {
                                VirtualCourseCode[29] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[29] = VirtualCourseCode[29] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    //Thursday
                    if (rs.getString("DayName").equals(dayNameString[5])) {
                        if (rs.getString("TimeHour").equals(timeArray[0])) {
                            if (VirtualCourseCode[30] == null) {
                                VirtualCourseCode[30] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[30] = VirtualCourseCode[30] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[5])) {
                        if (rs.getString("TimeHour").equals(timeArray[1])) {
                            if (VirtualCourseCode[31] == null) {
                                VirtualCourseCode[31] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[31] = VirtualCourseCode[31] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[5])) {
                        if (rs.getString("TimeHour").equals(timeArray[2])) {
                            if (VirtualCourseCode[32] == null) {
                                VirtualCourseCode[32] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[32] = VirtualCourseCode[32] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[5])) {
                        if (rs.getString("TimeHour").equals(timeArray[3])) {
                            if (VirtualCourseCode[33] == null) {
                                VirtualCourseCode[33] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[33] = VirtualCourseCode[33] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[5])) {
                        if (rs.getString("TimeHour").equals(timeArray[4])) {
                            if (VirtualCourseCode[34] == null) {
                                VirtualCourseCode[34] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[34] = VirtualCourseCode[34] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }

                    if (rs.getString("DayName").equals(dayNameString[5])) {
                        if (rs.getString("TimeHour").equals(timeArray[5])) {
                            if (VirtualCourseCode[35] == null) {
                                VirtualCourseCode[35] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            } else {
                                VirtualCourseCode[35] = VirtualCourseCode[35] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                            }
                        }
                    }
                }

                for (int x = 0; x < VirtualCourseCode.length; ++x) {
                    if (VirtualCourseCode[x] == null) {
                        VirtualCourseCode[x] = "-";
                    }
                    tRoutine.add(VirtualCourseCode[x]);
                }
                
                st.close();
                rs.close();
                
                
            } catch (SQLException e) {
                out.println(e.toString());
            }
            
            for (int h=0; h<VirtualCourseCode.length ; h++){
                VirtualCourseCode[h] = "";
            }
        }

        request.setAttribute("tRoutine", tRoutine);
        request.setAttribute("RoutineVersion", RoutineVersion);
        request.setAttribute("teacherList", teacherList);
        request.getRequestDispatcher("showAllTeacherRoutine.jsp").forward(request, response);
        //out.println("\"<a href=\"index.jsp\"><p align=\"center\" style=\"color:white;\">Back To Home</p></a>\"");
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
            Logger.getLogger(ProcessAllTeacherRoutine.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProcessAllTeacherRoutine.class.getName()).log(Level.SEVERE, null, ex);
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

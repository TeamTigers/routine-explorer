/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FilesPack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author User
 */
public class UploadCourseOfferServlet extends HttpServlet {

    public String fileName = "";
    public Connection connection = null;
    public PreparedStatement pst;

    public void getConnectiontoSqlite() throws UnsupportedEncodingException {
        try {
            Class.forName("org.sqlite.JDBC");
            String ConnectionURL = "jdbc:sqlite:" + getPath() + "com/FilesPack/" + "RoutineExample.sqlite";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
    }

    public String getPath() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        return path;
    }

    public void setFileNameInTable(PrintWriter out) {
        try {
            String SQLQuery = "INSERT into ExcelCourseOffer (FileNameCourse) values(?)";
            pst = connection.prepareStatement(SQLQuery);
            pst.setString(1, fileName);
            pst.executeUpdate();

        } catch (SQLException e) {

            out.print(e.toString());
        }
    }

    public void createTableInDatabase(PrintWriter out) {
        try {
            String SQLQuery = "CREATE TABLE " + fileName + "\n"
                    + "(\n"
                    + "Semester VARCHAR(15) NOT NULL,\n"
                    + "courseCode VARCHAR(15) NOT NULL,\n"
                    + "Credit VARCHAR(5) NOT NULL,\n"
                    + "Teacher VARCHAR(8) NOT NULL,\n"
                    + "Section VARCHAR(5) NOT NULL\n"
                    + ");";
            Statement st = connection.createStatement();
            st.executeUpdate(SQLQuery);

        } catch (SQLException e) {
            out.println(e.toString());
        }
    }

    public void insertValuesInATable(String Semester, String courseCode, String Credit, String Teacher, String Section) {
        try {
            String SQLQuery = "INSERT into " + fileName + " (Semester,courseCode,Credit,Teacher,Section) values(?,?,?,?,?)";
            pst = connection.prepareStatement(SQLQuery);
            pst.setString(1, Semester);
            pst.setString(2, courseCode);
            pst.setString(3, Credit);
            pst.setString(4, Teacher);
            pst.setString(5, Section);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            List<org.apache.commons.fileupload.FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (org.apache.commons.fileupload.FileItem item : items) {
                if (item.isFormField()) {

                } else {

                    fileName = FilenameUtils.getName(item.getName());
                    fileName = FilenameUtils.removeExtension(fileName);

                    InputStream fileContent = item.getInputStream();
                    //out.println("<h1>" + fileName + "</h1>" + "<br>");

                    getConnectiontoSqlite();

                    ResultSet rs2;

                    Statement st2 = connection.createStatement();
                    rs2 = st2.executeQuery("SELECT FileNameCourse FROM ExcelCourseOffer ");
                    int yy = -1;
                    
                    while (rs2.next()) {
                        if (rs2.getString("FileNameCourse").equals(fileName)) {
                            yy = 0;
                            break;
                        }
                    }
                    
                    if (yy==0 || fileName.contains("-") || fileName.contains(":") || fileName.contains(";") || fileName.startsWith("1") || fileName.startsWith("0")) {
                        response.sendRedirect("copiedUploadCourseOffer.jsp");
                    } else {
                        setFileNameInTable(out);
                        createTableInDatabase(out);
                        XSSFWorkbook wb = new XSSFWorkbook(fileContent);
                        XSSFSheet sheet0 = wb.getSheetAt(0);
                        reader(sheet0, out);
                        response.sendRedirect("azqQPo1copiedTINlk23admindashboard.jsp");
                    }
                }
            }
        } catch (IOException | FileUploadException e) {
            out.println(e.toString());
        }
        connection.close();
    }

    public void reader(XSSFSheet sheet0, PrintWriter out) throws FileNotFoundException, IOException {

        Iterator RowIteratorA = sheet0.rowIterator();
        XSSFRow RowA;
        Iterator CellIteratoA;
        XSSFCell CellA;

        int[] SemesterNameRowPositionTaker = new int[12];

        int arrayIndex = 0;
        int numberOfRows = 0;
        //Taking Position Of Course Offer
        while (RowIteratorA.hasNext()) {
            RowA = (XSSFRow) RowIteratorA.next();
            CellIteratoA = RowA.cellIterator();
            while (CellIteratoA.hasNext()) {
                CellA = (XSSFCell) CellIteratoA.next();
                if (CellA.toString().equals("1_1") || CellA.toString().equals("1_2") || CellA.toString().equals("1_3")
                        || CellA.toString().equals("2_1") || CellA.toString().equals("2_2") || CellA.toString().equals("2_3")
                        || CellA.toString().equals("3_1") || CellA.toString().equals("3_2") || CellA.toString().equals("3_3")
                        || CellA.toString().equals("4_1") || CellA.toString().equals("4_2")) {
                    SemesterNameRowPositionTaker[arrayIndex] = CellA.getRowIndex();
                    arrayIndex++;
                }
            }
            numberOfRows++;
        }
        SemesterNameRowPositionTaker[11] = numberOfRows - 1;

        //Reading Subjects
        int start = SemesterNameRowPositionTaker[0];
        int end = SemesterNameRowPositionTaker[1];
        int SemesterNumber = 1;

        int currentSemester = 0;
        String CourseString = "";
        String Credit = "";
        String CurrentSection = "";
        String sem = "Semester ";
        String[] CurrentTeacher = new String[6];

        RowIteratorA = sheet0.rowIterator();
        while (RowIteratorA.hasNext()) {
            RowA = (XSSFRow) RowIteratorA.next();
            CellIteratoA = RowA.cellIterator();
            while (CellIteratoA.hasNext()) {
                CellA = (XSSFCell) CellIteratoA.next();
                if (CellA.getRowIndex() > 0 && start <= end) {
                    //Section A
                    if (CellA.getColumnIndex() == 6) {
                        CurrentTeacher[0] = CellA.toString();
                        CurrentSection = "A";
                        if ("".equals(CurrentTeacher[0])) {

                        } else {
                            insertValuesInATable(sem, CourseString, Credit, CurrentTeacher[0], CurrentSection);

                        }
                    }

                    //Section B
                    if (CellA.getColumnIndex() == 7) {
                        CurrentTeacher[1] = CellA.toString();
                        CurrentSection = "B";
                        if ("".equals(CurrentTeacher[1])) {

                        } else {
                            insertValuesInATable(sem, CourseString, Credit, CurrentTeacher[1], CurrentSection);
                        }

                    }

                    //Section C
                    if (CellA.getColumnIndex() == 8) {
                        CurrentTeacher[2] = CellA.toString();
                        CurrentSection = "C";
                        if ("".equals(CurrentTeacher[2])) {

                        } else {
                            insertValuesInATable(sem, CourseString, Credit, CurrentTeacher[2], CurrentSection);
                        }

                    }

                    //Section D
                    if (CellA.getColumnIndex() == 9) {
                        CurrentTeacher[3] = CellA.toString();
                        CurrentSection = "D";
                        if ("".equals(CurrentTeacher[3])) {

                        } else {
                            insertValuesInATable(sem, CourseString, Credit, CurrentTeacher[3], CurrentSection);
                        }

                    }

                    //Section E
                    if (CellA.getColumnIndex() == 10) {
                        CurrentTeacher[4] = CellA.toString();
                        CurrentSection = "E";
                        if ("".equals(CurrentTeacher[4])) {

                        } else {
                            insertValuesInATable(sem, CourseString, Credit, CurrentTeacher[4], CurrentSection);
                        }

                    }

                    //Section F
                    if (CellA.getColumnIndex() == 11) {
                        CurrentTeacher[5] = CellA.toString();
                        CurrentSection = "F";
                        if ("".equals(CurrentTeacher[5])) {

                        } else {
                            insertValuesInATable(sem, CourseString, Credit, CurrentTeacher[5], CurrentSection);
                        }
                        start++;
                    }

                    //Reading Course & Semester
                    if (CellA.getColumnIndex() == 4) {
                        CourseString = CellA.toString();
                        currentSemester = SemesterNumber;
                        sem = "Semester ";
                        sem += currentSemester;

                    }

                    //Reading Credit of The Course
                    if (CellA.getColumnIndex() == 3) {
                        Credit = CellA.toString();
                    }

                    //increasing Semester Number
                    if (start == end && ((SemesterNumber + 1) < SemesterNameRowPositionTaker.length)) {
                        end = SemesterNameRowPositionTaker[SemesterNumber + 1];
                        SemesterNumber++;
                    }
                }
            }
        }
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
            Logger.getLogger(UploadCourseOfferServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UploadCourseOfferServlet.class.getName()).log(Level.SEVERE, null, ex);
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

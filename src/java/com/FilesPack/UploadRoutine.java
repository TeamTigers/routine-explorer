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
 * @author Showrov
 */
public class UploadRoutine extends HttpServlet {

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
            String SQLQuery = "INSERT into ExcelFileName (FileName, Status) values(?, ?)";
            pst = connection.prepareStatement(SQLQuery);
            pst.setString(1, fileName);
            pst.setString(2, "Unpublished");
            pst.executeUpdate();

        } catch (SQLException e) {
            out.println(e.toString());
        }
    }

    public void createTableInDatabase(PrintWriter out) {
        try {
            String SQLQuery = "CREATE TABLE " + fileName + "\n"
                    + "(\n"
                    + "RoomNo VARCHAR(15) NOT NULL,\n"
                    + "Course VARCHAR(25) NOT NULL,\n"
                    + "TeacherIni VARCHAR(10) NOT NULL,\n"
                    + "DayName VARCHAR(25) NOT NULL,\n"
                    + "TimeHour VARCHAR(25) NOT NULL\n"
                    + ");";
            Statement st = connection.createStatement();
            st.executeUpdate(SQLQuery);

        } catch (SQLException e) {
            out.println(e.toString());
        }
    }

    public void insertValuesInATable(String RoomNumber, String Course, String TeacherInitial, String DayName, String Time) {
        try {
            String SQLQuery = "INSERT into " + fileName + " (RoomNo,Course,TeacherIni,DayName,TimeHour) values(?,?,?,?,?)";
            pst = connection.prepareStatement(SQLQuery);
            pst.setString(1, RoomNumber);
            pst.setString(2, Course);
            pst.setString(3, TeacherInitial);
            pst.setString(4, DayName);
            pst.setString(5, Time);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException, Exception {

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
                    out.println("<h1>" + fileName + "</h1>" + "<br>");

                    getConnectiontoSqlite();
                    ResultSet rs2;

                    Statement st2 = connection.createStatement();
                    rs2 = st2.executeQuery("SELECT FileName FROM ExcelFileName ");
                    int yy = -1;

                    while (rs2.next()) {
                        if (rs2.getString("FileName").equals(fileName)) {
                            yy = 0;
                            break;
                        }
                    }

                    if (yy == 0 || fileName.contains("-") || fileName.contains(":") || fileName.contains(";") || fileName.startsWith("1") || fileName.startsWith("0")) {
                        response.sendRedirect("copiedUploadRoutine.jsp");
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
        Iterator DynamicRowIterator = sheet0.rowIterator();
        XSSFRow DynamicRow;
        Iterator DynamicCellIterator;

        Iterator RowIteratorA = sheet0.rowIterator();
        XSSFRow RowA;
        Iterator CellIteratoA;
        XSSFCell CellA;

        int[] DayNamePositionTaker = new int[7];
        String[] timeArray = {"8:30-10:00", "10:00-11:30", "11:30-1:00", "1:00-2:30", "2:30-4:00", "4:00-5:30"};
        String[] dayNameString = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "List of"};

        int arrayIndex = 0;

        //Taking Position Of All Days In An Array
        while (RowIteratorA.hasNext()) {
            RowA = (XSSFRow) RowIteratorA.next();
            CellIteratoA = RowA.cellIterator();

            while (CellIteratoA.hasNext()) {
                CellA = (XSSFCell) CellIteratoA.next();
                if (CellA.toString().contains(dayNameString[0])
                        || CellA.toString().contains(dayNameString[1])
                        || CellA.toString().contains(dayNameString[2])
                        || CellA.toString().contains(dayNameString[3])
                        || CellA.toString().contains(dayNameString[4])
                        || CellA.toString().contains(dayNameString[5])
                        || CellA.toString().contains(dayNameString[6])) {
                    DayNamePositionTaker[arrayIndex] = CellA.getRowIndex();
                    arrayIndex++;
                }
            }
        }

        /*
        ** Increasing Saturday Node Point Index (+2)
        ** So that it can Skip
        ** Two Numerical Indexed Rows To Read Attribute Data Successfully
         */
        DayNamePositionTaker[0] = DayNamePositionTaker[0] + 2;

        int dayNameIndex = 0;
        String RoomNumber = "", Course = "", TeacherInitial = "", DayName = "", Time = "";

        /*
        ** This for loop works as previously
        ** Taken Day Node Point
        ** And Work Till Previous Node Point Of The Last One
         */
        for (int day = 0; day < DayNamePositionTaker.length - 1; ++day) {
            int TimeIndex = 0;
            /*
            ** This for loop works to make program understand
            ** How many Rows program will read
            ** For Current Node Point Day
             */
            for (int i = DayNamePositionTaker[day] + 1; i <= DayNamePositionTaker[day + 1] - 1; i++) {
                DynamicRow = sheet0.getRow(i);
                DynamicCellIterator = DynamicRow.cellIterator();

                /*
                ** This While Loop Iterate over the current Row.
                ** Then Converted Cell Iterator Works 
                ** for Each Cell , Check the Value & work with It
                 */
                while (DynamicCellIterator.hasNext()) {
                    XSSFCell cell = (XSSFCell) DynamicCellIterator.next();

                    /*
                    ** This describes that the program should read an attribute as Room When 
                    ** Cell Index Like A Static Value
                    ** Index value, x :=> ((x+3)%3)=0 ==> true
                    **
                    ** x = 0,3,6,.....................................,n
                    **
                    ** Such that n is divisible by 3
                     */
                    if ((cell.getColumnIndex() + 3) % 3 == 0) {
                        RoomNumber = cell.toString();

                    }

                    /*
                    ** This describes that the program should read an attribute as Room When 
                    ** Cell Index Like A Static Value
                    ** Index value, x :=> ((x+3)%3)=0 ==> true
                    **
                    ** x = 0,1,4,.....................................,n
                    **
                    ** Such that n is divisible by 3
                     */
                    if ((cell.getColumnIndex() + 2) % 3 == 0) {
                        if (cell.toString().isEmpty()) {
                            Course = "Null";
                        } else {
                            Course = cell.toString();
                        }
                    }

                    /*
                    ** This describes that the program should read an attribute as Room When 
                    ** Cell Index Like A Static Value
                    ** Index value, x :=> ((x+3)%3)=0 ==> true
                    **
                    ** x = 0,2,5,.....................................,n
                    **
                    ** Such that n is divisible by 3
                     */
                    if ((cell.getColumnIndex() + 1) % 3 == 0) {
                        if (cell.toString().isEmpty()) {
                            TeacherInitial = "Null";
                        } else {
                            TeacherInitial = cell.toString();
                        }
                        DayName = dayNameString[dayNameIndex];
                        Time = timeArray[TimeIndex];
                        ++TimeIndex;

                        if ("Null".equals(Course) && "Null".equals(TeacherInitial)) {
                        } else {
                            insertValuesInATable(RoomNumber, Course, TeacherInitial, DayName, Time);
                        }

                    }

                }
                TimeIndex = 0;

            }
            dayNameIndex++;
        }

    }

    private static String textTrimmer(String x) {
        String y = "";
        for (int i = 0; i < x.length(); ++i) {
            if (x.charAt(i) != ' ') {
                y += x.charAt(i);
            }
        }
        return y;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadRoutine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadRoutine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package com.FilesPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bug_Teacher extends HttpServlet {

    static String RoutineVersion;
    static Connection connection = null;
    static Statement st;
    static ResultSet rs;
    static String latestCourseOfferName = "";
    static PrintWriter out;

    // jsp passing variables for continuous class
    public static ArrayList<String> tNameCC = new ArrayList<>();
    public static ArrayList<String> dayCC = new ArrayList<>();
    
    // jsp passing variables for double class
    public static ArrayList<String> tNameDC = new  ArrayList<>();
    public static ArrayList<String> timeDC = new  ArrayList<>();
    public static ArrayList<String> dayDC = new  ArrayList<>();
    public static int Done = 0;
    
    // jsp passing variables for whole day  class
    public static ArrayList <String> tNameWC = new ArrayList<>();
    public static ArrayList <String> dayWC = new ArrayList<>();
     
    // jsp passing variable for both campus  classes
    public  static ArrayList<String> tNameBC = new ArrayList<>();
    public  static ArrayList<String> dayBC = new ArrayList<>();
    
    
    
    
    static ArrayList<String> doubleClassForATeacher = new ArrayList<>();

    /**
     * making virtualCourseCode array list to get the course code
     */
    static String[] VirtualCourseCode = new String[36];

    /**
     * an array list where all course code of a teacher will be stored
     */
    static String[] copyingTeacherCourseList = new String[36];

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        //Servlet CSS

        RoutineVersion = request.getParameter("SelectedRoutine");

        try {
            String path = this.getClass().getClassLoader().getResource("").getPath();
            Class.forName("org.sqlite.JDBC");
            String ConnectionURL = "jdbc:sqlite:" + path + "com/FilesPack/" + "RoutineExample.sqlite";
            connection = DriverManager.getConnection(ConnectionURL);

        } catch (ClassNotFoundException | SQLException e) {
            out.println(e.toString());
        }

        //Assign Latest Course Offer
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM ExcelCourseOffer");

            while (rs.next()) {
                latestCourseOfferName = rs.getString("FileNameCourse");
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
        }

        //copy
        try {
            st = connection.createStatement();
            ArrayList<String> teacherList = new ArrayList<>();
            rs = st.executeQuery("SELECT DISTINCT TeacherIni FROM " + RoutineVersion);
            String teacher = "";

            while (rs.next()) {

                if (rs.getString("TeacherIni").equals("Null") || rs.getString("TeacherIni").equals(" ") || rs.getString("TeacherIni").equals("  ") || rs.getString("TeacherIni").equals("   ")) {
                    // do nothing    
                } else {
                    teacherList.add(rs.getString("TeacherIni"));
                }
            }
            
            // ram clear for request variables.
            tNameCC.clear();
            dayCC.clear();
            tNameDC.clear();
            dayDC.clear();
            timeDC.clear();
            tNameWC.clear();
            dayWC.clear();
            tNameBC.clear();
            dayBC.clear();
            
            
            Done = 0;
           // out.println("<h2>Continious Class For A Teacher</h2>");
            /**
             * loop for checking continuous classes for a teacher
             */

            for (int t = 0; t < teacherList.size(); t++) {
                teacher = teacherList.get(t);
                getArray(teacher);
                CopyTeacherList();

                checkClassesForATeacher(teacher);

                nullCourseCode();
            }
             //Making 1, to to Repeat getReportForDouble Method
            Done = 1;
            
       
            
//                    out.println("<h2>Double Class For A Teacher</h2><br>");
//                    for (int g = 0; g < doubleClassForATeacher.size(); g++) {
//                        out.println("<ul><li>" + doubleClassForATeacher.get(g) + "</ul></li>");
//                    }
//        
                    //  double class
                  
//                    out.println("<br>");
//        
//                    /**
//                     * loop for whole day class of a teacher
//                     */
                    out.println("<h2>Teachers having classes in whole Day</h2>");
                    for (int i = 0; i < teacherList.size(); i++) {
                        teacher = teacherList.get(i);
                        getArray(teacher);
                        CopyTeacherList();
                        wholeDayClass(teacher);
                        nullCourseCode();
                    }
//                    out.println("<br><br><br>");
//                    /**
//                     * loop for checking if a teacher has classes in both campus
//                     */
                    out.println("<h2>Teachers having classes in both campus </h2><br>");
                    for (int i = 0; i < teacherList.size(); i++) {
                        teacher = teacherList.get(i);
                        getArray(teacher);
                        CopyTeacherList();
                        checkBothCampusClass(teacher);
                        nullCourseCode();
                    }
        
//                    out.println("<br><br><br>");
//        
//                    /**
//                     * Class missing of a teacher
//                     */
//                    out.println("<h2>Class Missing </h2>");
//        
//                    ClassMissingForASection();
//        
        } catch (SQLException e) {

            out.println(e.toString());
        }
        
        // continuous class
        request.setAttribute("tNameCC", tNameCC);
        request.setAttribute("dayCC", dayCC);
        
             // double class
        request.setAttribute("tNameDC", tNameDC);
        request.setAttribute("dayDC", dayDC);
        request.setAttribute("timeDC", timeDC);
        
        // whole day class
        request.setAttribute("tNameWC", tNameWC);
        request.setAttribute("dayWC", dayWC);
        
        // both campus class
        request.setAttribute("tNameBC", tNameBC);
        request.setAttribute("dayBC", dayBC);
                
        
        request.getRequestDispatcher("showBugForTeacher.jsp").forward(request, response);
        // out.print(sublist);
    }

    /**
     * displaying teacher course code
     */
    private static void CopyTeacherList() {
        System.arraycopy(VirtualCourseCode, 0, copyingTeacherCourseList, 0, 36);
    }

    /*
    checing more than two classes for a teacher of all days
     */
    private static void checkClassesForATeacher(String teacher) {
        int k = 0;

        // s a t u r d a y
        for (int i = 0; i < 6; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                k = 0;
            } else {
                k++;
            }
            if (k > 2) {
                continuousClassReport(teacher, "Saturday");
                break;
            }
        }

        // s u n d a y
        k = 0;
        for (int i = 6; i < 12; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                k = 0;
            } else {
                k++;
            }
            if (k > 2) {
                continuousClassReport(teacher, "Sunday");
                break;
            }
        }

        // m o n d a y
        k = 0;
        for (int i = 12; i < 18; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                k = 0;
            } else {
                k++;
            }
            if (k > 2) {
                continuousClassReport(teacher, "Monday");
                break;
            }
        }

        // t u e s d a y
        k = 0;
        for (int i = 18; i < 24; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                k = 0;
            } else {
                k++;
            }
            if (k > 2) {
                continuousClassReport(teacher, "Tuesday");
                break;
            }
        }

        // w e d n e s d a y
        k = 0;
        for (int i = 24; i < 30; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                k = 0;
            } else {
                k++;
            }
            if (k > 2) {
                continuousClassReport(teacher, "Wednesday");
                break;
            }
        }

        // t h u r s d a y 
        k = 0;
        for (int i = 30; i < 36; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                k = 0;
            } else {
                k++;
            }
            if (k > 2) {
                continuousClassReport(teacher, "Thursday");
                break;
            }
        }
    }

    /**
     *
     * reporting teacher if he has both class at 8:30 am slot and 4:00 pm slot
     */
    
    
    
    private static void wholeDayClass(String teacher) {
                  
        if (copyingTeacherCourseList[0] != "" && copyingTeacherCourseList[5] != "") {
            tNameWC.add(teacher);
            dayWC.add("Saturday");
        }

        if (copyingTeacherCourseList[6] != "" && copyingTeacherCourseList[11] != "") {
            tNameWC.add(teacher);
            dayWC.add("Sunday");
        }

        if (copyingTeacherCourseList[12] != "" && copyingTeacherCourseList[17] != "") {
            tNameWC.add(teacher);
            dayWC.add("Monday");
        }

        if (copyingTeacherCourseList[18] != "" && copyingTeacherCourseList[23] != "") {
            tNameWC.add(teacher);
            dayWC.add("Tuesday");
        }

        if (copyingTeacherCourseList[24] != "" && copyingTeacherCourseList[29] != "") {
            tNameWC.add(teacher);
            dayWC.add("Wednesday");
        }

        if (copyingTeacherCourseList[30] != "" && copyingTeacherCourseList[35] != "") {
            tNameWC.add(teacher);
            dayWC.add("Thursday");
        }
    }

    /**
     * check if the string has both B and C
     *
     * @param perDayRoom
     * @return
     */
    private static boolean checkCampus(String perDayRoom) {
        return perDayRoom.contains("B") && (perDayRoom.contains("C"));
    }

    /**
     * generating report for teacher if the teacher has classes in both main
     * building or academic building and permanent campus
     *
     * @param teacher
     */
    private static void checkBothCampusClass(String teacher) {
        
      
        
        String perDayRoom = "";
        // s a t u r d a y
        for (int i = 0; i < 6; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                // do nothing
            } else {
                if (copyingTeacherCourseList[i].endsWith("AB<br><br>") || copyingTeacherCourseList[i].endsWith("AB <br><br>") || copyingTeacherCourseList[i].endsWith(" AB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("AB ") || copyingTeacherCourseList[i].endsWith(" AB") || copyingTeacherCourseList[i].endsWith("AB")
                        || copyingTeacherCourseList[i].endsWith("MB<br><br>") || copyingTeacherCourseList[i].endsWith("MB <br><br>") || copyingTeacherCourseList[i].endsWith(" MB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("MB ") || copyingTeacherCourseList[i].endsWith(" MB") || copyingTeacherCourseList[i].endsWith("MB")) {
                    perDayRoom += "B";
                }

                if (copyingTeacherCourseList[i].endsWith("PC<br><br>") || copyingTeacherCourseList[i].endsWith("PC <br><br>") || copyingTeacherCourseList[i].endsWith(" PC<br><br>")
                        || copyingTeacherCourseList[i].endsWith("PC ") || copyingTeacherCourseList[i].endsWith(" PC") || copyingTeacherCourseList[i].endsWith("PC")) {
                    perDayRoom += "C";
                }

            }
        }

        if (checkCampus(perDayRoom)) {
            tNameBC.add(teacher);
            dayBC.add("Saturday");
            
            
        }

        // s u n d a y
        perDayRoom = "";
        for (int i = 6; i < 12; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                // do nothing
            } else {
                if (copyingTeacherCourseList[i].endsWith("AB<br><br>") || copyingTeacherCourseList[i].endsWith("AB <br><br>") || copyingTeacherCourseList[i].endsWith(" AB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("AB ") || copyingTeacherCourseList[i].endsWith(" AB") || copyingTeacherCourseList[i].endsWith("AB")
                        || copyingTeacherCourseList[i].endsWith("MB<br><br>") || copyingTeacherCourseList[i].endsWith("MB <br><br>") || copyingTeacherCourseList[i].endsWith(" MB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("MB ") || copyingTeacherCourseList[i].endsWith(" MB") || copyingTeacherCourseList[i].endsWith("MB")) {
                    perDayRoom += "B";
                }

                if (copyingTeacherCourseList[i].endsWith("PC<br><br>") || copyingTeacherCourseList[i].endsWith("PC <br><br>") || copyingTeacherCourseList[i].endsWith(" PC<br><br>")
                        || copyingTeacherCourseList[i].endsWith("PC ") || copyingTeacherCourseList[i].endsWith(" PC") || copyingTeacherCourseList[i].endsWith("PC")) {
                    perDayRoom += "C";
                }
            }
        }

        if (checkCampus(perDayRoom)) {
            tNameBC.add(teacher);
            dayBC.add("Sunday");
        }

        // m o n d a y
        perDayRoom = "";
        for (int i = 12; i < 18; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                // do nothing
            } else {
                if (copyingTeacherCourseList[i].endsWith("AB<br><br>") || copyingTeacherCourseList[i].endsWith("AB <br><br>") || copyingTeacherCourseList[i].endsWith(" AB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("AB ") || copyingTeacherCourseList[i].endsWith(" AB") || copyingTeacherCourseList[i].endsWith("AB")
                        || copyingTeacherCourseList[i].endsWith("MB<br><br>") || copyingTeacherCourseList[i].endsWith("MB <br><br>") || copyingTeacherCourseList[i].endsWith(" MB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("MB ") || copyingTeacherCourseList[i].endsWith(" MB") || copyingTeacherCourseList[i].endsWith("MB")) {
                    perDayRoom += "B";
                }

                if (copyingTeacherCourseList[i].endsWith("PC<br><br>") || copyingTeacherCourseList[i].endsWith("PC <br><br>") || copyingTeacherCourseList[i].endsWith(" PC<br><br>")
                        || copyingTeacherCourseList[i].endsWith("PC ") || copyingTeacherCourseList[i].endsWith(" PC") || copyingTeacherCourseList[i].endsWith("PC")) {
                    perDayRoom += "C";
                }
            }
        }

        if (checkCampus(perDayRoom)) {
            tNameBC.add(teacher);
            dayBC.add("Monday");
        }

        // t u e s d a y
        perDayRoom = "";
        for (int i = 18; i < 24; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                // do nothing
            } else {
                if (copyingTeacherCourseList[i].endsWith("AB<br><br>") || copyingTeacherCourseList[i].endsWith("AB <br><br>") || copyingTeacherCourseList[i].endsWith(" AB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("AB ") || copyingTeacherCourseList[i].endsWith(" AB") || copyingTeacherCourseList[i].endsWith("AB")
                        || copyingTeacherCourseList[i].endsWith("MB<br><br>") || copyingTeacherCourseList[i].endsWith("MB <br><br>") || copyingTeacherCourseList[i].endsWith(" MB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("MB ") || copyingTeacherCourseList[i].endsWith(" MB") || copyingTeacherCourseList[i].endsWith("MB")) {
                    perDayRoom += "B";
                }

                if (copyingTeacherCourseList[i].endsWith("PC<br><br>") || copyingTeacherCourseList[i].endsWith("PC <br><br>") || copyingTeacherCourseList[i].endsWith(" PC<br><br>")
                        || copyingTeacherCourseList[i].endsWith("PC ") || copyingTeacherCourseList[i].endsWith(" PC") || copyingTeacherCourseList[i].endsWith("PC")) {
                    perDayRoom += "C";
                }
            }
        }

        if (checkCampus(perDayRoom)) {
            tNameBC.add(teacher);
            dayBC.add("Tuesday");
        }

        // w e d n e s d a y
        perDayRoom = "";
        for (int i = 24; i < 30; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                // do nothing
            } else {
                if (copyingTeacherCourseList[i].endsWith("AB<br><br>") || copyingTeacherCourseList[i].endsWith("AB <br><br>") || copyingTeacherCourseList[i].endsWith(" AB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("AB ") || copyingTeacherCourseList[i].endsWith(" AB") || copyingTeacherCourseList[i].endsWith("AB")
                        || copyingTeacherCourseList[i].endsWith("MB<br><br>") || copyingTeacherCourseList[i].endsWith("MB <br><br>") || copyingTeacherCourseList[i].endsWith(" MB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("MB ") || copyingTeacherCourseList[i].endsWith(" MB") || copyingTeacherCourseList[i].endsWith("MB")) {
                    perDayRoom += "B";
                }

                if (copyingTeacherCourseList[i].endsWith("PC<br><br>") || copyingTeacherCourseList[i].endsWith("PC <br><br>") || copyingTeacherCourseList[i].endsWith(" PC<br><br>")
                        || copyingTeacherCourseList[i].endsWith("PC ") || copyingTeacherCourseList[i].endsWith(" PC") || copyingTeacherCourseList[i].endsWith("PC")) {
                    perDayRoom += "C";
                }
            }
        }

        if (checkCampus(perDayRoom)) {
            tNameBC.add(teacher);
            dayBC.add("Wednesday");
        }

        // t h u r s d a y 
        perDayRoom = "";
        for (int i = 30; i < 36; i++) {
            if (copyingTeacherCourseList[i].equals("")) {
                // do nothing
            } else {
                if (copyingTeacherCourseList[i].endsWith("AB<br><br>") || copyingTeacherCourseList[i].endsWith("AB <br><br>") || copyingTeacherCourseList[i].endsWith(" AB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("AB ") || copyingTeacherCourseList[i].endsWith(" AB") || copyingTeacherCourseList[i].endsWith("AB")
                        || copyingTeacherCourseList[i].endsWith("MB<br><br>") || copyingTeacherCourseList[i].endsWith("MB <br><br>") || copyingTeacherCourseList[i].endsWith(" MB<br><br>")
                        || copyingTeacherCourseList[i].endsWith("MB ") || copyingTeacherCourseList[i].endsWith(" MB") || copyingTeacherCourseList[i].endsWith("MB")) {
                    perDayRoom += "B";
                }

                if (copyingTeacherCourseList[i].endsWith("PC<br><br>") || copyingTeacherCourseList[i].endsWith("PC <br><br>") || copyingTeacherCourseList[i].endsWith(" PC<br><br>")
                        || copyingTeacherCourseList[i].endsWith("PC ") || copyingTeacherCourseList[i].endsWith(" PC") || copyingTeacherCourseList[i].endsWith("PC")) {
                    perDayRoom += "C";
                }
            }
        }

        if (checkCampus(perDayRoom)) {
            tNameBC.add(teacher);
            dayBC.add("Thursday");
        }
    }

    /**
     * generating report for teacher who has continuously more than two classes
     *
     * @param teacher
     * @param day
     */
    private static void continuousClassReport(String teacher, String day) {

        tNameCC.add(teacher);
        dayCC.add(day);
    }

    /**
     * converting virtualCoursecode to null after getting 36 course code of a
     * teacher
     */
    private static void nullCourseCode() {
        for (int i = 0; i < 36; i++) {
            VirtualCourseCode[i] = "";
        }
    }

    /**
     * A method that makes counter zero. For each teacher classes counter
     * increases by one. When the loop runs each time this method makes the
     * counter to zero
     */
    private static void makeCounterzero(int countClass[]) {
        for (int i = 0; i < countClass.length; i++) {
            countClass[i] = 0;
        }
    }

    /**
     * generating report for a teacher who has double classes in a single slot
     *
     * @param teacherInitial
     * @param day
     * @param time
     */
    private static void getRreportOfDoubleClass(String teacherInitial, String day, String time) {
        
        tNameDC.add(teacherInitial);
        dayDC.add(day);
        timeDC.add(time);

    }

    private static void ClassMissingForASection() {
        Statement st1, st2;
        ResultSet rs1, rs2;
        try {
            st1 = connection.createStatement();
            rs1 = st1.executeQuery("SELECT * FROM " + latestCourseOfferName);

            while (rs1.next()) {
                double credit = rs1.getDouble("Credit");
                if (credit == 1.0) {
                    int count = 0;
                    String courseForCreditOne = rs1.getString("courseCode") + rs1.getString("Section") + "LAB1";
                    courseForCreditOne = trimmer(courseForCreditOne);

                    st2 = connection.createStatement();
                    rs2 = st2.executeQuery("SELECT * FROM " + RoutineVersion + " WHERE Course LIKE '" + courseForCreditOne + "' OR Course LIKE '" + courseForCreditOne + " '");

                    while (rs2.next()) {
                        count++;
                    }
                    if (count != 1) {
                        out.println("Class is missing for " + courseForCreditOne + "<br>");
                    }
                    st2.close();
                    rs2.close();
                }

                if (credit == 2.0) {
                    int count = 0;
                    String courseForCreditTwo = rs1.getString("courseCode") + rs1.getString("Section") + "LAB";
                    courseForCreditTwo = trimmer(courseForCreditTwo);

                    st2 = connection.createStatement();
                    rs2 = st2.executeQuery("SELECT * FROM " + RoutineVersion + " WHERE Course LIKE '" + courseForCreditTwo + "%' OR Course LIKE '" + courseForCreditTwo + " '");

                    while (rs2.next()) {
                        count++;
                    }
                    if (count != 2) {
                        out.println("Class is missing for " + courseForCreditTwo + "<br>");
                    }
                    st2.close();
                    rs2.close();
                }

                if (credit == 3.0) {
                    int count = 0;
                    String courseForCreditThree = rs1.getString("courseCode") + rs1.getString("Section");
                    courseForCreditThree = trimmer(courseForCreditThree);

                    st2 = connection.createStatement();
                    rs2 = st2.executeQuery("SELECT * FROM " + RoutineVersion + " WHERE Course LIKE '" + courseForCreditThree + "' OR Course LIKE '" + courseForCreditThree + " '");

                    while (rs2.next()) {
                        count++;
                    }
                    if (count != 2) {
                        out.println("Class is missing for " + courseForCreditThree + "<br>");
                    }
                    st2.close();
                    rs2.close();
                }
            }

        } catch (Exception e) {
            out.println(e.toString());
        }
    }

    /**
     * A Method That Trim String Such That All Blank Spaces Are Removed From A
     * Text & Return The Newly Trimmed Text
     */
    private static String trimmer(String xS) {
        String yS = "";
        for (int d = 0; d < xS.length(); ++d) {
            if (xS.charAt(d) != ' ') {
                yS += String.valueOf(xS.charAt(d));
            }
        }
        return yS;
    }

    /*
    A mehtod that get the teacher class of the week with time
     */
    private static void getArray(String teacher) {
        String[] timeArray = {"8:30-10:00", "10:00-11:30", "11:30-1:00", "1:00-2:30", "2:30-4:00", "4:00-5:30"};
        String[] dayNameString = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};

        int[] countClass = new int[36];

        /*
        Calling counter zero method here.
         */
        makeCounterzero(countClass);

        try {
            st = connection.createStatement();
            String SQLQuery = "SELECT * FROM " + RoutineVersion + " WHERE TeacherIni LIKE '" + teacher + "'";
            rs = st.executeQuery(SQLQuery);

            while (rs.next()) {

                //--------------------------                       S a t u r d a y                    ----------------------/
                if (rs.getString("DayName").equals(dayNameString[0])) {
                    if (rs.getString("TimeHour").equals(timeArray[0])) {
                        if (VirtualCourseCode[0] == null) {
                            VirtualCourseCode[0] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        } else {
                            VirtualCourseCode[0] = VirtualCourseCode[0] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        }

                        countClass[0]++;

                        /*
                        If a teacher has more than one class in a particular
                        single time then below code will execute.
                         */
                        if (countClass[0] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[0], timeArray[0]);
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
                        countClass[1]++;

                        if (countClass[1] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[0], timeArray[1]);
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

                        countClass[2]++;
                        if (countClass[2] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[0], timeArray[2]);
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
                        countClass[3]++;
                        if (countClass[3] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[0], timeArray[3]);
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
                        countClass[4]++;
                        if (countClass[4] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[0], timeArray[4]);
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
                        countClass[5]++;
                        if (countClass[5] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[0], timeArray[5]);
                        }
                    }
                }

                //-----------------                    S u n d a y              ---------------------/
                if (rs.getString("DayName").equals(dayNameString[1])) {
                    if (rs.getString("TimeHour").equals(timeArray[0])) {
                        if (VirtualCourseCode[6] == null) {
                            VirtualCourseCode[6] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        } else {
                            VirtualCourseCode[6] = VirtualCourseCode[6] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        }
                        countClass[6]++;
                        if (countClass[6] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[1], timeArray[0]);
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
                        countClass[7]++;
                        if (countClass[7] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[1], timeArray[1]);
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

                        countClass[8]++;
                        if (countClass[8] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[1], timeArray[2]);
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

                        countClass[9]++;
                        if (countClass[9] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[1], timeArray[3]);
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

                        countClass[10]++;
                        if (countClass[10] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[1], timeArray[4]);
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

                        countClass[11]++;
                        if (countClass[11] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[1], timeArray[5]);
                        }
                    }
                }

                //-----------------------                         M o n d a y                     -------------------/
                if (rs.getString("DayName").equals(dayNameString[2])) {
                    if (rs.getString("TimeHour").equals(timeArray[0])) {
                        if (VirtualCourseCode[12] == null) {
                            VirtualCourseCode[12] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        } else {
                            VirtualCourseCode[12] = VirtualCourseCode[12] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        }

                        countClass[12]++;
                        if (countClass[12] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[2], timeArray[0]);
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

                        countClass[13]++;
                        if (countClass[13] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[2], timeArray[1]);
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

                        countClass[14]++;
                        if (countClass[14] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[2], timeArray[2]);
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

                        countClass[15]++;
                        if (countClass[15] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[2], timeArray[3]);
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

                        countClass[16]++;
                        if (countClass[16] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[2], timeArray[4]);
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

                        countClass[17]++;
                        if (countClass[17] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[2], timeArray[5]);
                        }
                    }
                }

                //---------------------                   T u e s d a y               -------------------/
                if (rs.getString("DayName").equals(dayNameString[3])) {
                    if (rs.getString("TimeHour").equals(timeArray[0])) {
                        if (VirtualCourseCode[18] == null) {
                            VirtualCourseCode[18] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        } else {
                            VirtualCourseCode[18] = VirtualCourseCode[18] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        }

                        countClass[18]++;
                        if (countClass[18] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[3], timeArray[0]);
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

                        countClass[19]++;
                        if (countClass[19] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[3], timeArray[1]);
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

                        countClass[20]++;
                        if (countClass[20] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[3], timeArray[2]);
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

                        countClass[21]++;
                        if (countClass[21] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[3], timeArray[3]);
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

                        countClass[22]++;
                        if (countClass[22] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[3], timeArray[4]);
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

                        countClass[23]++;
                        if (countClass[23] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[3], timeArray[5]);
                        }
                    }
                }

                //----------------------            W e d n e s d a y          -----------------------/
                if (rs.getString("DayName").equals(dayNameString[4])) {
                    if (rs.getString("TimeHour").equals(timeArray[0])) {
                        if (VirtualCourseCode[24] == null) {
                            VirtualCourseCode[24] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        } else {
                            VirtualCourseCode[24] = VirtualCourseCode[24] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        }

                        countClass[24]++;
                        if (countClass[24] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[4], timeArray[0]);
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

                        countClass[25]++;
                        if (countClass[25] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[4], timeArray[1]);
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

                        countClass[26]++;
                        if (countClass[26] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[4], timeArray[2]);
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

                        countClass[27]++;
                        if (countClass[27] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[4], timeArray[3]);
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

                        countClass[28]++;
                        if (countClass[28] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[4], timeArray[4]);
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

                        countClass[29]++;
                        if (countClass[29] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[4], timeArray[5]);
                        }
                    }
                }

                //---------         T h u r s d a y        ---------/
                if (rs.getString("DayName").equals(dayNameString[5])) {
                    if (rs.getString("TimeHour").equals(timeArray[0])) {
                        if (VirtualCourseCode[30] == null) {
                            VirtualCourseCode[30] = rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        } else {
                            VirtualCourseCode[30] = VirtualCourseCode[30] + rs.getString("Course") + "<br>" + rs.getString("RoomNo") + "<br><br>";
                        }

                        countClass[30]++;
                        if (countClass[30] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[5], timeArray[0]);
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

                        countClass[31]++;
                        if (countClass[31] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[5], timeArray[1]);
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

                        countClass[32]++;
                        if (countClass[32] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[5], timeArray[2]);
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

                        countClass[33]++;
                        if (countClass[33] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[5], timeArray[3]);
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

                        countClass[34]++;
                        if (countClass[34] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[5], timeArray[4]);
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

                        countClass[35]++;
                        if (countClass[35] > 1 && Done == 0) {
                            getRreportOfDoubleClass(teacher, dayNameString[5], timeArray[5]);
                        }
                    }
                }
            }

            for (int x = 0; x < VirtualCourseCode.length; ++x) {
                if (VirtualCourseCode[x] == null) {
                    VirtualCourseCode[x] = "";
                }
            }

        } catch (SQLException e) {
            out.println(e.toString());
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
        processRequest(request, response);

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
        processRequest(request, response);
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

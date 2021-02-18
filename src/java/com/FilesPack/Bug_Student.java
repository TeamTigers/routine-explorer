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


public class Bug_Student extends HttpServlet {

    static Connection connection = null;
    static Statement st;
    static ResultSet rs;
    static String latestCourseOfferName = "";
    static String[] semesterName = {"Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8", "Semester 9", "Semester 10", "Semester 11"};
    static String subjectArray[] = new String[5];
    static String VirtualCourseCode[] = new String[36];
    static String RoutineVersion;
    static PrintWriter out;

    // continuous class
    public static ArrayList<String> sNameCC = new ArrayList<>();
    public static ArrayList<String> dNameCC = new ArrayList<>();
    public static ArrayList<String> sectionCC = new ArrayList<>();
    
    // whole day class
    public static ArrayList<String> sNameWC = new ArrayList<>();
    public static ArrayList<String> dNameWC = new ArrayList<>();
    public static ArrayList<String> sectionWC = new ArrayList<>();
    
    // five day  class
    public static ArrayList<String> sNameFC = new ArrayList<>();
    public static ArrayList<String> sectionFC = new ArrayList<>();
    
    // single class in a day
    public static ArrayList<String> sNameSC = new ArrayList<>();
    public static ArrayList<String> sectionSC = new  ArrayList<>();
    public  static ArrayList<String> dNameSC = new ArrayList<>();
    
    // gape more than 1.30 hours
    public  static  ArrayList<String> sNameGC = new ArrayList<>();
    public  static  ArrayList<String> dNameGC = new ArrayList<>();
    public  static  ArrayList<String> sectionGC = new ArrayList<>();
    public static ArrayList<String> timeGC = new ArrayList<>();
    
    // missing class
    public static ArrayList<String> courseMC = new ArrayList<>();
    
    
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
            System.out.println(e.toString());
        }
        
        
        st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM ExcelCourseOffer");
            
        while(rs.next())
        {
            latestCourseOfferName = rs.getString("FileNameCourse");   
        }
        st.close();
        rs.close();
        
        
        // clearing ram
        
        sNameCC.clear();
        dNameCC.clear();
        sectionCC.clear();
        sNameWC.clear();
        dNameWC.clear();
        sectionWC.clear();
        sNameFC.clear();
        sectionFC.clear();
        sNameSC.clear();
        sectionSC.clear();
        dNameSC.clear();
        sNameGC.clear();
        sectionGC.clear();
        dNameGC.clear();
        timeGC.clear();
        courseMC.clear();
        
        /**
         * Loop for checking if a section has continuous four classes in a day.
         */
        for(int i=0; i<11; i++)
        {
            reportForASemester(semesterName[i], out);
            
        }
        
        /**
         * loop for checking if a section has whole day classes like section has classes both 8:30-10:00 and 4:00-5:30 slot
         */
//        out.println("<h2> Whole Day Classes</h2>");
        for(int i=0; i<11; i++)
        {
            reportForASemesterForWholeClassInADay(semesterName[i], out);
        }
        
//        /**
//         * loop for checking if a section has five day classes in a week
//         */
//        out.println("<h2> Five Day Classes in a Week</h2>");
        for(int i=0; i<11; i++)
        {
            reportForFiveDayClassInAWeek(semesterName[i], out);
            
        }
//        
//        /**
//         * loop for checking if a section has single class in a day
//         */
//        out.println("<h2>Single Class in a Day</h2>");
        for(int i=0; i<11; i++)
        {
            reportForSingleClassOfADay(semesterName[i], out);
            
        }
//        
//        /**
//         * loop for checking if a section has more than 1:30 hours gap in a day
//         */
//        out.println("<h2>More Than 1:30 Hours Gap of A Section</h2>");
        for(int i=0; i<11; i++)
        {
            reportForHavingMoreThanOneClassGapOfASection(semesterName[i], out);
 
        }
//        
//        
//        out.println("<h2>Class Missing Of A Course</h2>");
        ClassMissingForASection();



    // setting response
    
    // continuous classes for a seciton
    request.setAttribute("sNameCC", sNameCC);
    request.setAttribute("dNameCC", dNameCC);
    request.setAttribute("sectionCC", sectionCC);
    
    // whole day class
    request.setAttribute("sNameWC", sNameWC);
    request.setAttribute("dNameWC", dNameWC);
    request.setAttribute("sectionWC", sectionWC);
     
    // five day class
    request.setAttribute("sNameFC", sNameFC);
    request.setAttribute("sectionFC", sectionFC);
    
    // single  class in a day
    request.setAttribute("sNameSC", sNameSC);
    request.setAttribute("sectionSC", sectionSC);
    request.setAttribute("dNameSC", dNameSC);
    
    // gap more than 1:30 hour
    request.setAttribute("sNameGC", sNameGC);
    request.setAttribute("sectionGC", sectionGC);
    request.setAttribute("dNameGC", dNameGC);
    request.setAttribute("timeGC", timeGC);
    
    // missing class
    request.setAttribute("courseMC", courseMC);
    
    request.getRequestDispatcher("showBugForStudent.jsp").forward(request, response);

    }
    
    
    /**
     * A Method That Trim String
     *  Such That
     * All Blank Spaces Are Removed From A Text & Return The Newly Trimmed Text
    */
    private static String trimmer(String xS) 
    {
        String yS = "";
        for (int d = 0; d < xS.length(); ++d) {
            if (xS.charAt(d) != ' ') {
                yS += String.valueOf(xS.charAt(d));
            }
        }
        return yS;
    }
    
    /**
     * sql connection method to get section from database
     * @param semester
     * @return 
     */
    
    private static ArrayList<String> getSectionsOfASemester(String semester)
    {
        ArrayList<String> section = new ArrayList<>();
        try 
        {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT DISTINCT Section FROM "+latestCourseOfferName+" WHERE Semester = '"+semester+"'");
            while(rs.next())
            {
                section.add(rs.getString("Section"));
            }
            st.close();
            rs.close();
        } catch (SQLException e) 
        {
            System.out.println(e.toString());
        }
        return section;
    }
    
    /**
     * sql connection method to get subject from database
     * @param semester
     * @return 
     */
    private static ArrayList<String> getSubjectsOfASemester(String semester)
    {
        ArrayList<String> subject = new ArrayList<>();
        try 
        {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT DISTINCT courseCode FROM "+latestCourseOfferName+" WHERE Semester = '"+semester+"'");
            while(rs.next())
            {
                subject.add(rs.getString("courseCode"));
            }
            st.close();
            rs.close();
        } catch (SQLException e) 
        {
            System.out.println(e.toString());
        }
        return subject;
    }
    
    
    /**
     * method to get which section has more than 4 classes in day
     * @param semester
     * @param out 
     */
    private static void reportForASemester(String semester, PrintWriter out)
    {
        ArrayList<String> section = new ArrayList<>();
        section = getSectionsOfASemester(semester);
        
        ArrayList<String> subject = new ArrayList<>();
        subject = getSubjectsOfASemester(semester);
        
        
        
        for(int i=0;  i<section.size(); i++)
        {
            
            for(int j = 0; j<subject.size();  j++)
            {
                //out.println(subject.get(j)+section.get(i));
                subjectArray[j] = subject.get(j)+ section.get(i);
                subjectArray[j]=trimmer(subjectArray[j]);
            }
            
            for(int k =0; k<subjectArray.length; k++)
            {
                if(subjectArray[k].equals(""))
                {
                    subjectArray[k] = "No_Value";       
                }
            }
            AssignCourseCode(subjectArray[0], subjectArray[1], subjectArray[2], subjectArray[3], subjectArray[4]);
         
            for(int p=0;p<subjectArray.length;p++){
                subjectArray[p] = "";
            }
            checkClassesForASection(semester, section.get(i));
            makingArrayNull();
         }
    }
    
    /**
     * method to get which section has classes both 8:30-10:00 and 4:00-5:30 slot
     * @param semester
     * @param out 
     */
    private static void reportForASemesterForWholeClassInADay(String semester, PrintWriter out)
    {
        ArrayList<String> section = new ArrayList<>();
        section = getSectionsOfASemester(semester);
        
        ArrayList<String> subject = new ArrayList<>();
        subject = getSubjectsOfASemester(semester);
        
        /**
         * Loop for checking whole day classes for each section of each semester
         */
       
        for(int i=0;  i<section.size(); i++)
        {   
            for(int j = 0; j<subject.size();  j++)
            {
                //out.println(subject.get(j)+section.get(i));
                subjectArray[j] = subject.get(j)+ section.get(i);
                subjectArray[j]=trimmer(subjectArray[j]);
            }
            
            for(int k =0; k<subjectArray.length; k++)
            {
                if(subjectArray[k].equals(""))
                {
                    subjectArray[k] = "No_Value";       
                }
                
            }
            AssignCourseCode(subjectArray[0], subjectArray[1], subjectArray[2], subjectArray[3], subjectArray[4]);
            
            for(int p=0;p<subjectArray.length;p++){
                subjectArray[p] = "";
            }
            
            wholeDayClass(semester, section.get(i));
            makingArrayNull();
         }
    }
    
    
    /**
     * generating report five day class in a week of a section
     * @param semester
     * @param out 
     */
    
    private static void reportForFiveDayClassInAWeek(String semester, PrintWriter out)
    {
        
        ArrayList<String> section = new ArrayList<>();
        section = getSectionsOfASemester(semester);
        
        ArrayList<String> subject = new ArrayList<>();
        subject = getSubjectsOfASemester(semester);
        
        /**
         * Loop for checking whole day classes for each section of each semester
         */
       
        for(int i=0;  i<section.size(); i++)
        {   
            for(int j = 0; j<subject.size();  j++)
            {
                //out.println(subject.get(j)+section.get(i));
                subjectArray[j] = subject.get(j)+ section.get(i);
                subjectArray[j]=trimmer(subjectArray[j]);
            }
            
            for(int k =0; k<subjectArray.length; k++)
            {
                if(subjectArray[k].equals(""))
                {
                    subjectArray[k] = "No_Value";       
                }
                
            }
            AssignCourseCode(subjectArray[0], subjectArray[1], subjectArray[2], subjectArray[3], subjectArray[4]);
            CheckFiveDayClassInAWeek(semester, section.get(i));
            for(int p=0;p<subjectArray.length;p++){
                subjectArray[p] = "";
            }
            
            makingArrayNull();
         }
    }
    
    /**
     * method for getting single class in a day of a section
     * @param semester
     * @param out 
     */
    private static void reportForSingleClassOfADay(String semester, PrintWriter out)
    {
        
        ArrayList<String> section = new ArrayList<>();
        section = getSectionsOfASemester(semester);
        
        ArrayList<String> subject = new ArrayList<>();
        subject = getSubjectsOfASemester(semester);
        
        /**
         * Loop for checking whole day classes for each section of each semester
         */
       
        for(int i=0;  i<section.size(); i++)
        {   
            for(int j = 0; j<subject.size();  j++)
            {
                //out.println(subject.get(j)+section.get(i));
                subjectArray[j] = subject.get(j)+ section.get(i);
                subjectArray[j]=trimmer(subjectArray[j]);
            }
            
            for(int k =0; k<subjectArray.length; k++)
            {
                if(subjectArray[k].equals(""))
                {
                    subjectArray[k] = "No_Value";       
                }
                
            }
            AssignCourseCode(subjectArray[0], subjectArray[1], subjectArray[2], subjectArray[3], subjectArray[4]);
            checkForSingleClassInADay(semester, section.get(i));
            for(int p=0;p<subjectArray.length;p++){
                subjectArray[p] = "";
            }
            
            makingArrayNull();
         }
    }
    
    /**
     * method for having more than 1:30 hours gap of a section
     * @param semester
     * @param out 
     */
    private static void reportForHavingMoreThanOneClassGapOfASection(String semester, PrintWriter out)
    {
        
        ArrayList<String> section = new ArrayList<>();
        section = getSectionsOfASemester(semester);
        
        ArrayList<String> subject = new ArrayList<>();
        subject = getSubjectsOfASemester(semester);
        
        /**
         * Loop for checking whole day classes for each section of each semester
         */
       
        for(int i=0;  i<section.size(); i++)
        {   
            for(int j = 0; j<subject.size();  j++)
            {
                //out.println(subject.get(j)+section.get(i));
                subjectArray[j] = subject.get(j)+ section.get(i);
                subjectArray[j]=trimmer(subjectArray[j]);
            }
            
            for(int k =0; k<subjectArray.length; k++)
            {
                if(subjectArray[k].equals(""))
                {
                    subjectArray[k] = "No_Value";       
                }
                
            }
            AssignCourseCode(subjectArray[0], subjectArray[1], subjectArray[2], subjectArray[3], subjectArray[4]);
            checkGapMoreThanOneClass(semester, section.get(i));
            for(int p=0;p<subjectArray.length;p++){
                subjectArray[p] = "";
            }
            
            makingArrayNull();
         }
    }
    
    /**
     * Check if the section has five day classes in a week
     * @param semester
     * @param section 
     */
    private static void CheckFiveDayClassInAWeek(String semester, String section)
    {
        int k =0;
        if(VirtualCourseCode[0]!="" || VirtualCourseCode[1]!="" || VirtualCourseCode[2]!=""
                || VirtualCourseCode[3]!="" || VirtualCourseCode[4]!="" || VirtualCourseCode[5]!="")
        {
            k++;
        }
        
        if(VirtualCourseCode[6]!="" || VirtualCourseCode[7]!="" || VirtualCourseCode[8]!=""
                || VirtualCourseCode[9]!="" || VirtualCourseCode[10]!="" || VirtualCourseCode[11]!="")
        {
            k++;
        }
        
        if(VirtualCourseCode[12]!="" || VirtualCourseCode[13]!="" || VirtualCourseCode[14]!=""
                || VirtualCourseCode[15]!="" || VirtualCourseCode[16]!="" || VirtualCourseCode[17]!="")
        {
            k++;
        }
        
        if(VirtualCourseCode[18]!="" || VirtualCourseCode[19]!="" || VirtualCourseCode[20]!=""
                || VirtualCourseCode[21]!="" || VirtualCourseCode[22]!="" || VirtualCourseCode[23]!="")
        {
            k++;
        }
        
        if(VirtualCourseCode[24]!="" || VirtualCourseCode[25]!="" || VirtualCourseCode[26]!=""
                || VirtualCourseCode[27]!="" || VirtualCourseCode[28]!="" || VirtualCourseCode[29]!="")
        {
            k++;
        }
        
        if(VirtualCourseCode[30]!="" || VirtualCourseCode[31]!="" || VirtualCourseCode[32]!=""
                || VirtualCourseCode[33]!="" || VirtualCourseCode[34]!="" || VirtualCourseCode[35]!="")
        {
            k++;
        }
        
        if(k==5)
        {
            sNameFC.add(semester);
            sectionFC.add(section);
        }
    }
    
    
    /**
     * displaying report for continuous classes of a section
     * @param teacher
     * @param day 
     */
    private static void continuousClassReport(String semester, String section, String day)
    {
        sNameCC.add(semester);
        dNameCC.add(day);
        sectionCC.add(section);
    }
    
    /**
     * 
     * Check continuous classes for each section of each semester
     * @param semester
     * @param section 
     */
    
    private static void checkClassesForASection(String semester, String section)
    {
        
        int k = 0;
        
        // s a t u r d a y
        for(int i=0; i<6; i++)
        {
            if(VirtualCourseCode[i].equals(""))
            {
                k=0;
            }
            else 
            {
                k++;
            }
            if(k>3)
            {
                continuousClassReport(semester, section, "Saturday");
                break;
            }
        }
        
        // s u n d a y
        k=0;
        for(int i=6; i<12; i++)
        {
            if(VirtualCourseCode[i].equals(""))
            {
                k=0;
            }
            else 
            {
                k++;
            }
            if(k>3)
            {
               continuousClassReport(semester, section,  "Sunday");
               break;
            }
        }
        
        // m o n d a y
        k=0;
        for(int i=12; i<18; i++)
        {
            if(VirtualCourseCode[i].equals(""))
            {
                k=0;
            }
            else 
            {
                k++;
            }
            if(k>3)
            {
               continuousClassReport(semester, section,  "Monday");
               break;
            }
        }
        
        // t u e s d a y
        k=0;
        for(int i=18; i<24; i++)
        {
            if(VirtualCourseCode[i].equals(""))
            {
                k=0;
            }
            else 
            {
                k++;
            }
            if(k>3)
            {
               continuousClassReport(semester, section,  "Tuesday");
               break;
            }
        }
        
        // w e d n e s d a y
        k=0;
        for(int i=24; i<30; i++)
        {
            if(VirtualCourseCode[i].equals(""))
            {
                k=0;
            }
            else 
            {
                k++;
            }
            if(k>3)
            {
                continuousClassReport(semester, section,  "Wednesday");
                break;
            }
        }
        
        // t h u r s d a y 
        k=0;
        for(int i=30; i<36; i++)
        {
            if(VirtualCourseCode[i].equals(""))
            {
                k=0;
            }
            else 
            {
                k++;
            }
            if(k>3)
            {
                continuousClassReport(semester, section,  "Thursday");
                break;
            }
        }
    }
    
    
    /**
     * Whole day classes for each section of each semester
     * @param teacher 
     */
    
    private static void wholeDayClass(String semester, String section)
    {
        if(VirtualCourseCode[0]!="" && VirtualCourseCode[5]!="")
        {
            sNameWC.add(semester);
            sectionWC.add(section);
            dNameWC.add("Saturday");
        }
        
        if(VirtualCourseCode[6]!="" && VirtualCourseCode[11]!="")
        {
            sNameWC.add(semester);
            sectionWC.add(section);
            dNameWC.add("Sunday");
        }
        
        if(VirtualCourseCode[12]!="" && VirtualCourseCode[17]!="")
        {
            sNameWC.add(semester);
            sectionWC.add(section);
            dNameWC.add("Monday");
        }
        
        if(VirtualCourseCode[18]!="" && VirtualCourseCode[23]!="")
        {
            sNameWC.add(semester);
            sectionWC.add(section);
            dNameWC.add("Tuesday");
        }
        
        if(VirtualCourseCode[24]!="" && VirtualCourseCode[29]!="")
        {
            sNameWC.add(semester);
            sectionWC.add(section);
            dNameWC.add("Wednesday");
        }
        
        if(VirtualCourseCode[30]!="" && VirtualCourseCode[35]!="")
        {
            sNameWC.add(semester);
            sectionWC.add(section);
            dNameWC.add("Thursday");
        }
    }
    
    
    /**
     * method for to check if a section has only a single class in a day
     * @param semester
     * @param section 
     */
  private static void checkForSingleClassInADay(String semester, String section)
  {
        int k=0;
       // s a t u r d a y
        for(int i=0; i<6; i++)
        {
            if(VirtualCourseCode[i]!="")
            {
                k++;
            }
        }
         if(k==1)
            {
                sNameSC.add(semester);
                sectionSC.add(section);
                dNameSC.add("Saturday");
            }
        
        // s u n d a y
        k=0;
        for(int i=6; i<12; i++)
        {
            if(VirtualCourseCode[i]!="")
            {
                k++;
            }
        }
        if(k==1)
            {
                sNameSC.add(semester);
                sectionSC.add(section);
                dNameSC.add("Sunday");
            }
        
        // m o n d a y
        k=0;
        for(int i=12; i<18; i++)
        {
            if(VirtualCourseCode[i]!="")
            {
                k++;
            }
        }
        if(k==1)
            {
                sNameSC.add(semester);
                sectionSC.add(section);
                dNameSC.add("Monday");
            }
        
        // t u e s d a y
        k=0;
        for(int i=18; i<24; i++)
        {
            if(VirtualCourseCode[i]!="")
            {
                k++;
            }
        }
        if(k==1)
            {
                sNameSC.add(semester);
                sectionSC.add(section);
                dNameSC.add("Tuesday");
            }
        
        // w e d n e s d a y
        k=0;
        for(int i=24; i<30; i++)
        {
            if(VirtualCourseCode[i]!="")
            {
                k++;
            }
        }
        if(k==1)
            {
                sNameSC.add(semester);
                sectionSC.add(section);
                dNameSC.add("Wednesday");
            }
        
        // t h u r s d a y 
        k=0;
        for(int i=30; i<36; i++)
        {
            if(VirtualCourseCode[i]!="")
            {
                k++;
            }
        }
        if(k==1)
            {
                sNameSC.add(semester);
                sectionSC.add(section);
                dNameSC.add("Thursday");
            }
  }
  
  /**
   * displaying report of a section having single class in a day
   * @param semester
   * @param section
   * @param day 
   */
    private static void displaySingleClassOfASection(String semester, String section, String day)
    {
       out.println(semester+" Section "+section+" has only one class in "+day);
    }
    
    /**
     * method to check more than 1:30 hours gap of a section
     * @param semester
     * @param section 
     */
    private static void checkGapMoreThanOneClass(String semester, String section)
    {
        String containsClass = "";
       // s a t u r d a y
        for(int i=0; i<6; i++)
        {
            if(VirtualCourseCode[i].equals(""))
            {
                containsClass+="N";
            }
            else
            {
                containsClass+="Y";
            }
        }
        if(containsClass.contains("YNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Saturday", "3 hours");
        }
        else if(containsClass.contains("YNNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Saturday", "4:30 hours");
        }
         
        // s u n d a y
        containsClass = "";
        for(int i=6; i<12; i++)
        {
             if(VirtualCourseCode[i].equals(""))
            {
                containsClass+="N";
            }
            else
            {
                containsClass+="Y";
            }
        }
       if(containsClass.contains("YNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Sunday", "3 hours");
        }
        else if(containsClass.contains("YNNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Sunday", "4:30 hours");
        }
        
        // m o n d a y
        containsClass ="";
        for(int i=12; i<18; i++)
        {
             if(VirtualCourseCode[i].equals(""))
            {
                containsClass+="N";
            }
            else
            {
                containsClass+="Y";
            }
        }
       if(containsClass.contains("YNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Monday", "3 hours");
        }
        else if(containsClass.contains("YNNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Monday", "4:30 hours");
        }
        
        // t u e s d a y
        containsClass = "";
        for(int i=18; i<24; i++)
        {
             if(VirtualCourseCode[i].equals(""))
            {
                containsClass+="N";
            }
            else
            {
                containsClass+="Y";
            }
        }
       if(containsClass.contains("YNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Tuesday", "3 hours");
        }
        else if(containsClass.contains("YNNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Tuesday", "4:30 hours");
        }
       
        // w e d n e s d a y
        containsClass = "";
        for(int i=24; i<30; i++)
        {
             if(VirtualCourseCode[i].equals(""))
            {
                containsClass+="N";
            }
            else
            {
                containsClass+="Y";
            }
        }
        if(containsClass.contains("YNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Wednesday", "3 hours");
        }
        else if(containsClass.contains("YNNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Wednesday", "4:30 hours");
        }
        
        // t h u r s d a y 
        containsClass = "";
        for(int i=30; i<36; i++)
        {
             if(VirtualCourseCode[i].equals(""))
            {
                containsClass+="N";
            }
            else
            {
                containsClass+="Y";
            }
        }
        if(containsClass.contains("YNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Thursday", "3 hours");
        }
        else if(containsClass.contains("YNNNY"))
        {
            displayMoreThanOneHourGapOfASection(semester, section, "Thursday", "4:30 hours");
        }
    }
    
    /**
     * display report of a section having more than 1:30 hours gap in a day
     * @param semester
     * @param section
     * @param day 
     */
    private static void displayMoreThanOneHourGapOfASection(String semester, String section, String day, String time)
    {
        sNameGC.add(semester);
        dNameGC.add(day);
        sectionGC.add(section);
        timeGC.add(time);
    }
    
    /**
     * Checking if course is missing 
     */
    private static void ClassMissingForASection()
    {
        Statement st1, st2;
        ResultSet rs1, rs2;
        try 
        {
            st1 = connection.createStatement();
            rs1 = st1.executeQuery("SELECT * FROM "+latestCourseOfferName);
            
            while(rs1.next())
            {
                double credit = rs1.getDouble("Credit");
                if(credit == 1.0)
                {
                    int count = 0;
                    String courseForCreditOne = rs1.getString("courseCode")+rs1.getString("Section")+"LAB1";
                    courseForCreditOne = trimmer(courseForCreditOne);
                    
                    st2 = connection.createStatement();
                    rs2 = st2.executeQuery("SELECT * FROM "+RoutineVersion+" WHERE Course LIKE '"+courseForCreditOne+"' OR Course LIKE '"+courseForCreditOne+" '");
                    
                    while(rs2.next())
                    {
                        count++;
                    }
                    if(count!=1)
                    {
                        courseMC.add(courseForCreditOne);
                    }
                    st2.close();
                    rs2.close();
                }
                
                if(credit == 2.0)
                {
                    int count = 0;
                    String courseForCreditTwo = rs1.getString("courseCode")+rs1.getString("Section")+"LAB";
                    courseForCreditTwo = trimmer(courseForCreditTwo);
                    
                    st2 = connection.createStatement();
                    rs2 = st2.executeQuery("SELECT * FROM "+RoutineVersion+" WHERE Course LIKE '"+courseForCreditTwo+"%' OR Course LIKE '"+courseForCreditTwo+" '");
                    
                    while(rs2.next())
                    {
                        count++;
                    }
                    if(count!=2)
                    {
                        courseMC.add(courseForCreditTwo);
                    }
                    st2.close();
                    rs2.close();
                }
                
                if(credit == 3.0)
                {
                    int count = 0;
                    String courseForCreditThree = rs1.getString("courseCode")+rs1.getString("Section");
                    courseForCreditThree = trimmer(courseForCreditThree);
                    
                    st2 = connection.createStatement();
                    rs2 = st2.executeQuery("SELECT * FROM "+RoutineVersion+" WHERE Course LIKE '"+courseForCreditThree+"' OR Course LIKE '"+courseForCreditThree+" '");
                    
                    while(rs2.next())
                    {
                        count++;
                    }
                    if(count!=2)
                    {
                        courseMC.add(courseForCreditThree);
                    }
                    st2.close();
                    rs2.close();
                }
            }
            
            
            
        } catch (Exception e) 
        {
            out.println(e.toString());
        }
    }
    
    
    
    private static void AssignCourseCode(String a, String b, String c, String d, String e)
    {
        String[] timeArray = {"8:30-10:00", "10:00-11:30", "11:30-1:00", "1:00-2:30", "2:30-4:00", "4:00-5:30"};
        String[] dayNameString = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};

        try 
        {
            st = connection.createStatement();
            String SQLQuery
                    = "SELECT *FROM " + RoutineVersion + " Where"
                    + " Course LIKE '" + a + "%' OR"
                    + " Course LIKE '" + b + "%' OR"
                    + " Course LIKE '" + c + "%' OR"
                    + " Course LIKE '" + d + "%' OR"
                    + " Course LIKE '" + e + "%';";

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
                    VirtualCourseCode[x] = "";
                }
            }
            
        rs.close();
        st.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.toString());
        }
    }
    
    /**
     * Making array to null
     */
    private static void makingArrayNull()
    {
        for(int i=0; i<36; i++)
        {
            VirtualCourseCode[i] = "";
        }
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

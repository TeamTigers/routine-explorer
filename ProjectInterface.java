package com.FilesPack;

import java.io.PrintWriter;
import java.util.ArrayList;


public interface ProjectInterface 
{
    /*
    ** Methods for finding bug on students routine
    */
    public String trimmer(String readText);
    public ArrayList<String> getSectionsOfASemester(String semester);
    public ArrayList<String> getSubjectsOfASemester(String semester);
    public void reportForASemester(String semester, PrintWriter out);
    public void reportForASemesterForWholeClassInADay(String semester, PrintWriter out);
    public void reportForFiveDayClassInAWeek(String semester, PrintWriter out);
    public void reportForSingleClassOfADay(String semester, PrintWriter out);
    public void reportForHavingMoreThanOneClassGapOfASection(String semester, PrintWriter out);
    public void CheckFiveDayClassInAWeek(String semester, String section);
    public void continuousClassReport(String semester, String section, String day);
    public void checkClassesForASection(String semester, String section);
    public void wholeDayClass(String semester, String section);
    public void checkForSingleClassInADay(String semester, String section);
    public void displaySingleClassOfASection(String semester, String section, String day);
    public void displayMoreThanOneHourGapOfASection(String semester, String section, String day, String time);
    public void ClassMissingForASection();
    public void AssignCourseCode(String a, String b, String c, String d, String e);
    public void makingArrayNull();
    
    /*
    ** Methods for finding bug on teachers routine
    */
    public void CopyTeacherList();
    public void checkClassesForATeacher(String teacher);
    public void wholeDayClass(String teacher);
    public boolean checkCampus(String perDayRoom);
    public void checkBothCampusClass(String teacher);
    public void continuousClassReport(String teacher, String day);
    public void makeCounterzero(int countClass[]);
    public void getArray(String teacher);
}

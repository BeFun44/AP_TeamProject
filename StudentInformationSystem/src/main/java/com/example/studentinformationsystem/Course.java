package com.example.studentinformationsystem;

public class Course {
private int courseID;
private String courseName;
private String DepartmentId;



    public void setDepartmentId(String departmentId) {
        DepartmentId = departmentId;
    }


    private String courseCode;
    private int CreditHours;

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCreditHours(int creditHours) {
        CreditHours = creditHours;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }
    public String getDepartmentId() {
        return DepartmentId;
    }

    public int getCreditHours() {
        return CreditHours;
    }

    public Course(int courseID, String courseName, String courseCode, int creditHours,String departmentId) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
        CreditHours = creditHours;
        DepartmentId = departmentId;


    }
}

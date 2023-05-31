package com.example.studentinformationsystem;

public class StudentInfo {
   private String student_id;
    private String first_name;
    private String last_name;
    private int course_id;
    private String course_name;
    private int mark_id;
    private double mark;

    public StudentInfo(String student_id, String first_name, String last_name, int course_id, String course_name, int mark_id, double mark) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.course_id = course_id;
        this.course_name = course_name;
        this.mark_id = mark_id;
        this.mark = mark;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getMark_id() {
        return mark_id;
    }

    public void setMark_id(int mark_id) {
        this.mark_id = mark_id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}

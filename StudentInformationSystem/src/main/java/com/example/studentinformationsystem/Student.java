package com.example.studentinformationsystem;

import java.sql.Date;

public class Student {
    private String Student_ID;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Address;
    private Date Date_of_birth;
    private String Gender;
    private Date Date_of_Enrolled;

    public Student(String student_ID, String firstName, String lastName, String email, String address, Date date_of_birth, String gender, Date date_of_Enrolled) {
        Student_ID = student_ID;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Address = address;
        Date_of_birth = date_of_birth;
        Gender = gender;
        Date_of_Enrolled = date_of_Enrolled;
    }

    public Student(String student_id, String first_name, String last_name, int course_id, String course_name, int mark_id, double mark_obtained) {
    }

    public String getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(String student_ID) {
        Student_ID = student_ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getDate_of_birth() {
        return Date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        Date_of_birth = date_of_birth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getDate_of_Enrolled() {
        return Date_of_Enrolled;
    }

    public void setDate_of_Enrolled(Date date_of_Enrolled) {
        Date_of_Enrolled = date_of_Enrolled;
    }


}

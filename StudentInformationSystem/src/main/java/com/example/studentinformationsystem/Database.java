package com.example.studentinformationsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connectDb() {

        try{

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system","root", "");
            return connect;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

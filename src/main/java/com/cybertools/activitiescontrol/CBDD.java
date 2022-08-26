package com.cybertools.activitiescontrol;

//@author jpjar

import java.sql.*;
 
public class CBDD {
    
    private Statement st;
    private static Connection conn;

    static {
        String url = "jdbc:mysql://localhost/poo?useSSL=false";
        String user = "root";
        String password = "Sup3r";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    public static Connection getConnection() {
        return conn;
    }

}

package org.example;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/sakila";
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "2512");
        try {
            conn = DriverManager.getConnection(url, info);
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package org.example;

import java.sql.*;

public class ProcedureExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "2512";
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            // Create a connection to the database
            con = DriverManager.getConnection(url, username, password);
            // Create a callable statement object
            cstmt = con.prepareCall("{call get_customer_name(?, ?)}");
            // Set the input parameter
            cstmt.setInt(1, 1);
            // Register the output parameter
            cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            // Execute the stored procedure
            cstmt.execute();
            // Get the output parameter value
            String name = cstmt.getString(2);
            System.out.println("Customer name: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the callable statement and connection objects
            try { if (cstmt != null) cstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }


        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement stmt = conn.prepareCall("{? = call get_film_count(?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, "Comedy");
            stmt.execute();
            int count = stmt.getInt(1);
            System.out.println("Number of films in the Comedy category: " + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
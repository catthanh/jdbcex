package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JoinQueryExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "2512";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Create a connection to the database
            con = DriverManager.getConnection(url, username, password);
            // Create a statement object
            stmt = con.createStatement();
            // Execute a join query to get the rental and customer data
            rs = stmt.executeQuery("SELECT rental.rental_id, rental.rental_date, rental.return_date, customer.first_name, customer.last_name "
                    + "FROM rental JOIN customer ON rental.customer_id = customer.customer_id "
                    + "ORDER BY rental.rental_date DESC");

            // Iterate through the result set and print the data
            while (rs.next()) {
                int rentalId = rs.getInt("rental_id");
                String rentalDate = rs.getString("rental_date");
                String returnDate = rs.getString("return_date");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                System.out.println(rentalId + "\t" + rentalDate + "\t" + returnDate + "\t" + firstName + "\t" + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection objects
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
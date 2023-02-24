package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "2512";
        Connection con = null;
        Statement stmt = null;

        try {
            // Create a connection to the database
            con = DriverManager.getConnection(url, username, password);
            // Turn off auto-commit mode
            con.setAutoCommit(false);
            stmt = con.createStatement();
            // Charge a rental fee of $4.99 to customer 1 for rental 1
            stmt.executeUpdate("UPDATE payment SET amount = 4.99 WHERE customer_id = 1 AND rental_id = 1");
            // Give a refund of $4.99 to customer 2 for rental 2
            stmt.executeUpdate("UPDATE payment SET amount = -4.99 WHERE customer_id = 2 AND rental_id = 2");
            // Commit the transaction
            con.commit();
            System.out.println("Transaction completed successfully.");
        } catch (SQLException e) {
            // Roll back the transaction in case of an error
            try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            // Close the statement and connection objects
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
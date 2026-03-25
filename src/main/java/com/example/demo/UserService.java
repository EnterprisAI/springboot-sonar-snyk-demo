package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Demo service with intentional code quality issues for pre-push hook testing.
 */
public class UserService {

    // S2068: Hardcoded credentials
    private static final String DB_PASSWORD = "admin123";
    private static final String DB_URL = "jdbc:mysql://localhost/mydb";

    public String getUserById(String userId) throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL, "root", DB_PASSWORD);
        Statement stmt = conn.createStatement();
        // S2077: SQL injection — user input concatenated directly into query
        ResultSet rs = stmt.executeQuery("SELECT name FROM users WHERE id = " + userId);
        if (rs.next()) {
            return rs.getString("name");
        }
        return null; // S2259: potential NullPointerException on caller
    }

    public boolean authenticate(String inputPassword) {
        String expected = "secret123"; // S2068: hardcoded password
        // S4973: String comparison using == instead of .equals()
        if (inputPassword == expected) {
            return true;
        }
        return false;
    }
}

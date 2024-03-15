package org.hospital.repository;

import java.sql.*;

public class AdminRepository extends DBConfig {

    public boolean validateAdminCredentials(String username, String password) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM Admin WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            return rs.next(); // If a row is returned, credentials are valid
        } catch (SQLException ex) {
            System.out.println("Error validating admin credentials: " + ex.getMessage());
            return false;
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
    }
}


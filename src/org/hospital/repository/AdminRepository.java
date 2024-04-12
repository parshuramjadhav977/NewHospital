package org.hospital.repository;

import java.sql.*;

public class AdminRepository extends DBConfig {

    public boolean validateAdminCredentials(String username, String password) {
        try {
            stmt = conn.prepareStatement("select * from admin where username = ? and password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            return rs.next(); 
        } catch (SQLException ex) {
            System.out.println("Error validating admin credentials: " + ex.getMessage());
            return false;
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
    }
}


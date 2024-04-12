package org.hospital.repository;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hospital.model.AllocatePatientModel;

public class AllocatePatientRepository extends DBConfig {
    
    public List<AllocatePatientModel> getAllAllocatedPatients() {
        List<AllocatePatientModel> allocatedPatients = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select p.ptid, p.ptName, p.docid, d.docname " +"FROM patient p " +"inner join docter d on p.docid = d.docid");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                AllocatePatientModel patient = new AllocatePatientModel();
                patient.setPtid(rs.getInt("ptid"));
                patient.setPtName(rs.getString("ptName"));
                patient.setDocid(rs.getInt("docid"));
                patient.setDoctorName(rs.getString("docname"));
                allocatedPatients.add(patient);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving allocated patients: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return allocatedPatients;
    }

    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
}





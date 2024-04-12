package org.hospital.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hospital.model.PrescriptionModel;
import org.hospital.service.PrescriptionService;

public class PrescriptionRepository extends DBConfig {

    public void allocatePrescriptionToPatient(PrescriptionModel prescription) {
        try {
            String query = "insert into Prescriptions (Prescription_id, Prescription_details, ptid, docid) values ('0', ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setInt(1, prescription.getPrescriptionId());
            pstmt.setString(1, prescription.getPrescriptionDetails());
            pstmt.setInt(2, prescription.getPtid());
            pstmt.setInt(3, prescription.getDocid());
            
            pstmt.executeUpdate();
            
            System.out.println("Prescription allocated successfully.");
        } catch (SQLException ex) {
            System.out.println("Error allocating prescription: " + ex.getMessage());
        } finally {
            closeResources();
        }
    }

	private void closeResources() {
		
	}
	
	
}


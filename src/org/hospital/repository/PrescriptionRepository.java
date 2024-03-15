package org.hospital.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hospital.model.PrescriptionModel;
import org.hospital.service.PrescriptionService;

public class PrescriptionRepository extends DBConfig {

    public void allocatePrescriptionToPatient(PrescriptionModel prescription) {
        try {
            // Prepare the SQL statement to insert the prescription
            String query = "INSERT INTO Prescriptions (Prescription_id, Prescription_details, ptid, docid) VALUES ('0', ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setInt(1, prescription.getPrescriptionId());
            pstmt.setString(1, prescription.getPrescriptionDetails());
            pstmt.setInt(2, prescription.getPtid());
            pstmt.setInt(3, prescription.getDocid());
            
            // Execute the SQL statement
            pstmt.executeUpdate();
            
            System.out.println("Prescription allocated successfully.");
        } catch (SQLException ex) {
            System.out.println("Error allocating prescription: " + ex.getMessage());
        } finally {
            closeResources();
        }
    }

	private void closeResources() {
		// TODO Auto-generated method stub
		
	}
}


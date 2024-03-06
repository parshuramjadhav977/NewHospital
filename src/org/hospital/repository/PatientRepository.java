package org.hospital.repository;


import java.sql.SQLException;





import java.util.*;


import org.hospital.model.PatientModel;

public class PatientRepository extends DBConfig {
	List<PatientModel> listPatient = new ArrayList<PatientModel>();
    PatientModel patient = new PatientModel();

    public boolean addPatient(PatientModel model) {
        try {
            stmt = conn.prepareStatement("INSERT INTO Patient  VALUES ('0',?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, model.getPtName());
            stmt.setInt(2, model.getAge());
            stmt.setString(3, model.getGender());
            stmt.setString(4, model.getContact());
            stmt.setString(5, model.getAddress());
            stmt.setDate(6, new java.sql.Date(model.getOpdDate().getTime()));
            stmt.setInt(7, model.getDocid());
            stmt.setInt(8, model.getFees());
            stmt.setDate(9, new java.sql.Date(model.getAppointmentDate().getTime()));
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error adding patient: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }


    
    public List<PatientModel> getAllPatients() {
		try {
			stmt = conn.prepareStatement("select * from Docter");
			rs = stmt.executeQuery();
			while (rs.next()) {
				patient.setPtid(rs.getInt(1));
				patient.setPtName(rs.getNString(2));
				patient.setAge(rs.getInt(3));
				patient.setGender(rs.getString(4));
				patient.setContact(rs.getString(5));
				patient.setAddress(rs.getString(6));
				patient.setOpdDate(rs.getDate(7));
				patient.setDocid(rs.getInt(8));
				patient.setFees(rs.getInt(9));
				patient.setAppointmentDate(rs.getDate(10));
				listPatient.add(patient);
				}
			return listPatient.size() > 0 ? listPatient : null;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;

		}

	}
   
    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }

	
}


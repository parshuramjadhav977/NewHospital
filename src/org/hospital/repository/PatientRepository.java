package org.hospital.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.hospital.model.PatientModel;
import org.hospital.model.PatientModel.Category;
import org.hospital.model.PatientModel.Gender;
import org.hosspital.helper.PathHelper;

public class PatientRepository extends DBConfig {
    // Existing methods

    public boolean addPatient(PatientModel model) {
        try {
            stmt = conn.prepareStatement("INSERT INTO patient (ptName, age, Gender, contact, address, opddate, docid, fess, appoinmentdate, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, model.getPtName());
            stmt.setInt(2, model.getAge());
            stmt.setString(3, model.getGender().toString()); // Convert enum to string
            stmt.setString(4, model.getContact());
            stmt.setString(5, model.getAddress());
            stmt.setDate(6, new java.sql.Date(model.getOpdDate().getTime()));
            stmt.setInt(7, model.getDocid());
            stmt.setFloat(8, model.getFess());
            stmt.setDate(9, new java.sql.Date(model.getAppointmentDate().getTime()));
            stmt.setString(10, model.getCategory().toString()); // Convert enum to string
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error adding patient: " + ex.getMessage());
            return false;
        } finally {
        }
    }



    

    
public List<PatientModel> getAllPatients() {
    List<PatientModel> listPatient = new ArrayList<>();
    try {
        stmt = conn.prepareStatement("SELECT * FROM patient");
        rs = stmt.executeQuery();
        while (rs.next()) {
            PatientModel patient = new PatientModel(); // Create a new PatientModel object for each record
            patient.setPtid(rs.getInt("ptid"));
            patient.setPtName(rs.getString("ptName"));
            patient.setAge(rs.getInt("age"));
            patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase())); // Set gender as enum
            patient.setContact(rs.getString("contact"));
            patient.setAddress(rs.getString("address"));
            patient.setOpdDate(rs.getDate("opddate"));
            patient.setDocid(rs.getInt("docid"));
            patient.setFess(rs.getFloat("fess"));
            patient.setAppointmentDate(rs.getDate("appoinmentdate"));
            // Assuming category is retrieved from the database as a string and set as an enum
            patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
            listPatient.add(patient);
        }
        return listPatient;
    } catch (SQLException ex) {
        System.out.println("Error retrieving patients: " + ex.getMessage());
        return null;
    }
}




private Category category;




	private void closeResources() {
	// TODO Auto-generated method stub
	
}
	
	
	public List<PatientModel> searchPatientsByName(String name) {
	    List<PatientModel> searchResults = new ArrayList<>();
	    try {
	        stmt = conn.prepareStatement("SELECT * FROM patient WHERE ptName = ?");
	        stmt.setString(1, name);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            PatientModel patient = new PatientModel();
	            
	            
	            patient.setPtid(rs.getInt("ptid"));
	            patient.setPtName(rs.getString("ptName"));
	            patient.setAge(rs.getInt("age"));
	            patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase())); // Set gender as enum
	            patient.setContact(rs.getString("contact"));
	            patient.setAddress(rs.getString("address"));
	            patient.setOpdDate(rs.getDate("opddate"));
	            patient.setDocid(rs.getInt("docid"));
	            patient.setFess(rs.getFloat("fess"));
	            patient.setAppointmentDate(rs.getDate("appoinmentdate"));
	            patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase())); // Set category as enum
	            searchResults.add(patient);
	        }
	    } catch (SQLException ex) {
	        System.out.println("Error searching patients by name: " + ex.getMessage());
	    } finally {
	        closeResources();
	    }
	    return searchResults;
	}





	public boolean deletePatientById(int patientId) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM patient WHERE ptid = ?")) {
            stmt.setInt(1, patientId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting patient: " + ex.getMessage());
            return false;
        }
    }

	public List<PatientModel> getPatientsByCategory(String category) {
	    List<PatientModel> listPatient = new ArrayList<>();
	    try {
	        stmt = conn.prepareStatement("SELECT * FROM patient WHERE category = ?");
	        stmt.setString(1, category);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            PatientModel patient = new PatientModel(); // Create a new PatientModel object for each record
	            patient.setPtid(rs.getInt("ptid"));
	            patient.setPtName(rs.getString("ptName"));
	            patient.setAge(rs.getInt("age"));
	            patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase())); // Set gender as enum
	            patient.setContact(rs.getString("contact"));
	            patient.setAddress(rs.getString("address"));
	            patient.setOpdDate(rs.getDate("opddate"));
	            patient.setDocid(rs.getInt("docid"));
	            patient.setFess(rs.getFloat("fess"));
	            patient.setAppointmentDate(rs.getDate("appoinmentdate"));
	            patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase())); // Set category as enum
	            listPatient.add(patient);
	        }
	        return listPatient;
	    } catch (SQLException ex) {
	        System.out.println("Error retrieving patients by category: " + ex.getMessage());
	        return null;
	    }
	}










	


}



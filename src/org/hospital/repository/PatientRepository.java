package org.hospital.repository;

import java.sql.*;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import org.hospital.model.PatientModel;
import org.hospital.model.PatientModel.Category;
import org.hospital.model.PatientModel.Gender;

public class PatientRepository extends DBConfig {

    private static final ArrayList PatientModel = null;

	public boolean addPatient(PatientModel model) {
        try {
            stmt = conn.prepareStatement("insert into patient (ptName, age, Gender, contact, address, opddate, docid, fess, appoinmentdate, category) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, model.getPtName());
            stmt.setInt(2, model.getAge());
            stmt.setString(3, model.getGender().toString()); 
            stmt.setString(4, model.getContact());
            stmt.setString(5, model.getAddress());
            stmt.setDate(6, new java.sql.Date(model.getOpdDate().getTime()));
            stmt.setInt(7, model.getDocid());
            stmt.setFloat(8, model.getFess());
            stmt.setDate(9, new java.sql.Date(model.getAppointmentDate().getTime()));
            stmt.setString(10, model.getCategory().toString()); 
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
	        stmt = conn.prepareStatement("select * from patient");
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            PatientModel patient = new PatientModel();
	            patient.setPtid(rs.getInt("ptid"));
	            patient.setPtName(rs.getString("ptName"));
	            patient.setAge(rs.getInt("age"));
	            patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase())); 
	            patient.setContact(rs.getString("contact"));
	            patient.setAddress(rs.getString("address"));
	            patient.setOpdDate(rs.getDate("opddate"));
	            patient.setDocid(rs.getInt("docid"));
	            patient.setFess(rs.getFloat("fess"));
	            patient.setAppointmentDate(rs.getDate("appoinmentdate"));
	            patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
	            listPatient.add(patient);
	        }
	        return listPatient;
	    } catch (SQLException ex) {
	        System.out.println("Error retrieving patients: " + ex.getMessage());
	        return null;
	    }
	}


    

    

private void closeResources() 
{
}
	public List<PatientModel> searchPatientsByName(String name) {
	    List<PatientModel> searchResults = new ArrayList<>();
	    try {
	        stmt = conn.prepareStatement("select * from patient where ptName = ?");
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
        try (PreparedStatement stmt = conn.prepareStatement("delete from patient where ptid = ?")) {
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
	        stmt = conn.prepareStatement("select * from patient where category = ?");
	        stmt.setString(1, category);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            PatientModel patient = new PatientModel(); 
	            patient.setPtid(rs.getInt("ptid"));
	            patient.setPtName(rs.getString("ptName"));
	            patient.setAge(rs.getInt("age"));
	            patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase())); 
	            patient.setContact(rs.getString("contact"));
	            patient.setAddress(rs.getString("address"));
	            patient.setOpdDate(rs.getDate("opddate"));
	            patient.setDocid(rs.getInt("docid"));
	            patient.setFess(rs.getFloat("fess"));
	            patient.setAppointmentDate(rs.getDate("appoinmentdate"));
	            patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
	            listPatient.add(patient);
	        }
	        return listPatient;
	    } catch (SQLException ex) {
	        System.out.println("Error retrieving patients by category: " + ex.getMessage());
	        return null;
	    }
	}


    public List<PatientModel> getPatientsByDate(java.util.Date date) {
        List<PatientModel> patients = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(date);
            String query = "SELECT * FROM patient WHERE opddate = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, formattedDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PatientModel patient = new PatientModel();
                patient.setPtid(rs.getInt("ptid"));
                patient.setPtName(rs.getString("ptName"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase()));
                patient.setContact(rs.getString("contact"));
                patient.setAddress(rs.getString("address"));
                patient.setOpdDate(rs.getDate("opddate"));
                patient.setDocid(rs.getInt("docid"));
                patient.setFess(rs.getFloat("fess"));
                patient.setAppointmentDate(rs.getDate("appoinmentdate"));
                patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                patients.add(patient);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving patients by date: " + ex.getMessage());
        }
        return patients;
    }


    public List<PatientModel> getPatientsByMonth(java.util.Date date) {
        List<PatientModel> patients = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Construct the SQL query to retrieve patients for the specified month
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String query = "SELECT * FROM patient WHERE YEAR(opddate) = YEAR(?) AND MONTH(opddate) = MONTH(?)";
            stmt = conn.prepareStatement(query);
            stmt.setDate(1, sqlDate);
            stmt.setDate(2, sqlDate);
            rs = stmt.executeQuery();

            // Iterate through the result set and populate PatientModel objects
            while (rs.next()) {
                PatientModel patient = new PatientModel();
                patient.setPtid(rs.getInt("ptid"));
                patient.setPtName(rs.getString("ptName"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase()));
                patient.setContact(rs.getString("contact"));
                patient.setAddress(rs.getString("address"));
                patient.setOpdDate(rs.getDate("opddate"));
                patient.setDocid(rs.getInt("docid"));
                patient.setFess(rs.getFloat("fess"));
                patient.setAppointmentDate(rs.getDate("appoinmentdate"));
                patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                patients.add(patient);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving patients for the specified month: " + ex.getMessage());
        } finally {
            // Close resources in the finally block
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return patients;
    }
    
    public List<PatientModel> getPatientsByYear(int year) {
        List<PatientModel> patients = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Construct the SQL query to retrieve patients for the specified year
            String query = "SELECT * FROM patient WHERE YEAR(opddate) = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, year);
            rs = stmt.executeQuery();

            // Iterate through the result set and populate PatientModel objects
            while (rs.next()) {
                PatientModel patient = new PatientModel();
                patient.setPtid(rs.getInt("ptid"));
                patient.setPtName(rs.getString("ptName"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase()));
                patient.setContact(rs.getString("contact"));
                patient.setAddress(rs.getString("address"));
                patient.setOpdDate(rs.getDate("opddate"));
                patient.setDocid(rs.getInt("docid"));
                patient.setFess(rs.getFloat("fess"));
                patient.setAppointmentDate(rs.getDate("appoinmentdate"));
                patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                patients.add(patient);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving patients for the specified year: " + ex.getMessage());
        } finally {
            // Close resources in the finally block
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return patients;
    }

    
    
    public List<PatientModel> getPatientsBetweenDates(java.util.Date startDate, java.util.Date endDate) {
        List<PatientModel> patients = new ArrayList<>();
        try {
            String query = "SELECT * FROM patient WHERE opddate BETWEEN ? AND ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PatientModel patient = new PatientModel();
                patient.setPtid(rs.getInt("ptid"));
                patient.setPtName(rs.getString("ptName"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase())); 
                patient.setContact(rs.getString("contact"));
                patient.setAddress(rs.getString("address"));
                patient.setOpdDate(rs.getDate("opddate"));
                patient.setDocid(rs.getInt("docid"));
                patient.setFess(rs.getFloat("fess"));
                patient.setAppointmentDate(rs.getDate("appoinmentdate"));
                patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                patients.add(patient);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving patients between dates: " + ex.getMessage());
        }
        return patients;
    }
    
    
    
    public List<PatientModel> getPatientsByDoctorId(int doctorId) {
        List<PatientModel> patients = new ArrayList<>();
        try {
            String query = "SELECT * FROM patient WHERE docid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PatientModel patient = new PatientModel();
                patient.setPtid(rs.getInt("ptid"));
                patient.setPtName(rs.getString("ptName"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(Gender.valueOf(rs.getString("Gender").toUpperCase()));
                patient.setContact(rs.getString("contact"));
                patient.setAddress(rs.getString("address"));
                patient.setOpdDate(rs.getDate("opddate"));
                patient.setDocid(rs.getInt("docid"));
                patient.setFess(rs.getFloat("fess"));
                patient.setAppointmentDate(rs.getDate("appoinmentdate"));
                patient.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                patients.add(patient);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving patients by doctor ID: " + ex.getMessage());
        }
        return patients;
    }
    
    
    
    public Map<Integer, Integer> getDoctorWisePatientCount() {
        Map<Integer, Integer> doctorWisePatientCount = new HashMap<>();
        try {
            String query = "SELECT docid, COUNT(*) AS patientCount FROM patient GROUP BY docid";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int doctorId = rs.getInt("docid");
                int patientCount = rs.getInt("patientCount");
                doctorWisePatientCount.put(doctorId, patientCount);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving doctor-wise patient count: " + ex.getMessage());
        }
        return doctorWisePatientCount;
    }
    
    
    public int getMonthWithMaxOPDPatients() {
        int month = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish connection with the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");

            // Prepare SQL query to retrieve the month with the maximum number of OPD patients
            String sql = "SELECT MONTH(opd_date) AS month, COUNT(*) AS patient_count " +
                         "FROM patients " +
                         "WHERE category = 'OPD' " +
                         "GROUP BY MONTH(opd_date) " +
                         "ORDER BY patient_count DESC " +
                         "LIMIT 1";

            // Create PreparedStatement object
            stmt = conn.prepareStatement(sql);

            // Execute the query
            rs = stmt.executeQuery();

            // Check if there are results
            if (rs.next()) {
                // Retrieve the month with the maximum count
                month = rs.getInt("month");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close ResultSet, PreparedStatement, and Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return month;
    }

}
    













package org.hospital.repository;

import java.sql.SQLException;




import java.util.*;


import org.hospital.model.Doctermodel;

public class DocterRepository extends DBConfig {
	List<Doctermodel> listdoc = new ArrayList<Doctermodel>();

	public boolean isdocterPresent(String doctername) {
		try {
			stmt = conn.prepareStatement("select * from Docter where docname=?");
			stmt.setString(1, doctername);
			rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException ex) {
			return false;
		}
		
		

	}

	public boolean isAddDocter(Doctermodel model) {
		try {

			stmt = conn.prepareStatement("insert into docter values('0',?,?,?)");
			stmt.setString(1, model.getDoctername());
			stmt.setString(2, model.getSpeciality());
			stmt.setString(3, model.getDesgination());

			int value = stmt.executeUpdate();

			if (value > 0) {
				return true;

			}

			else {
				return false;
			}

		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return false;
		}

	}

	public List<Doctermodel> getAlldocter() {
		try {
			Doctermodel model = null;
			stmt = conn.prepareStatement("select * from Docter");
			rs = stmt.executeQuery();
			while (rs.next()) {
				model = new Doctermodel();
				model.setDocid(rs.getInt(1));
				model.setDoctername(rs.getString(2));
				model.setSpeciality(rs.getString(3));
				model.setDesgination(rs.getString(4));
				listdoc.add(model);
			}
			return listdoc.size() > 0 ? listdoc : null;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;

		}

	}
	
	public Doctermodel searchDoctor(String doctorName) {
	    try {
	        stmt = conn.prepareStatement("SELECT * FROM Docter WHERE docname = ?");
	        stmt.setString(1, doctorName);
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            Doctermodel model = new Doctermodel();
	            model.setDocid(rs.getInt("docid"));
	            model.setDoctername(rs.getString("docname"));
	            model.setSpeciality(rs.getString("speciality"));
	            model.setDesgination(rs.getString("desgination"));
	            return model;
	        } else {
	            return null; // Doctor not found
	        }
	    } catch (SQLException ex) {
	        System.out.println("Error searching for doctor: " + ex.getMessage());
	        return null;
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

	public boolean deleteDoctor(int doctorId) {
	    try {
	        stmt = conn.prepareStatement("DELETE FROM Docter WHERE docid = ?");
	        stmt.setInt(1, doctorId);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException ex) {
	        System.out.println("Error deleting doctor: " + ex.getMessage());
	        return false;
	    } finally {
	        // Close resources
	        try {
	            if (stmt != null) stmt.close();
	        } catch (SQLException ex) {
	            System.out.println("Error closing resources: " + ex.getMessage());
	        }
	    }
	}

	

}

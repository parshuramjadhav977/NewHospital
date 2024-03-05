package org.hospital.repository;

import java.util.*;


import org.hospital.model.Doctermodel;

public class DocterRepository extends DBConfig {

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

	List<Doctermodel> listdoc = new ArrayList<Doctermodel>();
		
		
		public List<Doctermodel> getAlldocter() {
			try {
				Doctermodel model=null;
				stmt = conn.prepareStatement("select * from Docter");
				rs = stmt.executeQuery();
				while (rs.next()) {
					 model = new Doctermodel();
					model.setDoctername(rs.getString(1));
					model.setSpeciality(rs.getString(2));
					model.setDesgination(rs.getString(3));
					listdoc.add(model);
				}
				return listdoc.size() > 0 ? listdoc : null;
			} 
			catch (Exception ex) 
			{
				System.out.println("Error is" + ex);
				return null;

			}

		}
		
		
		
		
	}



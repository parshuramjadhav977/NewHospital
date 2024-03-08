package org.hospital.user;




import java.util.*;
import java.sql.*;


import org.hospital.model.Doctermodel;
import org.hospital.model.PatientModel;
import org.hospital.service.DocterService;
import org.hospital.service.PatientService;

import org.hospital.repository.*;

public class AddminApplicationApp {

	public static void main(String[] args)throws Exception {
		DocterService dv =new DocterService();
		 DocterRepository docRepo=new  DocterRepository();
		
	        PatientService patientService = new PatientService();
	        PatientRepository patientRepository = new PatientRepository();

		 
		 Scanner xyz = new Scanner(System.in);
		System.out.print("Enter Username for admin  ==> ");
		String uName=xyz.nextLine();
		
		System.out.print("Enter Password for admin  ==> ");
		String uPass=xyz.nextLine();
		
		
		if (uName.equals("admin") && uPass.equals("Pass1234")){
			
			
			
		
			do
			{
				
				System.out.println(" case 1: Add New Doctor /View /Search /Delete ");
				System.out.println(" case 2: Add New Patient /View/Search/Delete");
				
				System.out.println("Enter your choice");
				boolean s=true;
				int choice = xyz.nextInt();
				switch(choice)
				{
				
				case 1:
					
					while(s) {
						System.out.println("case 1 ADD new Docter");
						System.out.println("case 2 View All Docter List");
						System.out.println("Case 3 Search Docter");
						System.out.println("case 4 Delete Docter By ID");
						
						System.out.println("case: 0 exit");
						System.out.println("Enter your choice");
					
						choice=xyz.nextInt();
						switch(choice)
						{
							
						case 1:
						    xyz.nextLine(); 

							System.out.println("enter Doctername speality Degination");
							String docterName=xyz.nextLine();
							String speality=xyz.nextLine();
							String Degination=xyz.nextLine();
							
							Doctermodel model =new Doctermodel();
							model.setDoctername(docterName);
							model.setSpeciality(speality);
							model.setDesgination(Degination);
							int result =dv.addDocter(model);
							System.out.println((result == 1) ? " Docter added sucessfully.": (result == -1) ? "Docter already present" : " some problem is there...");
							break;
						
						case 2:
							
//							System.out.println("All Doctor list");
//							List<Doctermodel> list=dv.getAlldocter();
//							for(Doctermodel o:list) {
//								System.out.println(o.getDocid()+"\t"+ o.getDoctername()+"\t"+o.getSpeciality()+"\t"+o.getDesgination());
//							}
//							
							
							System.out.println("All Doctor list");
							List<Doctermodel> list = dv.getAlldocter();
							Set<Integer> seenIds = new HashSet<>(); // To keep track of seen doctor IDs
							for (Doctermodel o : list) {
							    // Check if the ID is already seen, if not, print and add to seenIds
							    if (!seenIds.contains(o.getDocid())) {
							        System.out.println(o.getDocid() + "\t" + o.getDoctername() + "\t" + o.getSpeciality() + "\t" + o.getDesgination());
							        seenIds.add(o.getDocid());
							    }
							}

							break;
							
						case 3:
						    xyz.nextLine(); // Consume newline
						    System.out.println("Enter the name of the doctor to search:");
						    String searchName = xyz.nextLine();
						    Doctermodel searchedDoctor = docRepo.searchDoctor(searchName);
						    if (searchedDoctor != null) {
						        System.out.println("Doctor found:");
						        System.out.println("Name: " + searchedDoctor.getDoctername());

						        System.out.println("ID: " + searchedDoctor.getDocid());
						        System.out.println("Speciality: " + searchedDoctor.getSpeciality());
						        System.out.println("Designation: " + searchedDoctor.getDesgination());
						        
						        
						    } else {
						        System.out.println("Doctor not found.");
						    }
						    break;

						case 4:
						    System.out.println("Enter the ID of the doctor to delete:");
						    int deleteId = xyz.nextInt();
						    boolean isDeleted = dv.deleteDoctor(deleteId);
						    if (isDeleted) {
						        System.out.println("Doctor deleted successfully.");
						    } else {
						        System.out.println("Failed to delete doctor. Doctor may not exist.");
						    }
							
							

						    break;
						case 0:
							s=false;
							break;

						default:
						System.out.println("Wrong choice");
						}
					}
					
				
				
				case 2:

				while(s)
				{
					System.out.println("case 1 ADD new patient");
					System.out.println("case 2 View All patient List");
					System.out.println("Case 3 Search patient by name");
					System.out.println("case 4 Delete patient By ID");
					System.out.println("case 0 Exist ");
					
					System.out.println("Enter your choice");
					choice=xyz.nextInt();
			        PatientModel patient = new PatientModel();

					switch(choice)
					{ 


					case 1:
						
						xyz.nextLine();
				        System.out.println("Enter patient details:");

				        System.out.print("Patient Name: ");
				        String ptName = xyz.nextLine();

				        System.out.print("Age: ");
				        int age = xyz.nextInt();
				        xyz.nextLine(); // Consume newline

				        System.out.print("Gender: ");
				        String gender = xyz.nextLine();

				        System.out.print("Contact: ");
				        String contact = xyz.nextLine();

				        System.out.print("Address: ");
				        String address = xyz.nextLine();

				        System.out.print("OPD Date (YYYY-MM-DD): ");
				        String opdDateStr = xyz.nextLine();
				        java.sql.Date opdDate = java.sql.Date.valueOf(opdDateStr);

				        System.out.print("Doctor ID: ");
				        int docid = xyz.nextInt();

				        System.out.print("Fees: ");
				        int fees = xyz.nextInt();
				        xyz.nextLine();

				        System.out.print("Appointment Date (YYYY-MM-DD): ");
				        String appointmentDateStr = xyz.nextLine();
				        java.sql.Date appointmentDate = java.sql.Date.valueOf(appointmentDateStr);
				       

				        patient.setPtName(ptName);
				        patient.setAge(age);
				        patient.setGender(gender);
				        patient.setContact(contact);
				        patient.setAddress(address);
				        patient.setOpdDate(opdDate);
				        patient.setDocid(docid);
				        patient.setFees(fees);
				        patient.setAppointmentDate(appointmentDate);

				        boolean isSuccess = patientService.addPatient(patient);
				        if (isSuccess) {
				            System.out.println("Patient added successfully.");
				        } else {
				            System.out.println("Failed to add patient.");
				        }

					
					break;
					case 2:
					    System.out.println("All Patients List:");
					    List<PatientModel> patien = patientService.getAllPatients();
					    if (patien != null && !patien.isEmpty()) {
					        for (PatientModel patient1 : patien) {
					            System.out.println("Patient ID: " + patient1.getPtid());
					            System.out.println("Name: " + patient1.getPtName());
					            System.out.println("Age: " + patient1.getAge());
					            System.out.println("Gender: " + patient1.getGender());
					            System.out.println("Contact: " + patient1.getContact());
					            System.out.println("Address: " + patient1.getAddress());
					            System.out.println("OPD Date: " + patient1.getOpdDate());
					            System.out.println("Doctor ID: " + patient1.getDocid());
					            System.out.println("Fees: " + patient1.getFees());
					            System.out.println("Appointment Date: " + patient1.getAppointmentDate());
					            System.out.println("------------------------------------");
					        }
					    } else {
					        System.out.println("No patients found.");
					    }
					    break;
					    
					case 3:
					    xyz.nextLine(); // Consume newline
					    System.out.println("Enter the name of the patient to search:");
					    String searchName = xyz.nextLine(); // Read the name of the patient to search for
					    List<PatientModel> searchResults = patientService.searchPatientsByName(searchName); // Call the service method to search for patients by name
					    if (searchResults != null && !searchResults.isEmpty()) { // If search results are not empty
					        System.out.println("Search Results:");
					        for (PatientModel patient1 : searchResults) { // Iterate over each patient in the search results
					            // Print details of each patient
					            System.out.println("Patient ID: " + patient1.getPtid());
					            System.out.println("Name: " + patient1.getPtName());
					            System.out.println("Age: " + patient1.getAge());
					            System.out.println("Gender: " + patient1.getGender());
					            System.out.println("Contact: " + patient1.getContact());
					            System.out.println("Address: " + patient1.getAddress());
					            System.out.println("OPD Date: " + patient1.getOpdDate());
					            System.out.println("Doctor ID: " + patient1.getDocid());
					            System.out.println("Fees: " + patient1.getFees());
					            System.out.println("Appointment Date: " + patient1.getAppointmentDate());
					            System.out.println("------------------------------------");
					            
					            System.out.println("patients found ");
					        }
					    } else {
					        System.out.println("No patients found with the given name."); // Print message if no patients found
					    }
					    break;


					    
		
					case 4:
						
						xyz.nextLine();

					    System.out.print("Enter patient ID to delete: ");
					    int deleteId = xyz.nextInt();
					    boolean isDeleted = patientService.deletePatientById(deleteId);
					    if (isDeleted) {
					        System.out.println("Patient with ID " + deleteId + " deleted successfully.");
					    } else {
					        System.out.println("Failed to delete patient with ID " + deleteId + ".");
					    }
					    break;
						
					    
					   
					case 0:
						s=false;
						break;
					default:
					}
				}
				
				
					
			
					
					default:
						System.out.println("worng choice");
			
				}
				
			}
			while(true);	
	}
			else {
			System.out.println("Credential do not match !!! ");
		}
		
		
		

	}

}

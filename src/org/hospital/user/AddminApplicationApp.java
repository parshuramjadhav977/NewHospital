package org.hospital.user;

import java.util.*;
import java.sql.*;
import org.hospital.model.Doctermodel;
import org.hospital.model.PatientModel;
import org.hospital.model.PatientModel.Category;
import org.hospital.model.PatientModel.Gender;
import org.hospital.model.PrescriptionModel;
import org.hospital.model.AdminModel;
import org.hospital.model.AllocatePatientModel;
import org.hospital.service.DocterService;
import org.hospital.service.PatientService;
import org.hospital.service.PrescriptionService;
import org.hospital.service.AllocatePatientService;
import org.hospital.service.AdminService;
import org.hospital.repository.*;

public class AddminApplicationApp {

		public static void main(String[] args) throws Exception {
			DocterService dv = new DocterService();
			DocterRepository docRepo = new DocterRepository();
			AllocatePatientService allocatePatientService = new AllocatePatientService();

		PatientService patientService = new PatientService();
		PrescriptionService prescriptionService = new PrescriptionService();

		PatientRepository patientRepository = new PatientRepository();

		Scanner xyz = new Scanner(System.in);
		System.out.print("Enter Username for admin  ==> ");
		String uName = xyz.nextLine();

		System.out.print("Enter Password for admin  ==> ");
		String uPass = xyz.nextLine();

		AdminRepository adminRepo = new AdminRepository();

		if (adminRepo.validateAdminCredentials(uName, uPass)) {

			do {

				System.out.println("case 1:Add New Doctor /View /Search /Delete ");
				System.out.println("case 2:Add OPD Patient /View/Search/Delete");
				System.out.println("case 3:view Patient Category like as OPD");
				System.out.println("case 4:view Patient Category like as IPD");
				System.out.println("case 5:Allocate Doctor to patient for treatment ");
				System.out.println("case 6:Allocate prescription to patient ");

				System.out.println("Enter your choice");
				boolean s = true;
				int choice = xyz.nextInt();
				switch (choice) {

				case 1:

					while (s) {
						System.out.println("case 1 ADD new Docter");
						System.out.println("case 2 View All Docter List");
						System.out.println("Case 3 Search Docter");
						System.out.println("case 4 Delete Docter By ID");
						System.out.println("case: 0 exit");
						System.out.println("Enter your choice");

						choice = xyz.nextInt();
						
						switch (choice) {

						case 1:
							xyz.nextLine();

							System.out.println("enter Doctername speality Degination");
							String docterName = xyz.nextLine();
							String speality = xyz.nextLine();
							String Degination = xyz.nextLine();

							Doctermodel model = new Doctermodel();
							model.setDoctername(docterName);
							model.setSpeciality(speality);
							model.setDesgination(Degination);
							int result = dv.addDocter(model);
							System.out.println((result == 1) ? " Docter added sucessfully."
									: (result == -1) ? "Docter already present" : " some problem is there...");
							break;

						case 2:

							System.out.println("All Doctor list");
							List<Doctermodel> list = dv.getAlldocter();
							Set<Integer> seenIds = new HashSet<>();
							for (Doctermodel o : list) {
								if (!seenIds.contains(o.getDocid())) {
									System.out.println(o.getDocid() + "\t" + o.getDoctername() + "\t"
											+ o.getSpeciality() + "\t" + o.getDesgination());
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
							s = false;
							break;

						default:
							System.out.println("Wrong choice");
						}
					}

				case 2:

					while (s) {
						System.out.println("case 1 ADD new OPD patient");
						System.out.println("case 2 View All OPD patient List");
						System.out.println("Case 3 Search patient by name");
						System.out.println("case 4 Delete patient By ID");
						System.out.println("case 0 Exist ");

						System.out.println("Enter your choice");
						choice = xyz.nextInt();
						PatientModel patient = new PatientModel();

						switch (choice) {

						case 1:

							xyz.nextLine();

							System.out.println("Enter patient details:");

							System.out.print("Patient Name: ");
							patient.setPtName(xyz.nextLine());

							System.out.print("Age: ");
							patient.setAge(xyz.nextInt());

							System.out.print("Gender (Male/Female/Other): ");
							xyz.nextLine(); // Consume newline character
							patient.setGender(Gender.valueOf(xyz.nextLine().toUpperCase()));

							System.out.print("Contact: ");
							patient.setContact(xyz.nextLine());

							System.out.print("Address: ");
							patient.setAddress(xyz.nextLine());

							System.out.print("OPD Date (YYYY-MM-DD): ");
							patient.setOpdDate(java.sql.Date.valueOf(xyz.nextLine()));

							System.out.print("Doctor ID: ");
							patient.setDocid(xyz.nextInt());

							System.out.print("Fees: ");
							patient.setFess(xyz.nextFloat());

							System.out.print("Appointment Date (YYYY-MM-DD): ");
							patient.setAppointmentDate(java.sql.Date.valueOf(xyz.next()));

							System.out.print("Category (OPD/IPD): ");
							xyz.nextLine(); // Consume newline character
							patient.setCategory(Category.valueOf(xyz.nextLine().toUpperCase()));

							boolean isSuccess = patientService.addPatient(patient);
							if (isSuccess) {
								System.out.println("Patient added successfully.");
							} else {
								System.out.println("Failed to add patient.");
							}

							break;

						case 2:
							System.out.println("All Patients List:");
							List<PatientModel> patients = patientService.getAllPatients();
							if (patients != null && !patients.isEmpty()) {
								Set<Integer> seenIds = new HashSet<>(); // To keep track of seen patient IDs
								for (PatientModel patient1 : patients) {
									// Check if the ID is already seen, if not, print and add to seenIds
									if (!seenIds.contains(patient1.getPtid())) {
										System.out.println(patient1); // Utilize overridden toString() method
										System.out.println("------------------------------------");
										seenIds.add(patient1.getPtid());
									}
								}
							} else {
								System.out.println("No patients found.");
							}
							break;

						case 3:
							xyz.nextLine(); // Consume newline
							System.out.println("Enter the name of the patient to search:");
							String searchName = xyz.nextLine(); // Read the name of the patient to search for
							List<PatientModel> searchResults = patientService.searchPatientsByName(searchName);
							if (searchResults != null && !searchResults.isEmpty()) {
								System.out.println("Search Results:");
								for (PatientModel patient1 : searchResults) {
									// Print patient details
									System.out.println("Patient ID: " + patient1.getPtid());
									System.out.println("Name: " + patient1.getPtName());
									System.out.println("Age: " + patient1.getAge());
									System.out.println("Gender: " + patient1.getGender());
									System.out.println("Contact: " + patient1.getContact());
									System.out.println("Address: " + patient1.getAddress());
									System.out.println("OPD Date: " + patient1.getOpdDate());
									System.out.println("Doctor ID: " + patient1.getDocid());
									System.out.println("Fees: " + patient1.getFess());
									System.out.println("Appointment Date: " + patient1.getAppointmentDate());
									System.out.println("Category: " + patient1.getCategory());
									System.out.println("------------------------------------");
								}
							} else {
								System.out.println("No patients found with the given name.");
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
							s = false;
							break;
						default:
						}
					}

				case 3:
					List<PatientModel> opdPatients = patientService.getOPDPatients();

					System.out.println("OPD Patients:");
					if (opdPatients != null && !opdPatients.isEmpty()) {
						for (PatientModel patient1 : opdPatients) {
							System.out.println(patient1);
							System.out.println("------------------------------------");
						}
					} else {
						System.out.println("No OPD patients found.");
					}
					break;

				case 4:
					List<PatientModel> ipdPatients = patientService.getIPDPatients();

					System.out.println("IPD Patients:");
					if (ipdPatients != null && !ipdPatients.isEmpty()) {
						for (PatientModel patient1 : ipdPatients) {
							System.out.println(patient1);
							System.out.println("------------------------------------");
						}
					} else {
						System.out.println("No IPD patients found.");
					}
					break;

				case 5:
					List<AllocatePatientModel> allocatedPatients = allocatePatientService.getAllAllocatedPatients();
					if (allocatedPatients != null && !allocatedPatients.isEmpty()) {
						System.out.println("Allocated Patients:");
						for (AllocatePatientModel allocatedPatient : allocatedPatients) {
							System.out.println(allocatedPatient);
						}
					} else {
						System.out.println("No patients are currently allocated to doctors.");
					}

					break;

				case 6:
				

					// Allocate prescription to patient
					PrescriptionModel prescription = new PrescriptionModel();

					System.out.println("Allocate Prescription to Patient:");
					xyz.nextLine(); // Consume newline
					System.out.print("Enter Prescription Details: ");
					String prescriptionDetails = xyz.nextLine();
					System.out.print("Enter Patient ID: ");
					int patientId = xyz.nextInt();
					System.out.print("Enter Doctor ID: ");
					int doctorId = xyz.nextInt();

					// Call the service to allocate the prescription
					prescriptionService.allocatePrescriptionToPatient(prescriptionDetails, patientId,
							doctorId);
					break;

				default:
					System.out.println("worng choice");

				}

			}

			while (true);
		} else {
			System.out.println("Credential do not match !!! ");
		}

	}

}

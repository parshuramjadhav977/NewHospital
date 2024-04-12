	package org.hospital.user;
	
	import java.text.ParseException;
	
	
	import java.text.SimpleDateFormat;
	import java.util.*;
	import org.hospital.model.Doctermodel;
	import org.hospital.model.PatientModel;
	import org.hospital.model.PatientModel.Category;
	import org.hospital.model.PatientModel.Gender;
	import org.hospital.model.PrescriptionModel;
	import org.hospital.model.AllocatePatientModel;
	import org.hospital.service.DocterService;
	import org.hospital.service.PatientService;
	import org.hospital.service.PrescriptionService;
	import org.hospital.service.AllocatePatientService;
	import org.hospital.repository.*;
	
	public class AddminApplicationApp {
	
		public static void main(String[] args) throws Exception {
			DocterService dv = new DocterService();
			DocterRepository docRepo = new DocterRepository();
			AllocatePatientService allocatePatientService = new AllocatePatientService();
			PatientRepository patientRepository = new PatientRepository();
	
			PatientService patientService = new PatientService();
			PrescriptionService prescriptionService = new PrescriptionService();
	
			Scanner xyz = new Scanner(System.in);
			System.out.print("Enter Username for admin =");
			String uName = xyz.nextLine();
	
			System.out.print("Enter Password for admin  **** ");
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
					System.out.println("case 7. View All patients Date wise ");
					System.out.println("case 8. View patients Month wise");
					System.out.println("case 9. View patients Year wise ");
					System.out.println("case 10. View Patients Between specified date ");
					System.out.println("case 11. Show Doctor wise patient list ");
					System.out.println("case 12. Show Doctor wise patient count ");
					System.out.println("case 13. show the month in which maximum patient admitted in hospital in OPD section");
					System.out.println("case 14. view the patient history of patients ");
	
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
	
								System.out.println("+-------+----------------------------+------------+-------------+");
								System.out.println("| docid | docname                    | speciality | desgination |");
								System.out.println("+-------+----------------------------+------------+-------------+");
								List<Doctermodel> list = dv.getAlldocter();
								for (Doctermodel o : list) {
									System.out.printf("| %-5d | %-26s | %-10s | %-11s |\n", o.getDocid(), o.getDoctername(),
											o.getSpeciality(), o.getDesgination());
								}
								System.out.println("+-------+----------------------------+------------+-------------+");
	
								break;
	
							case 3:
								xyz.nextLine();
								System.out.println("Enter the name of the doctor to search:");
								String searchName = xyz.nextLine();
								Doctermodel searchedDoctor = docRepo.searchDoctor(searchName);
								if (searchedDoctor != null) {
									System.out.println("Doctor found:");
									System.out.println("+-------+----------------------------+------------+-------------+");
									System.out.println("| ID    | Name                       | Speciality | Designation |");
									System.out.println("+-------+----------------------------+------------+-------------+");
									System.out.printf("| %-5d | %-26s | %-10s | %-11s |\n", searchedDoctor.getDocid(),
											searchedDoctor.getDoctername(), searchedDoctor.getSpeciality(),
											searchedDoctor.getDesgination());
									System.out.println("+-------+----------------------------+------------+-------------+");
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
							System.out.println("case 1 ADD new  patient");
							System.out.println("case 2 View All  patient List");
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
								System.out.println(
										"+------+----------------+-----+--------+-------------+----------------+------------+-------+------+----------------+----------+");
								System.out.println(
										"| ptid | ptName         | age | Gender | contact     | address        | opddate    | docid | fess | appoinmentdate | category |");
								System.out.println(
										"+------+----------------+-----+--------+-------------+----------------+------------+-------+------+----------------+----------+");
								List<PatientModel> patients = patientService.getAllPatients();
								if (patients != null && !patients.isEmpty()) {
									for (PatientModel patient1 : patients) {
										System.out.printf(
												"| %-4d | %-14s | %-3d | %-6s | %-11s | %-14s | %-10s | %-5d | %-4.2f | %-14s | %-8s |\n",
												patient1.getPtid(), patient1.getPtName(), patient1.getAge(),
												patient1.getGender(), patient1.getContact(), patient1.getAddress(),
												patient1.getOpdDate(), patient1.getDocid(), patient1.getFess(),
												patient1.getAppointmentDate(), patient1.getCategory());
									}
									System.out.println(
											"+------+----------------+-----+--------+-------------+----------------+------------+-------+------+----------------+----------+");
								} else {
									System.out.println("No patients found.");
								}
	
								break;
	
							case 3:
								xyz.nextLine();
								System.out.println("Enter the name of the patient to search:");
								String searchName = xyz.nextLine();
								List<PatientModel> searchResults = patientService.searchPatientsByName(searchName);
								if (searchResults != null && !searchResults.isEmpty()) {
									System.out.println(
											"+------+----------------+-----+--------+-------------+----------------+------------+-------+------+----------------+----------+");
									System.out.println(
											"| ptid | ptName         | age | Gender | contact     | address        | opddate    | docid | fess | appoinmentdate | category |");
									System.out.println(
											"+------+----------------+-----+--------+-------------+----------------+------------+-------+------+----------------+----------+");
									for (PatientModel patient1 : searchResults) {
										// Print patient details
										System.out.printf(
												"| %-4s | %-14s | %-3s | %-6s | %-11s | %-14s | %-10s | %-5s | %-4s | %-14s | %-8s |%n",
												patient1.getPtid(), patient1.getPtName(), patient1.getAge(),
												patient1.getGender(), patient1.getContact(), patient1.getAddress(),
												patient1.getOpdDate(), patient1.getDocid(), patient1.getFess(),
												patient1.getAppointmentDate(), patient1.getCategory());
									}
									System.out.println(
											"+------+----------------+-----+--------+-------------+----------------+------------+-------+------+----------------+----------+");
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
						break;
					case 3:
						List<PatientModel> opdPatients = patientService.getOPDPatients();
	
						System.out.println("OPD Patients:");
						if (opdPatients != null && !opdPatients.isEmpty()) {
							// Print table headers
							System.out.printf("%-15s %-20s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %-10s %-15s\n",
									"Patient ID", "Name", "Age", "Gender", "Contact", "Address", "OPD Date", "Doctor ID",
									"Fee", "Appointment Date", "Category");
							// Print separator line
							for (PatientModel patient : opdPatients) {
								// Print patient details in formatted table rows
								System.out.printf("%-15s %-20s %-10d %-15s %-15s %-15s %-10s %-15s %-10.2f %-10s %10s\n",
										patient.getPtid(), patient.getPtName(), patient.getAge(), patient.getGender(),
										patient.getContact(), patient.getAddress(), patient.getOpdDate(),
										patient.getDocid(), patient.getFess(), patient.getAppointmentDate(),
										patient.getCategory());
							}
						} else {
							System.out.println("No OPD patients found.");
						}
	
						break;
					case 4:
						List<PatientModel> ipdPatients = patientService.getIPDPatients();
	
						System.out.println("IPD Patients:");
						if (ipdPatients != null && !ipdPatients.isEmpty()) {
							// Print table headers
							System.out.printf("%-15s %-20s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %-10s %-15s\n",
									"Patient ID", "Name", "Age", "Gender", "Contact", "Address", "IPD Date", "Doctor ID",
									"Fee", "Appointment Date", "Category");
							// Print separator line
							System.out.println(
									"-------------------------------------------------------------------------------------------------------------------------");
							for (PatientModel patient : ipdPatients) {
								// Print patient details in formatted table rows
								System.out.printf("%-15s %-20s %-10d %-15s %-15s %-15s %-10s %-15s %-10.2f %-10s %-10s \n",
										patient.getPtid(), patient.getPtName(), patient.getAge(), patient.getGender(),
										patient.getContact(), patient.getAddress(), patient.getOpdDate(),
										patient.getDocid(), patient.getFess(), patient.getAppointmentDate(),
										patient.getCategory());
							}
						} else {
							System.out.println("No IPD patients found.");
						}
	
						break;
	
					case 5:
						List<AllocatePatientModel> allocatedPatients = allocatePatientService.getAllAllocatedPatients();
	
						System.out.println("Allocated Patients:");
						if (allocatedPatients != null && !allocatedPatients.isEmpty()) {
							// Print table headers
							System.out.printf("%-15s %-20s %-10s %-20s\n", "Patient ID", "Patient Name", "Doctor ID",
									"Doctor Name");
							// Print separator line
							System.out.println("------------------------------------------------------");
							for (AllocatePatientModel allocatedPatient : allocatedPatients) {
								// Print allocated patient details in formatted table rows
								System.out.printf("%-15s %-20s %-10s %-20s\n", allocatedPatient.getPtid(),
										allocatedPatient.getPtName(), allocatedPatient.getDocid(),
										allocatedPatient.getDoctorName());
							}
						} else {
							System.out.println("No patients are currently allocated to doctors.");
						}
	
						break;
	
					case 6:
	
						PrescriptionModel prescription = new PrescriptionModel();
	
						System.out.println("Allocate Prescription to Patient:");
						xyz.nextLine();
						System.out.print("Enter Prescription Details: ");
						String prescriptionDetails = xyz.nextLine();
						System.out.print("Enter Patient ID: ");
						int patientId = xyz.nextInt();
						System.out.print("Enter Doctor ID: ");
						int doctorId = xyz.nextInt();
						prescriptionService.allocatePrescriptionToPatient(prescriptionDetails, patientId, doctorId);
						break;
	
					case 7:
						// Logic to view patients based on a specific date
						System.out.println("Enter the date (YYYY-MM-DD): ");
						xyz.nextLine(); // Consume newline
						String dateInput = xyz.nextLine();
						try {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date = dateFormat.parse(dateInput);
	
							// Retrieve patients for the specified date
							List<PatientModel> patientsByDate = patientService.getPatientsByDate(date);
	
							if (patientsByDate != null && !patientsByDate.isEmpty()) {
								// Print patients for the specified date in table format
								System.out.println("Patients for " + dateFormat.format(date) + ":");
								System.out.printf("%-15s %-20s %-10s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %-10s\n",
										"Patient ID", "Name", "Age", "Gender", "Contact", "Address", "OPD Date",
										"Doctor ID", "Fee", "Appointment Date", "Category");
								for (PatientModel patient : patientsByDate) {
									System.out.printf("%-15s %-20s %-10s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %-10s\n",
											patient.getPtid(), patient.getPtName(), patient.getAge(), patient.getGender(),
											patient.getContact(), patient.getAddress(),
											dateFormat.format(patient.getOpdDate()), patient.getDocid(), patient.getFess(),
											dateFormat.format(patient.getAppointmentDate()), patient.getCategory());
								}
							} else {
								System.out.println("No patients found for the specified date.");
							}
						} catch (ParseException e) {
							System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
						}
	
						break;
	
					case 8:
						System.out.println("Enter the month (MM): ");
						int month = xyz.nextInt(); // Get the month input from the user
						System.out.println("Enter the year (YYYY): ");
						int year = xyz.nextInt(); // Get the year input from the user
						try {
							// Create a Calendar instance and set it to the specified month and year
							Calendar calendar = Calendar.getInstance();
							calendar.set(Calendar.YEAR, year);
							calendar.set(Calendar.MONTH, month - 1); // Month in Calendar starts from 0 (January)
							calendar.set(Calendar.DAY_OF_MONTH, 1); // Set day to the first day of the month
	
							// Retrieve patients for the specified month and year
							List<PatientModel> patientsByMonth = patientService.getPatientsByMonth(calendar.getTime());
	
							// Check if patients are found for the specified month and year
							if (patientsByMonth != null && !patientsByMonth.isEmpty()) {
								// Print patients for the specified month and year in table format
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								System.out.println("Patients for " + dateFormat.format(calendar.getTime()) + ":");
								System.out.printf("%-15s %-20s %-10s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %-10s\n",
										"Patient ID", "Name", "Age", "Gender", "Contact", "Address", "OPD Date",
										"Doctor ID", "Fee", "Appointment Date", "Category");
								for (PatientModel patient : patientsByMonth) {
									System.out.printf("%-15s %-20s %-10s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %10s\n",
											patient.getPtid(), patient.getPtName(), patient.getAge(), patient.getGender(),
											patient.getContact(), patient.getAddress(),
											dateFormat.format(patient.getOpdDate()), patient.getDocid(), patient.getFess(),
											dateFormat.format(patient.getAppointmentDate()), patient.getCategory());
								}
							} else {
								// Print a message if no patients are found for the specified month and year
								System.out.println("No patients found for the specified month and year.");
							}
						} catch (Exception e) {
							// Handle exceptions for invalid month or year input
							System.out.println("Invalid month or year input.");
						}
	
						break;
	
					// Inside the main switch statement...
	
					// Inside the main switch statement...
	
					case 9:
						System.out.println("Enter the year:");
						year = xyz.nextInt();
						List<PatientModel> patientsByYear = patientService.getPatientsByYear(year);
						if (!patientsByYear.isEmpty()) {
							// Display patients for the specified year in table format
							System.out.println("Patients for the year " + year + ":");
							System.out.printf("%-10s %-20s %-10s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %-10s\n",
									"Patient ID", "Name", "Age", "Gender", "Contact", "Address", "OPD Date", "Doctor ID",
									"Fee", "Appointment Date", "Category");
							for (PatientModel patient : patientsByYear) {
								System.out.printf("%-10s %-20s %-10s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %10s\n",
										patient.getPtid(), patient.getPtName(), patient.getAge(), patient.getGender(),
										patient.getContact(), patient.getAddress(), patient.getOpdDate(),
										patient.getDocid(), patient.getFess(), patient.getAppointmentDate(),
										patient.getCategory());
							}
						} else {
							System.out.println("No patients found for the year " + year);
						}
						break;
	
					case 10:
						System.out.println("Enter the start date (YYYY-MM-DD):");
						String startDateStr = xyz.next();
						System.out.println("Enter the end date (YYYY-MM-DD):");
						String endDateStr = xyz.next();
	
						try {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date startDate = dateFormat.parse(startDateStr);
							Date endDate = dateFormat.parse(endDateStr);
	
							List<PatientModel> patientsBetweenDates = patientService.getPatientsBetweenDates(startDate,
									endDate);
							if (!patientsBetweenDates.isEmpty()) {
								System.out.println("Patients between " + startDateStr + " and " + endDateStr + ":");
	
								// Print table header
								System.out.printf("%-5s %-20s %-10s %-10s %-15s %-20s %-15s %-10s %-10s %-20s %-10s\n",
										"ID", "Name", "Age", "Gender", "Contact", "Address", "OPD Date", "Doctor ID",
										"Fees", "Appointment Date", "Category");
								System.out.println(
										"-----------------------------------------------------------------------------------------------------------------------------");
	
								// Print patient data
								for (PatientModel patient : patientsBetweenDates) {
									System.out.printf(
											"%-5d %-20s %-10d %-10s %-15s %-20s %-15s %-10d %-10.2f %-20s %-10s\n",
											patient.getPtid(), patient.getPtName(), patient.getAge(), patient.getGender(),
											patient.getContact(), patient.getAddress(), patient.getOpdDate(),
											patient.getDocid(), patient.getFess(), patient.getAppointmentDate(),
											patient.getCategory());
								}
							} else {
								System.out.println("No patients found between " + startDateStr + " and " + endDateStr);
							}
						} catch (ParseException e) {
							System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
						}
						break;
	
					case 11:
						List<Doctermodel> allDoctors = dv.getAlldocter();
						if (allDoctors != null && !allDoctors.isEmpty()) {
							for (Doctermodel doctor : allDoctors) {
								System.out.println("Doctor: " + doctor.getDoctername());
								List<PatientModel> doctorPatients = patientService.getPatientsByDoctorId(doctor.getDocid());
								if (doctorPatients != null && !doctorPatients.isEmpty()) {
									// Print table headers
									System.out.printf("%-15s %-20s %-10s %-15s %-15s %-15s %-10s %-15s %-10s %-10s %-15s\n",
											"Patient ID", "Name", "Age", "Gender", "Contact", "Address", "OPD Date",
											"Doctor ID", "Fee", "Appointment Date", "Category");
									// Print separator line
									System.out.println(
											"-------------------------------------------------------------------------------------------------------------------------");
									// Print patient details
									for (PatientModel patient : doctorPatients) {
										System.out.printf(
												"%-15s %-20s %-10d %-15s %-15s %-15s %-10s %-15s %-10.2f %-10s %-10s \n",
												patient.getPtid(), patient.getPtName(), patient.getAge(),
												patient.getGender(), patient.getContact(), patient.getAddress(),
												patient.getOpdDate(), patient.getDocid(), patient.getFess(),
												patient.getAppointmentDate(), patient.getCategory());
									}
								} else {
									System.out.println("No patients assigned to this doctor.");
								}
								System.out.println(); // Add a newline between each doctor's patients
							}
						} else {
							System.out.println("No doctors found.");
						}
						break;
	
					case 12:
						System.out.println("Doctor-wise Patient Count:");
						Map<Integer, Integer> doctorWisePatientCount = patientService.getDoctorWisePatientCount();
						if (!doctorWisePatientCount.isEmpty()) {
							System.out.printf("%-10s %-20s\n", "Doctor ID", "Patient Count");
							System.out.println("-----------------------------");
							for (Map.Entry<Integer, Integer> entry : doctorWisePatientCount.entrySet()) {
								System.out.printf("%-10d %-20d\n", entry.getKey(), entry.getValue());
							}
						} else {
							System.out.println("No patients found for any doctor.");
						}
						break;
	
					case 13:
						int monthWithMaxOPDPatients = patientService.getMonthWithMaxOPDPatients();
						System.out.println("Month with maximum OPD patients: " + monthWithMaxOPDPatients);
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

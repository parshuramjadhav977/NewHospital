package org.hospital.service;

import java.util.List;




import org.hospital.model.*;
import org.hospital.repository.*;

public class PatientService {
    private PatientRepository patientRepository;
	private PatientModel patientModel;

    public PatientService() {
        this.patientRepository = new PatientRepository();
    }

    public boolean addPatient(PatientModel patient) {
        return patientRepository.addPatient(patient);
    }

    public List<PatientModel> getAllPatients() {
        return patientRepository.getAllPatients();
    }
   
    
    public List<PatientModel> searchPatientsByName(String name) {
        return patientRepository.searchPatientsByName(name);
    }


    public boolean deletePatientById(int patientId) {
        return patientRepository.deletePatientById(patientId);
    }

    public List<PatientModel> getOPDPatients() {
        return getPatientsByCategory("opd");
    }

    public List<PatientModel> getIPDPatients() {
        return getPatientsByCategory("ipd");
    }

    public List<PatientModel> getPatientsByCategory(String category) {
        return patientRepository.getPatientsByCategory(category);
    }
    
   


    
}


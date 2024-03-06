package org.hospital.service;

import java.util.List;


import org.hospital.model.*;
import org.hospital.repository.*;

public class PatientService {
    private PatientRepository patientRepository;

    public PatientService() {
        this.patientRepository = new PatientRepository();
    }

    public boolean addPatient(PatientModel patient) {
        return patientRepository.addPatient(patient);
    }

    public List<PatientModel> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    
}


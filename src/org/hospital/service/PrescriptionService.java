package org.hospital.service;

import org.hospital.model.PrescriptionModel;

import org.hospital.repository.PrescriptionRepository;

public class PrescriptionService {
    private PrescriptionRepository prescriptionRepository;

    public PrescriptionService() {
        this.prescriptionRepository = new PrescriptionRepository();
    }

    public void allocatePrescriptionToPatient(PrescriptionModel prescription) {
        prescriptionRepository.allocatePrescriptionToPatient(prescription);
    }
    
    
    public void allocatePrescriptionToPatient( String prescriptionDetails, int patientId, int doctorId) {
        PrescriptionModel prescription = new PrescriptionModel();
// prescription.setPrescriptionId(prescriptionId);
        prescription.setPrescriptionDetails(prescriptionDetails);
        prescription.setPtid(patientId);
        prescription.setDocid(doctorId);
        prescriptionRepository.allocatePrescriptionToPatient(prescription);
    }

    
    
}


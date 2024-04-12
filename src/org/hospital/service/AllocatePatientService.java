package org.hospital.service;

import java.util.List;
import org.hospital.model.AllocatePatientModel;
import org.hospital.repository.AllocatePatientRepository;

public class AllocatePatientService 
{
	private AllocatePatientRepository allocatePatientRepository;

    public AllocatePatientService() {
        this.allocatePatientRepository = new AllocatePatientRepository();
    }

    public List<AllocatePatientModel> getAllAllocatedPatients() {
        return allocatePatientRepository.getAllAllocatedPatients();
    }
   

	

    
}


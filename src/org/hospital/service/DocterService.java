package org.hospital.service;

import java.util.List;

import org.hospital.model.Doctermodel;

import org.hospital.repository.DocterRepository;

public class DocterService 
{
	DocterRepository docRepo=new DocterRepository();
	
	public int addDocter(Doctermodel model)
	{
		return (docRepo.isdocterPresent(model.getDoctername()))?-1: docRepo.isAddDocter(model)?1:0;
		
	}



	public List<Doctermodel> getAlldocter()
	{
		return docRepo.getAlldocter();

	}
	
	public Doctermodel searchDoctor(String doctorName) {
        return docRepo.searchDoctor(doctorName);
    }

	public boolean deleteDoctor(int doctorId) {
	    return docRepo.deleteDoctor(doctorId);
	}

	

	
}

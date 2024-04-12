package org.hospital.service;

import java.util.List;

import org.hospital.model.*;
import org.hospital.repository.*;
import java.util.*; //


public class PatientService 
{
    private PatientRepository patientRepository;
	private PatientModel patientModel;

    public PatientService()
    {
        this.patientRepository = new PatientRepository();
    }
   

    public boolean addPatient(PatientModel patient)
    {
        return patientRepository.addPatient(patient);
    }

    public List<PatientModel> getAllPatients() 
    {
        return patientRepository.getAllPatients();
    }
   
    
    public List<PatientModel> searchPatientsByName(String name) 
    {
        return patientRepository.searchPatientsByName(name);
    }


    public boolean deletePatientById(int patientId) 
    {
        return patientRepository.deletePatientById(patientId);
    }

    public List<PatientModel> getOPDPatients() 
    {
        return getPatientsByCategory("opd");
    }

    public List<PatientModel> getIPDPatients()
    {
        return getPatientsByCategory("ipd");
    }

    public List<PatientModel> getPatientsByCategory(String category) 
    {
        return patientRepository.getPatientsByCategory(category);
    }
    
    public List<PatientModel> getPatientsByDate(Date date) {
        return patientRepository.getPatientsByDate(date);
    }


    public List<PatientModel> getPatientsByMonth(Date date) {
        return patientRepository.getPatientsByMonth(date);
    }
    public List<PatientModel> getPatientsByYear(int year) {
        return patientRepository.getPatientsByYear(year);
    }


    public List<PatientModel> getPatientsBetweenDates(Date startDate, Date endDate) {
        return patientRepository.getPatientsBetweenDates(startDate, endDate);
    }

    public List<PatientModel> getPatientsByDoctorId(int doctorId) {
        return patientRepository.getPatientsByDoctorId(doctorId);
    }

    public Map<Integer, Integer> getDoctorWisePatientCount() {
        return patientRepository.getDoctorWisePatientCount();
    }

    public int getMonthWithMaxOPDPatients() {
        return patientRepository.getMonthWithMaxOPDPatients();
    }





}


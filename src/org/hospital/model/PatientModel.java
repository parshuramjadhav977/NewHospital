package org.hospital.model;

import java.util.Date;

public class PatientModel {
    private int ptid;
    private String ptName;
    private int age;
    private String gender;
    private String contact;
    private String address;
    private Date opdDate;
    private int docid;
    private int fees;
    private Date appointmentDate;
	public int getPtid() {
		return ptid;
	}
	public void setPtid(int ptid) {
		this.ptid = ptid;
	}
	public String getPtName() {
		return ptName;
	}
	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getOpdDate() {
		return opdDate;
	}
	public void setOpdDate(Date opdDate) {
		this.opdDate = opdDate;
	}
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	

}


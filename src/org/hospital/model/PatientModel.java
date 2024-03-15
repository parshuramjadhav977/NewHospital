package org.hospital.model;

import java.util.Date;

public class PatientModel {
    
    private int ptid;
    private String ptName;
    private int age;
    private Gender gender;
    private String contact;
    private String address;
    private Date opdDate;
    private int docid;
    private float fess;
    private Date appointmentDate;
    private Category category;

    public PatientModel()
    {
    	
    }
    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum Category {
        OPD, IPD;

		public boolean equalsIgnoreCase(String string) {
			// TODO Auto-generated method stub
			return false;
		}
    }

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
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

	public float getFess() {
		return fess;
	}

	public void setFess(float fess) {
		this.fess = fess;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    public String toString() {
        return "Patient ID: " + ptid + "\n" +
               "Name: " + ptName + "\n" +
               "Age: " + age + "\n" +
               "Gender: " + gender + "\n" +
               "Contact: " + contact + "\n" +
               "Address: " + address + "\n" +
               "OPD Date: " + opdDate + "\n" +
               "Doctor ID: " + docid + "\n" +
               "Fess: " + fess + "\n" +
               "Appointment Date: " + appointmentDate + "\n" +
               "Category: " + category;
    }

	
	

}


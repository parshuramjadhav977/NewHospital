package org.hospital.model;

public class AllocatePatientModel {
    private int ptid;
    private String ptName;
    private int docid;
    private String doctorName;

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

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "AllocatePatientModel{" +
                "ptid=" + ptid +
                ", ptName='" + ptName + '\'' +
                ", docid=" + docid +
                ", doctorName='" + doctorName + '\'' +
                '}';
    }
}

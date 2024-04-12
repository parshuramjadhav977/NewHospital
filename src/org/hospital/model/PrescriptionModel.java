package org.hospital.model;


public class PrescriptionModel {
 private int prescriptionId;
 private String prescriptionDetails;
 private int ptid;
 private int docid;


 public int getPrescriptionId() {
     return prescriptionId;
 }

 public void setPrescriptionId(int prescriptionId) {
     this.prescriptionId = prescriptionId;
 }

 public String getPrescriptionDetails() {
     return prescriptionDetails;
 }

 public void setPrescriptionDetails(String prescriptionDetails) {
     this.prescriptionDetails = prescriptionDetails;
 }

 public int getPtid() {
     return ptid;
 }

 public void setPtid(int ptid) {
     this.ptid = ptid;
 }

 public int getDocid() {
     return docid;
 }

 public void setDocid(int docid) {
     this.docid = docid;
 }

 @Override
 public String toString() {
     return "Prescription ID: " + prescriptionId + "Prescription Details:" + prescriptionDetails;
 }
}

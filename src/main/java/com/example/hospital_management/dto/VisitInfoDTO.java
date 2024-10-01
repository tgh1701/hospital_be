package com.example.hospital_management.dto;

import java.util.Date;

public class VisitInfoDTO {
    private String visitId;
    private String patientName;
    private String doctorName;
    private String diseaseName;
    private Date dateIn;
    private Date dateOut;
    private int totalPrice;
    private String status;
    private String nurseName;

    // Constructors
    public VisitInfoDTO(String visitId, String patientName, String doctorName, String diseaseName, Date dateIn, Date dateOut, int totalPrice, String status, String nurseName) {
        this.visitId = visitId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.diseaseName = diseaseName;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.totalPrice = totalPrice;
        this.status = status;
        this.nurseName = nurseName;
    }

    // Getters and Setters
    public String getVisitId() { return visitId; }
    public void setVisitId(String visitId) { this.visitId = visitId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getDiseaseName() { return diseaseName; }
    public void setDiseaseName(String diseaseName) { this.diseaseName = diseaseName; }

    public Date getDateIn() { return dateIn; }
    public void setDateIn(Date dateIn) { this.dateIn = dateIn; }

    public Date getDateOut() { return dateOut; }
    public void setDateOut(Date dateOut) { this.dateOut = dateOut; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNurseName() { return nurseName; }
    public void setNurseName(String nurseName) { this.nurseName = nurseName; }
}

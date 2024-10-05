package com.example.hospital_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Prescription")
public class Prescription {
    @Id
    @Column(name = "Prescriptionid")
    private String prescriptionId;

    @Column(name = "Visitid")
    private String visitId;

    // Getters and Setters
    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }
}
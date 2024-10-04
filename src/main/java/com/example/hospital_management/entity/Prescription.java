package com.example.hospital_management.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Prescription")
public class Prescription {

    @Id
    @Column(name = "Prescriptionid", length = 10)
    private String prescriptionId;

    @ManyToOne(fetch = FetchType.EAGER) // Dùng EAGER để tránh Lazy Loading Issue
    @JoinColumn(name = "Visitid", referencedColumnName = "Visitid", nullable = false)
    private Visit visit;

    // Getters and Setters
    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    // toString method
    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId='" + prescriptionId + '\'' +
                ", visit=" + (visit != null ? visit.getVisitId() : null) +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prescription that)) return false;
        return Objects.equals(prescriptionId, that.prescriptionId) &&
                Objects.equals(visit, that.visit);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId, visit);
    }
}

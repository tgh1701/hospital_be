package com.example.hospital_management.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Prescription")
public class Prescription {

    @Id
    @Column(name = "PrescriptionID", length = 10)
    private String prescriptionId;

    @Column(name = "VisitID", length = 10)
    private String visitId;

    @Column(name = "MedicineID", length = 10)
    private String medicineId;

    @Column(name = "Quantity")
    private int quantity;

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

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // toString method
    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId='" + prescriptionId + '\'' +
                ", visitId='" + visitId + '\'' +
                ", medicineId='" + medicineId + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prescription)) return false;
        Prescription that = (Prescription) o;
        return quantity == that.quantity &&
                Objects.equals(prescriptionId, that.prescriptionId) &&
                Objects.equals(visitId, that.visitId) &&
                Objects.equals(medicineId, that.medicineId);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId, visitId, medicineId, quantity);
    }
}

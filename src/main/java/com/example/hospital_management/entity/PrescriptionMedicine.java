package com.example.hospital_management.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PrescriptionMedicine")
public class PrescriptionMedicine {

    @Id
    @Column(name = "Prescriptionmedicineid", length = 10)
    private String prescriptionMedicineId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Prescriptionid", referencedColumnName = "Prescriptionid", nullable = false)
    private Prescription prescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Medicineid", referencedColumnName = "Medicineid", nullable = false)
    private Medicine medicine;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    // Getters and Setters
    public String getPrescriptionMedicineId() {
        return prescriptionMedicineId;
    }

    public void setPrescriptionMedicineId(String prescriptionMedicineId) {
        this.prescriptionMedicineId = prescriptionMedicineId;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
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
        return "PrescriptionMedicine{" +
                "prescriptionMedicineId='" + prescriptionMedicineId + '\'' +
                ", prescription=" + (prescription != null ? prescription.getPrescriptionId() : null) +
                ", medicine=" + (medicine != null ? medicine.getMedicineId() : null) +
                ", quantity=" + quantity +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrescriptionMedicine that)) return false;
        return quantity == that.quantity &&
                Objects.equals(prescriptionMedicineId, that.prescriptionMedicineId) &&
                Objects.equals(prescription, that.prescription) &&
                Objects.equals(medicine, that.medicine);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(prescriptionMedicineId, prescription, medicine, quantity);
    }
}

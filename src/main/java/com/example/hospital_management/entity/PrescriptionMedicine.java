package com.example.hospital_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Prescriptionmedicine")
public class PrescriptionMedicine {
    @Id
    @Column(name = "Prescriptionmedicineid")
    private String prescriptionMedicineId;

    @Column(name = "Prescriptionid")
    private String prescriptionId;

    @Column(name = "Medicineid")
    private String medicineId;

    @Column(name = "Quantity")
    private int quantity;

    // Getters and Setters
    public String getPrescriptionMedicineId() {
        return prescriptionMedicineId;
    }

    public void setPrescriptionMedicineId(String prescriptionMedicineId) {
        this.prescriptionMedicineId = prescriptionMedicineId;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
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
}
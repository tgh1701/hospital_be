package com.example.hospital_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "Medicine")
public class Medicine {

    @Id
    @Column(name = "Medicineid", length = 10)
    private String medicineId;

    @Column(name = "Medicinename", length = 100)
    private String medicineName;

    @Column(name = "Price")
    private int price;

    // Getters and Setters
    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // toString method
    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId='" + medicineId + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", price=" + price +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine medicine)) return false;
        return price == medicine.price &&
                Objects.equals(medicineId, medicine.medicineId) &&
                Objects.equals(medicineName, medicine.medicineName);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(medicineId, medicineName, price);
    }
}

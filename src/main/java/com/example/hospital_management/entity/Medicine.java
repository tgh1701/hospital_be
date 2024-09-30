package com.example.hospital_management.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Medicine")
public class Medicine {

    @Id
    @Column(name = "MedicineID", length = 10)
    private String medicineId;

    @Column(name = "MedicineName", length = 100)
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
        if (!(o instanceof Medicine)) return false;
        Medicine medicine = (Medicine) o;
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

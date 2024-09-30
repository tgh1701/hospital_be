package com.example.hospital_management.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Date;

@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @Column(name = "PatientID", length = 10)
    private String patientId;

    @Column(name = "IdentityCard", length = 12, unique = true)
    private String identityCard;

    @Column(name = "PatientName", length = 100)
    private String patientName;

    @Temporal(TemporalType.DATE)
    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "Address", length = 200)
    private String address;

    @Column(name = "Phone", length = 15)
    private String phone;

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // toString method
    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", patientName='" + patientName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(patientId, patient.patientId) &&
                Objects.equals(identityCard, patient.identityCard) &&
                Objects.equals(patientName, patient.patientName) &&
                Objects.equals(dateOfBirth, patient.dateOfBirth) &&
                Objects.equals(address, patient.address) &&
                Objects.equals(phone, patient.phone);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(patientId, identityCard, patientName, dateOfBirth, address, phone);
    }
}

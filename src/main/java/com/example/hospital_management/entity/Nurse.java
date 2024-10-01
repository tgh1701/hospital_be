package com.example.hospital_management.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Nurse")
public class Nurse {

    @Id
    @Column(name = "Nurseid", length = 10)
    private String nurseId;

    @Column(name = "Identitycard", unique = true, length = 12)
    private String identityCard;

    @Column(name = "Nursename", length = 100)
    private String nurseName;

    @Column(name = "Level", length = 50)
    private String level;

    @Column(name = "Seniority")
    private int seniority;

    @Column(name = "Dateofbirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "Address", length = 200)
    private String address;

    @Column(name = "Phone", length = 15)
    private String phone;

    // Getters and Setters
    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
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
        return "Nurse{" +
                "nurseId='" + nurseId + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", nurseName='" + nurseName + '\'' +
                ", level='" + level + '\'' +
                ", seniority=" + seniority +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nurse nurse)) return false;
        return seniority == nurse.seniority &&
                Objects.equals(nurseId, nurse.nurseId) &&
                Objects.equals(identityCard, nurse.identityCard) &&
                Objects.equals(nurseName, nurse.nurseName) &&
                Objects.equals(level, nurse.level) &&
                Objects.equals(dateOfBirth, nurse.dateOfBirth) &&
                Objects.equals(address, nurse.address) &&
                Objects.equals(phone, nurse.phone);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(nurseId, identityCard, nurseName, level, seniority, dateOfBirth, address, phone);
    }
}

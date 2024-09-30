package com.example.hospital_management.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Doctor")
public class Doctor {

    @Id
    @Column(name = "DoctorID", length = 10)
    private String doctorId;

    @Column(name = "IdentityCard", unique = true, length = 12)
    private String identityCard;

    @Column(name = "DoctorName", length = 100)
    private String doctorName;

    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "Address", length = 200)
    private String address;

    @Column(name = "CareerLevel")
    private int careerLevel;

    @Column(name = "Seniority")
    private int seniority;

    @Column(name = "Level", length = 50)
    private String level;

    @Column(name = "Expertise", length = 100)
    private String expertise;

    // Getter và Setter cho từng trường
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public int getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(int careerLevel) {
        this.careerLevel = careerLevel;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", careerLevel=" + careerLevel +
                ", seniority=" + seniority +
                ", level='" + level + '\'' +
                ", expertise='" + expertise + '\'' +
                '}';
    }

    // Override equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return careerLevel == doctor.careerLevel &&
                seniority == doctor.seniority &&
                Objects.equals(doctorId, doctor.doctorId) &&
                Objects.equals(identityCard, doctor.identityCard) &&
                Objects.equals(doctorName, doctor.doctorName) &&
                Objects.equals(dateOfBirth, doctor.dateOfBirth) &&
                Objects.equals(address, doctor.address) &&
                Objects.equals(level, doctor.level) &&
                Objects.equals(expertise, doctor.expertise);
    }

    // Override hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(doctorId, identityCard, doctorName, dateOfBirth, address, careerLevel, seniority, level, expertise);
    }
}

package com.example.hospital_management.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Doctor")
public class Doctor {

    @Id
    @Column(name = "Doctorid", length = 10)
    private String doctorId;

    @Column(name = "Identitycard", unique = true, length = 12)
    private String identityCard;

    @Column(name = "Doctorname", length = 100)
    private String doctorName;

    @Column(name = "Dateofbirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "Address", length = 200)
    private String address;

    @Column(name = "Careerlevel", nullable = false)
    private Integer careerLevel;

    @Column(name = "Seniority", nullable = false)
    private Integer seniority;

    @Column(name = "Level", length = 50)
    private String level;

    @Column(name = "Departmentid", length = 10)
    private String departmentId;

    // Getters and Setters
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

    public Integer getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(Integer careerLevel) {
        this.careerLevel = careerLevel;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDepartment() {
        return departmentId;
    }

    public void setDepartment(String department) {
        this.departmentId = department;
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
                ", departmentId='" + departmentId + '\'' +
                '}';
    }

    // Override equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor doctor)) return false;
        return Objects.equals(careerLevel, doctor.careerLevel) &&
                Objects.equals(seniority, doctor.seniority) &&
                Objects.equals(doctorId, doctor.doctorId) &&
                Objects.equals(identityCard, doctor.identityCard) &&
                Objects.equals(doctorName, doctor.doctorName) &&
                Objects.equals(dateOfBirth, doctor.dateOfBirth) &&
                Objects.equals(address, doctor.address) &&
                Objects.equals(level, doctor.level) &&
                Objects.equals(departmentId, doctor.departmentId);
    }

    // Override hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(doctorId, identityCard, doctorName, dateOfBirth, address, careerLevel, seniority, level, departmentId);
    }
}

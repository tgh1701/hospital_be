package com.example.hospital_management.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Visit")
public class Visit {

    @Id
    @Column(name = "Visitid", length = 10)
    private String visitId;

    @Column(name = "Patientid", length = 10)
    private String patientId;

    @Column(name = "Doctorid", length = 10)
    private String doctorId;

    @Column(name = "Diseaseid", length = 10)
    private String diseaseId;

    @Column(name = "Datein")
    @Temporal(TemporalType.DATE)
    private Date dateIn;

    @Column(name = "Dateout")
    @Temporal(TemporalType.DATE)
    private Date dateOut;

    @Column(name = "Totalprice")
    private int totalPrice;

    @Column(name = "Status")
    private String status;

    // Getters and Setters
    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method
    @Override
    public String toString() {
        return "Visit{" +
                "visitId='" + visitId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", diseaseId='" + diseaseId + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit visit)) return false;
        return totalPrice == visit.totalPrice &&
                Objects.equals(visitId, visit.visitId) &&
                Objects.equals(patientId, visit.patientId) &&
                Objects.equals(doctorId, visit.doctorId) &&
                Objects.equals(diseaseId, visit.diseaseId) &&
                Objects.equals(dateIn, visit.dateIn) &&
                Objects.equals(dateOut, visit.dateOut) &&
                Objects.equals(status, visit.status);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(visitId, patientId, doctorId, diseaseId, dateIn, dateOut, totalPrice, status);
    }
}

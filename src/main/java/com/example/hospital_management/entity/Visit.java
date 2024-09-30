package com.example.hospital_management.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Visit")
public class Visit {

    @Id
    @Column(name = "VisitID", length = 10)
    private String visitId;

    @Column(name = "PatientID", length = 10)
    private String patientId;

    @Column(name = "DoctorID", length = 10)
    private String doctorId;

    @Column(name = "DiseaseID", length = 10)
    private String diseaseId;

    @Column(name = "DateIn")
    @Temporal(TemporalType.DATE)
    private Date dateIn;

    @Column(name = "DateOut")
    @Temporal(TemporalType.DATE)
    private Date dateOut;

    @Column(name = "TotalPrice")
    private int totalPrice;

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
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) return false;
        Visit visit = (Visit) o;
        return totalPrice == visit.totalPrice &&
                Objects.equals(visitId, visit.visitId) &&
                Objects.equals(patientId, visit.patientId) &&
                Objects.equals(doctorId, visit.doctorId) &&
                Objects.equals(diseaseId, visit.diseaseId) &&
                Objects.equals(dateIn, visit.dateIn) &&
                Objects.equals(dateOut, visit.dateOut);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(visitId, patientId, doctorId, diseaseId, dateIn, dateOut, totalPrice);
    }
}

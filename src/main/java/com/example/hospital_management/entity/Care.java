package com.example.hospital_management.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Care")
public class Care {

    @Id
    @Column(name = "Careid", length = 10)
    private String careId;

    @Column(name = "Visitid", length = 10)
    private String visitId;

    @Column(name = "Nurseid", length = 10)
    private String nurseId;

    @Column(name = "Datecare")
    @Temporal(TemporalType.DATE)
    private Date dateCare;

    // Getters and Setters
    public String getCareId() {
        return careId;
    }

    public void setCareId(String careId) {
        this.careId = careId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public Date getDateCare() {
        return dateCare;
    }

    public void setDateCare(Date dateCare) {
        this.dateCare = dateCare;
    }

    // toString method
    @Override
    public String toString() {
        return "Care{" +
                "careId='" + careId + '\'' +
                ", visitId='" + visitId + '\'' +
                ", nurseId='" + nurseId + '\'' +
                ", dateCare=" + dateCare +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Care care)) return false;
        return Objects.equals(careId, care.careId) &&
                Objects.equals(visitId, care.visitId) &&
                Objects.equals(nurseId, care.nurseId) &&
                Objects.equals(dateCare, care.dateCare);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(careId, visitId, nurseId, dateCare);
    }
}

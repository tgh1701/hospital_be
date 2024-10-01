package com.example.hospital_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Disease")
public class Disease {

    @Id
    @Column(name = "Diseaseid", length = 10)
    private String diseaseId;

    @Column(name = "Diseasename", length = 100)
    private String diseaseName;

    @Column(name = "Department", length = 100)
    private String department;

    // Getters and Setters
    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // toString method
    @Override
    public String toString() {
        return "Disease{" +
                "diseaseId='" + diseaseId + '\'' +
                ", diseaseName='" + diseaseName + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disease disease)) return false;
        return Objects.equals(diseaseId, disease.diseaseId) &&
                Objects.equals(diseaseName, disease.diseaseName) &&
                Objects.equals(department, disease.department);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, diseaseName, department);
    }
}

package com.example.hospital_management.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Disease")
public class Disease {

    @Id
    @Column(name = "DiseaseID", length = 10)
    private String diseaseId;

    @Column(name = "DiseaseName", length = 100)
    private String diseaseName;

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

    // toString method
    @Override
    public String toString() {
        return "Disease{" +
                "diseaseId='" + diseaseId + '\'' +
                ", diseaseName='" + diseaseName + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disease)) return false;
        Disease disease = (Disease) o;
        return Objects.equals(diseaseId, disease.diseaseId) &&
                Objects.equals(diseaseName, disease.diseaseName);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, diseaseName);
    }
}

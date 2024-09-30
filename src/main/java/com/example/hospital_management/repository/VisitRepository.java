package com.example.hospital_management.repository;

import com.example.hospital_management.dto.DiseaseStatistics;
import com.example.hospital_management.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, String> {
    @Query(value = "WITH RankedVisits AS (" +
            "SELECT v.VisitID, v.PatientID, v.DiseaseID, v.DateIn, " +
            "ROW_NUMBER() OVER (PARTITION BY v.PatientID, v.DiseaseID ORDER BY v.DateIn) AS VisitRank, " +
            "LAG(v.DiseaseID) OVER (PARTITION BY v.PatientID ORDER BY v.DateIn) AS PrevDisease " +
            "FROM Visit v WHERE v.DateIn BETWEEN :startDate AND :endDate) " +
            "SELECT d.DiseaseName, COUNT(DISTINCT rv.PatientID) AS PatientCount " +
            "FROM RankedVisits rv JOIN Disease d ON rv.DiseaseID = d.DiseaseID " +
            "WHERE rv.DiseaseID <> rv.PrevDisease OR rv.PrevDisease IS NULL " +
            "GROUP BY d.DiseaseName ORDER BY PatientCount DESC", nativeQuery = true)
    List<DiseaseStatistics> getDiseaseStatistics(String startDate, String endDate);
}
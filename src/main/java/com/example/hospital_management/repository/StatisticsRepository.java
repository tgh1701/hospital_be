package com.example.hospital_management.repository;

import com.example.hospital_management.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class StatisticsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DiseaseStatDTO> getDiseaseStatistics(LocalDate startDate, LocalDate endDate) {
        String sql = """
            WITH RankedVisits AS (
                SELECT 
                    v.VisitID,
                    v.PatientID,
                    v.DiseaseID,
                    v.DateIn,
                    ROW_NUMBER() OVER (PARTITION BY v.PatientID, v.DiseaseID ORDER BY v.DateIn) AS VisitRank,
                    LAG(v.DiseaseID) OVER (PARTITION BY v.PatientID ORDER BY v.DateIn) AS PrevDisease
                FROM Visit v
                WHERE v.DateIn BETWEEN ? AND ?
            )
            SELECT 
                d.DiseaseName,
                COUNT(DISTINCT rv.PatientID) AS PatientCount
            FROM RankedVisits rv
            JOIN Disease d ON rv.DiseaseID = d.DiseaseID
            WHERE rv.DiseaseID <> rv.PrevDisease OR rv.PrevDisease IS NULL
            GROUP BY d.DiseaseName
            ORDER BY PatientCount DESC
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new DiseaseStatDTO(
                rs.getString("DiseaseName"),
                rs.getInt("PatientCount")
        ), startDate, endDate);
    }

    public List<DoctorSalaryDTO> getDoctorSalaries(LocalDate startDate, LocalDate endDate) {
        String sql = """
            SELECT 
                d.DoctorName,
                COALESCE(7000000 + COUNT(DISTINCT v.PatientID) * 1000000, 7000000) AS Salary
            FROM Doctor d
            LEFT JOIN Visit v ON d.DoctorID = v.DoctorID AND v.DateOut BETWEEN ? AND ?
            AND v.Status = 'Done'
            GROUP BY d.DoctorID
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new DoctorSalaryDTO(
                rs.getString("DoctorName"),
                rs.getDouble("Salary")
        ), startDate, endDate);
    }

    public List<NurseSalaryDTO> getNurseSalaries(LocalDate startDate, LocalDate endDate) {
        String sql = """
            SELECT 
                n.NurseName,
                5000000 + COUNT(c.CareID) * 200000 AS Salary
            FROM Nurse n
            JOIN Care c ON n.NurseID = c.NurseID
            JOIN Visit v ON c.VisitID = v.VisitID
            WHERE v.DateOut BETWEEN ? AND ?
            GROUP BY n.NurseID
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new NurseSalaryDTO(
                rs.getString("NurseName"),
                rs.getDouble("Salary")
        ), startDate, endDate);
    }

    public List<PatientVisitDTO> getPatientInfo(String patientId) {
        String sql = """
            WITH VisitDetails AS (
                SELECT 
                    p.PatientName,
                    v.VisitID,
                    v.DateIn,
                    v.DateOut,
                    d.DiseaseName,
                    v.TotalPrice,
                    v.Status,
                    ROW_NUMBER() OVER (PARTITION BY p.PatientID, v.DiseaseID ORDER BY v.DateIn) AS VisitSequence
                FROM Patient p
                JOIN Visit v ON p.PatientID = v.PatientID
                JOIN Disease d ON v.DiseaseID = d.DiseaseID
                WHERE p.PatientID = ?
            )
            SELECT 
                PatientName,
                VisitID,
                DateIn,
                DateOut,
                DiseaseName,
                TotalPrice,
                VisitSequence,
                Status
            FROM VisitDetails
            ORDER BY DateIn
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new PatientVisitDTO(
                rs.getString("PatientName"),
                rs.getString("VisitID"),
                rs.getDate("DateIn").toLocalDate(),
                rs.getDate("DateOut") != null ? rs.getDate("DateOut").toLocalDate() : null,
                rs.getString("DiseaseName"),
                rs.getDouble("TotalPrice"),
                rs.getInt("VisitSequence"),
                rs.getString("Status")
        ), patientId);
    }

    public TotalRevenueDTO getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        String sql = """
            SELECT 
                SUM(v.TotalPrice) + (
                    SELECT SUM(m.Price * p.Quantity) 
                    FROM Prescription p 
                    JOIN Medicine m ON p.MedicineID = m.MedicineID
                    JOIN Visit v ON p.VisitID = v.VisitID
                    WHERE v.DateOut BETWEEN ? AND ?
                ) AS TotalRevenue
            FROM Visit v
            WHERE v.DateOut BETWEEN ? AND ?
        """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new TotalRevenueDTO(
                rs.getDouble("TotalRevenue")
        ), startDate, endDate, startDate, endDate);
    }
}
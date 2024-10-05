package com.example.hospital_management.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CustomPrescriptionRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomPrescriptionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getPrescriptionByVisitId(String visitId) {
        String sql = """
                SELECT 
                    p.PrescriptionID,
                    pm.MedicineID,
                    m.MedicineName,
                    pm.Quantity,
                    m.Price,
                    (pm.Quantity * m.Price) AS TotalMedicinePrice,
                    v.TotalPrice,
                    pat.PatientName,
                    d.DoctorName,
                    v.DateIn,
                    v.DateOut
                FROM 
                    Prescription p
                JOIN 
                    PrescriptionMedicine pm ON p.PrescriptionID = pm.PrescriptionID
                JOIN 
                    Medicine m ON pm.MedicineID = m.MedicineID
                JOIN 
                    Visit v ON p.VisitID = v.VisitID
                JOIN 
                    Patient pat ON v.PatientID = pat.PatientID
                JOIN 
                    Doctor d ON v.DoctorID = d.DoctorID
                WHERE 
                    v.VisitID = ?
                """;
        return jdbcTemplate.queryForList(sql, visitId);
    }

    public int deletePrescriptionByVisitId(String visitId) {
        String sql = "DELETE FROM Prescription WHERE VisitID = ?";
        return jdbcTemplate.update(sql, visitId);
    }
}

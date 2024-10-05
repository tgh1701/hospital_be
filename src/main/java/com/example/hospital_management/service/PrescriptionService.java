package com.example.hospital_management.service;

import com.example.hospital_management.entity.Prescription;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.CustomPrescriptionRepository;
import com.example.hospital_management.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PrescriptionService {

    @Autowired
    private CustomPrescriptionRepository customPrescriptionRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;  // Thêm @Autowired ở đây để khởi tạo PrescriptionRepository

    public Prescription createPrescription(Prescription prescription) {
        try {
            return prescriptionRepository.save(prescription);
        } catch (Exception e) {
            throw new CustomException(500, "Failed to create prescription");
        }
    }

    public List<Map<String, Object>> getPrescriptionByVisitId(String visitId) {
        List<Map<String, Object>> result = customPrescriptionRepository.getPrescriptionByVisitId(visitId);
        if (result.isEmpty()) {
            throw new CustomException(404, "Prescription with VisitID " + visitId + " not found.");
        }
        return result;
    }

    public void deletePrescriptionByVisitId(String visitId) {
        if (customPrescriptionRepository.deletePrescriptionByVisitId(visitId) == 0) {
            throw new CustomException(404, "Prescription with VisitID " + visitId + " not found.");
        }
    }
}

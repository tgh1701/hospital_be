package com.example.hospital_management.service;

import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Map<String, Object>> getPrescriptionByVisitId(String visitId) {
        List<Map<String, Object>> result = prescriptionRepository.getPrescriptionByVisitId(visitId);
        if (result.isEmpty()) {
            throw new CustomException(404, "Prescription with VisitID " + visitId + " not found.");
        }
        return result;
    }

    public void deletePrescriptionByVisitId(String visitId) {
        if (prescriptionRepository.deletePrescriptionByVisitId(visitId) == 0) {
            throw new CustomException(404, "Prescription with VisitID " + visitId + " not found.");
        }
    }
}

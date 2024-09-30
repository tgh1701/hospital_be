package com.example.hospital_management.service;

import com.example.hospital_management.entity.Prescription; // Nếu bạn có class Prescription, hãy import
import com.example.hospital_management.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescriptionById(String id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void deletePrescription(String id) {
        prescriptionRepository.deleteById(id);
    }
}

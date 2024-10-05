package com.example.hospital_management.service;

import com.example.hospital_management.entity.PrescriptionMedicine;
import com.example.hospital_management.repository.PrescriptionMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMedicineService {
    @Autowired
    private PrescriptionMedicineRepository prescriptionMedicineRepository;

    public PrescriptionMedicine createPrescriptionMedicine(PrescriptionMedicine prescriptionMedicine) {
        return prescriptionMedicineRepository.save(prescriptionMedicine);
    }
}

package com.example.hospital_management.service;

import com.example.hospital_management.entity.Prescription;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.PrescriptionRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescriptionById(String id) {
        return prescriptionRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Prescription with ID " + id + " not found."));
    }

    public Prescription updatePrescription(String id, Prescription updatedPrescription) throws CustomException {
        Optional<Prescription> existingPrescriptionOptional = prescriptionRepository.findById(id);
        if (existingPrescriptionOptional.isPresent()) {
            Prescription existingPrescription = existingPrescriptionOptional.get();

            existingPrescription.setVisitId(updatedPrescription.getVisitId());
            existingPrescription.setMedicineId(updatedPrescription.getMedicineId());
            existingPrescription.setQuantity(updatedPrescription.getQuantity());

            return prescriptionRepository.save(existingPrescription);
        } else {
            throw new CustomException(404, "Prescription not found");
        }
    }

    public Prescription savePrescription(Prescription prescription) {
        if (prescription.getPrescriptionId() != null && prescriptionRepository.existsById(prescription.getPrescriptionId())) {
            throw new CustomException(409, "Prescription with ID " + prescription.getPrescriptionId() + " already exists.");
        }
        return prescriptionRepository.save(prescription);
    }

    public ApiResponse deletePrescription(String id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new CustomException(404, "Prescription with ID " + id + " not found.");
        }
        try {
            prescriptionRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this prescription as it is associated with existing records.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

package com.example.hospital_management.service;

import com.example.hospital_management.entity.Medicine;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.MedicineRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicineById(String id) {
        return medicineRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Medicine with ID " + id + " not found."));
    }

    public Medicine saveMedicine(Medicine medicine) {
        if (medicine.getMedicineId() != null && medicineRepository.existsById(medicine.getMedicineId())) {
            throw new CustomException(409, "Medicine with ID " + medicine.getMedicineId() + " already exists.");
        }
        return medicineRepository.save(medicine);
    }

    public ApiResponse deleteMedicine(String id) {
        if (!medicineRepository.existsById(id)) {
            throw new CustomException(404, "Medicine with ID " + id + " not found.");
        }
        try {
            medicineRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this medicine as it is associated with existing records.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

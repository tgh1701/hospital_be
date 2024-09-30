package com.example.hospital_management.service;

import com.example.hospital_management.entity.Medicine; // Nếu bạn có class Medicine, hãy import
import com.example.hospital_management.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return medicineRepository.findById(id).orElse(null);
    }

    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public void deleteMedicine(String id) {
        medicineRepository.deleteById(id);
    }
}

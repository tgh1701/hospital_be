package com.example.hospital_management.service;

import com.example.hospital_management.entity.Care; // Nếu bạn có class Care, hãy import
import com.example.hospital_management.repository.CareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CareService {
    @Autowired
    private CareRepository careRepository;

    public List<Care> getAllCares() {
        return careRepository.findAll();
    }

    public Care getCareById(String id) {
        return careRepository.findById(id).orElse(null);
    }

    public Care saveCare(Care care) {
        return careRepository.save(care);
    }

    public void deleteCare(String id) {
        careRepository.deleteById(id);
    }
}

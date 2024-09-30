package com.example.hospital_management.service;

import com.example.hospital_management.entity.Nurse;
import com.example.hospital_management.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NurseService {
    @Autowired
    private NurseRepository nurseRepository;

    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse getNurseById(String id) {
        return nurseRepository.findById(id).orElse(null);
    }

    public Nurse saveNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public void deleteNurse(String id) {
        nurseRepository.deleteById(id);
    }
}

package com.example.hospital_management.service;

import com.example.hospital_management.entity.Nurse;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.NurseRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        return nurseRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Nurse with ID " + id + " not found."));
    }

    public Nurse saveNurse(Nurse nurse) {
        if (nurse.getNurseId() != null && nurseRepository.existsById(nurse.getNurseId())) {
            throw new CustomException(409, "Nurse with ID " + nurse.getNurseId() + " already exists.");
        }
        return nurseRepository.save(nurse);
    }

    public ApiResponse deleteNurse(String id) {
        if (!nurseRepository.existsById(id)) {
            throw new CustomException(404, "Nurse with ID " + id + " not found.");
        }
        try {
            nurseRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this nurse as they have existing records.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

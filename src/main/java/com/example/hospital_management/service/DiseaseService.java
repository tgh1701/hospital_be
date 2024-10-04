package com.example.hospital_management.service;

import com.example.hospital_management.entity.Disease;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.DiseaseRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseService {
    @Autowired
    private DiseaseRepository diseaseRepository;

    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }

    public Disease getDiseaseById(String id) {
        return diseaseRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Disease with ID " + id + " not found."));
    }

    public Disease saveDisease(Disease disease) {
        if (disease.getDiseaseId() != null && diseaseRepository.existsById(disease.getDiseaseId())) {
            throw new CustomException(409, "Disease with ID " + disease.getDiseaseId() + " already exists.");
        }
        return diseaseRepository.save(disease);
    }

    public Disease updateDisease(String id, Disease updatedDisease) {
        Optional<Disease> existingDiseaseOptional = diseaseRepository.findById(id);
        if (existingDiseaseOptional.isPresent()) {
            Disease existingDisease = existingDiseaseOptional.get();
            existingDisease.setDiseaseName(updatedDisease.getDiseaseName());
            existingDisease.setDepartment(updatedDisease.getDepartment());
            return diseaseRepository.save(existingDisease);
        } else {
            throw new CustomException(404, "Disease not found");
        }
    }

    public ApiResponse deleteDisease(String id) {
        if (!diseaseRepository.existsById(id)) {
            throw new CustomException(404, "Disease with ID " + id + " not found.");
        }
        try {
            diseaseRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this disease as it is associated with existing records.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

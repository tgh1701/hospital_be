package com.example.hospital_management.service;

import com.example.hospital_management.entity.Care;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.CareRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareService {
    @Autowired
    private CareRepository careRepository;

    public List<Care> getAllCares() {
        return careRepository.findAll();
    }

    public Care getCareById(String id) {
        return careRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Care with ID " + id + " not found."));
    }

    public Care saveCare(Care care) {
        if (care.getCareId() != null && careRepository.existsById(care.getCareId())) {
            throw new CustomException(409, "Care with ID " + care.getCareId() + " already exists.");
        }
        return careRepository.save(care);
    }

    public Care updateCare(String id, Care updatedCare) throws CustomException {
        Optional<Care> existingCareOptional = careRepository.findById(id);
        if (existingCareOptional.isPresent()) {
            Care existingCare = existingCareOptional.get();

            existingCare.setVisitId(updatedCare.getVisitId());
            existingCare.setNurseId(updatedCare.getNurseId());
            existingCare.setDateCare(updatedCare.getDateCare());

            return careRepository.save(existingCare);
        } else {
            throw new CustomException(404, "Care not found");
        }
    }

    public ApiResponse deleteCare(String id) {
        if (!careRepository.existsById(id)) {
            throw new CustomException(404, "Care with ID " + id + " not found.");
        }
        try {
            careRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this care entry as it is associated with existing records.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

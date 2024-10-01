package com.example.hospital_management.service;

import com.example.hospital_management.dto.VisitInfoDTO;
import com.example.hospital_management.entity.Visit;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.VisitRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public List<VisitInfoDTO> getAllVisitInfo() {
        return visitRepository.findAllVisitInfo();
    }

    public Visit getVisitById(String id) {
        return visitRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Visit with ID " + id + " not found."));
    }

    public Visit saveVisit(Visit visit) {
        if (visit.getVisitId() != null && visitRepository.existsById(visit.getVisitId())) {
            throw new CustomException(409, "Visit with ID " + visit.getVisitId() + " already exists.");
        }
        return visitRepository.save(visit);
    }

    public ApiResponse deleteVisit(String id) {
        if (!visitRepository.existsById(id)) {
            throw new CustomException(404, "Visit with ID " + id + " not found.");
        }
        try {
            visitRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this visit as it is associated with existing records.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

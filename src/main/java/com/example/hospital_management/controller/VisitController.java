package com.example.hospital_management.controller;

import com.example.hospital_management.dto.VisitInfoDTO;
import com.example.hospital_management.entity.Visit;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllVisits() {
        List<VisitInfoDTO> visits = visitService.getAllVisitInfo();
        if (visits.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse(204, "No visits found"));
        }
        return ResponseEntity.ok(new ApiResponse(200, "Visits retrieved successfully", visits));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getVisitById(@PathVariable String id) {
        try {
            Visit visit = visitService.getVisitById(id);
            return ResponseEntity.ok(new ApiResponse(200, "Visit retrieved successfully", visit));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createVisit(@RequestBody Visit visit) {
        try {
            Visit savedVisit = visitService.saveVisit(visit);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Visit created successfully", savedVisit));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateVisit(@PathVariable String id, @RequestBody Visit visit) {
        try {
            visit.setVisitId(id);
            Visit updatedVisit = visitService.updateVisit(id, visit);
            return ResponseEntity.ok(new ApiResponse(200, "Visit updated successfully", updatedVisit));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteVisit(@PathVariable String id) {
        try {
            ApiResponse response = visitService.deleteVisit(id);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}

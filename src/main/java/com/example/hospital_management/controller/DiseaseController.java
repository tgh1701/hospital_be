package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Disease;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDiseases() {
        try {
            List<Disease> diseases = diseaseService.getAllDiseases();
            if (diseases.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse(204, "No diseases found"));
            }
            return ResponseEntity.ok(new ApiResponse(200, "Diseases retrieved successfully", diseases));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(500, "An unexpected error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDiseaseById(@PathVariable String id) {
        try {
            Disease disease = diseaseService.getDiseaseById(id);
            return ResponseEntity.ok(new ApiResponse(200, "Disease retrieved successfully", disease));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDisease(@RequestBody Disease disease) {
        try {
            Disease savedDisease = diseaseService.saveDisease(disease);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Disease created successfully", savedDisease));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDisease(@PathVariable String id, @RequestBody Disease disease) {
        try {
            disease.setDiseaseId(id);
            Disease updatedDisease = diseaseService.updateDisease(id, disease);
            return ResponseEntity.ok(new ApiResponse(200, "Disease updated successfully", updatedDisease));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDisease(@PathVariable String id) {
        try {
            ApiResponse response = diseaseService.deleteDisease(id);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}

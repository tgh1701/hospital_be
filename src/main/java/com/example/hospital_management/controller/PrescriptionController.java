package com.example.hospital_management.controller;

import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/{visitId}")
    public ResponseEntity<ApiResponse> getPrescriptionByVisitId(@PathVariable String visitId) {
        try {
            return ResponseEntity.ok(new ApiResponse(200, "Prescription retrieved successfully", prescriptionService.getPrescriptionByVisitId(visitId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(404, e.getMessage()));
        }
    }

    @DeleteMapping("/{visitId}")
    public ResponseEntity<ApiResponse> deletePrescriptionByVisitId(@PathVariable String visitId) {
        try {
            prescriptionService.deletePrescriptionByVisitId(visitId);
            return ResponseEntity.ok(new ApiResponse(200, "Prescription deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(404, e.getMessage()));
        }
    }
}

package com.example.hospital_management.controller;

//import com.example.hospital_management.dto.MedicineRequest;
//import com.example.hospital_management.entity.Prescription;

import com.example.hospital_management.entity.Prescription;
import com.example.hospital_management.exception.CustomException;
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

    @PostMapping
    public ResponseEntity<ApiResponse> createPrescription(@RequestBody Prescription prescription) {
        try {
            Prescription savedPrescription = prescriptionService.createPrescription(prescription);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Prescription created successfully", savedPrescription));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

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

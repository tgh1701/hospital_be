package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Prescription;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        if (prescriptions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse(204, "No prescriptions found"));
        }
        return ResponseEntity.ok(new ApiResponse(200, "Prescriptions retrieved successfully", prescriptions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPrescriptionById(@PathVariable String id) {
        try {
            Prescription prescription = prescriptionService.getPrescriptionById(id);
            return ResponseEntity.ok(new ApiResponse(200, "Prescription retrieved successfully", prescription));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPrescription(@RequestBody Prescription prescription) {
        try {
            Prescription savedPrescription = prescriptionService.savePrescription(prescription);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Prescription created successfully", savedPrescription));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePrescription(@PathVariable String id, @RequestBody Prescription prescription) {
        try {
            prescription.setPrescriptionId(id);
            Prescription updatedPrescription = prescriptionService.updatePrescription(id, prescription);
            return ResponseEntity.ok(new ApiResponse(200, "Prescription updated successfully", updatedPrescription));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePrescription(@PathVariable String id) {
        try {
            ApiResponse response = prescriptionService.deletePrescription(id);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}

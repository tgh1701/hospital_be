package com.example.hospital_management.controller;

import com.example.hospital_management.entity.PrescriptionMedicine;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.PrescriptionMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescription-medicines")
public class PrescriptionMedicineController {
    @Autowired
    private PrescriptionMedicineService prescriptionMedicineService;

    @PostMapping
    public ResponseEntity<ApiResponse> createPrescriptionMedicine(@RequestBody PrescriptionMedicine prescriptionMedicine) {
        try {
            PrescriptionMedicine savedPrescriptionMedicine = prescriptionMedicineService.createPrescriptionMedicine(prescriptionMedicine);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Prescription medicine created successfully", savedPrescriptionMedicine));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

}
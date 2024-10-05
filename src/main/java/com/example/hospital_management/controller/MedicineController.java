package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Medicine;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMedicines() {
        try {
            List<Medicine> medicines = medicineService.getAllMedicines();
            if (medicines.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse(204, "No medicines found"));
            }
            return ResponseEntity.ok(new ApiResponse(200, "Medicines retrieved successfully", medicines));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(500, "An unexpected error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMedicineById(@PathVariable String id) {
        try {
            Medicine medicine = medicineService.getMedicineById(id);
            return ResponseEntity.ok(new ApiResponse(200, "Medicine retrieved successfully", medicine));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createMedicine(@RequestBody Medicine medicine) {
        try {
            Medicine savedMedicine = medicineService.saveMedicine(medicine);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Medicine created successfully", savedMedicine));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMedicine(@PathVariable String id, @RequestBody Medicine medicine) {
        try {
            medicine.setMedicineId(id);
            Medicine updatedMedicine = medicineService.updateMedicine(id, medicine);
            return ResponseEntity.ok(new ApiResponse(200, "Medicine updated successfully", updatedMedicine));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMedicine(@PathVariable String id) {
        try {
            ApiResponse response = medicineService.deleteMedicine(id);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}

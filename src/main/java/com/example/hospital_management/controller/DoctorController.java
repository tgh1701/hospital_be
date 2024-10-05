package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Doctor;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            if (doctors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse(204, "No doctors found"));
            }
            return ResponseEntity.ok(new ApiResponse(200, "Doctors retrieved successfully", doctors));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(500, "An unexpected error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDoctorById(@PathVariable String id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            return ResponseEntity.ok(new ApiResponse(200, "Doctor retrieved successfully", doctor));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor savedDoctor = doctorService.saveDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Doctor created successfully", savedDoctor));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
        try {
            doctor.setDoctorId(id);
            Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
            return ResponseEntity.ok(new ApiResponse(200, "Doctor updated successfully", updatedDoctor));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable String id) {
        try {
            ApiResponse response = doctorService.deleteDoctor(id);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}

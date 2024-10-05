package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Patient;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPatients() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            if (patients.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse(204, "No patients found"));
            }
            return ResponseEntity.ok(new ApiResponse(200, "Patients retrieved successfully", patients));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(500, "An unexpected error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPatientById(@PathVariable String id) {
        try {
            Patient patient = patientService.getPatientById(id);
            return ResponseEntity.ok(new ApiResponse(200, "Patient retrieved successfully", patient));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPatient(@RequestBody Patient patient) {
        try {
            Patient savedPatient = patientService.savePatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Patient created successfully", savedPatient));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePatient(@PathVariable String id, @RequestBody Patient patient) {
        try {
            patient.setPatientId(id);
            Patient updatedPatient = patientService.updatePatient(id, patient);
            return ResponseEntity.ok(new ApiResponse(200, "Patient updated successfully", updatedPatient));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePatient(@PathVariable String id) {
        try {
            ApiResponse response = patientService.deletePatient(id);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}

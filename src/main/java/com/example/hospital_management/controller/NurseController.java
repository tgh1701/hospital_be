package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Nurse;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {
    @Autowired
    private NurseService nurseService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllNurses() {
        List<Nurse> nurses = nurseService.getAllNurses();
        if (nurses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse(204, "No nurses found"));
        }
        return ResponseEntity.ok(new ApiResponse(200, "Nurses retrieved successfully", nurses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getNurseById(@PathVariable String id) {
        try {
            Nurse nurse = nurseService.getNurseById(id);
            return ResponseEntity.ok(new ApiResponse(200, "Nurse retrieved successfully", nurse));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createNurse(@RequestBody Nurse nurse) {
        try {
            Nurse savedNurse = nurseService.saveNurse(nurse);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Nurse created successfully", savedNurse));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateNurse(@PathVariable String id, @RequestBody Nurse nurse) {
        try {
            nurse.setNurseId(id);
            Nurse updatedNurse = nurseService.updateNurse(id, nurse);
            return ResponseEntity.ok(new ApiResponse(200, "Nurse updated successfully", updatedNurse));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteNurse(@PathVariable String id) {
        try {
            ApiResponse response = nurseService.deleteNurse(id);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}

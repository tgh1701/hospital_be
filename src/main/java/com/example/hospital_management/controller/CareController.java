package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Care;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.CareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/care")
public class CareController {
    @Autowired
    private CareService careService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCares() {
        List<Care> cares = careService.getAllCares();
        if (cares.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse(204, "No cares found"));
        }
        return ResponseEntity.ok(new ApiResponse(200, "Cares retrieved successfully", cares));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCareById(@PathVariable String id) {
        try {
            Care care = careService.getCareById(id);
            return ResponseEntity.ok(new ApiResponse(200, "Care retrieved successfully", care));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCare(@RequestBody Care care) {
        try {
            Care savedCare = careService.saveCare(care);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Care created successfully", savedCare));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCare(@PathVariable String id, @RequestBody Care care) {
        try {
            care.setCareId(id);
            Care updatedCare = careService.updateCare(id, care);
            return ResponseEntity.ok(new ApiResponse(200, "Care updated successfully", updatedCare));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCare(@PathVariable String id) {
        try {
            ApiResponse response = careService.deleteCare(id);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}

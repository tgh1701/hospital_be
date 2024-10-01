package com.example.hospital_management.controller;

import com.example.hospital_management.dto.*;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.response.ApiResponse;
import com.example.hospital_management.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/diseases")
    public ResponseEntity<ApiResponse> getDiseaseStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<DiseaseStatDTO> stats = statisticsService.getDiseaseStatistics(startDate, endDate);
            return ResponseEntity.ok(new ApiResponse(200, "Disease statistics retrieved successfully", stats));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @GetMapping("/doctor-salaries")
    public ResponseEntity<ApiResponse> getDoctorSalaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<DoctorSalaryDTO> salaries = statisticsService.getDoctorSalaries(startDate, endDate);
            return ResponseEntity.ok(new ApiResponse(200, "Doctor salaries retrieved successfully", salaries));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @GetMapping("/nurse-salaries")
    public ResponseEntity<ApiResponse> getNurseSalaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<NurseSalaryDTO> salaries = statisticsService.getNurseSalaries(startDate, endDate);
            return ResponseEntity.ok(new ApiResponse(200, "Nurse salaries retrieved successfully", salaries));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @GetMapping("/patient-info/{patientId}")
    public ResponseEntity<ApiResponse> getPatientInfo(@PathVariable String patientId) {
        try {
            List<PatientVisitDTO> patientInfo = statisticsService.getPatientInfo(patientId);
            return ResponseEntity.ok(new ApiResponse(200, "Patient information retrieved successfully", patientInfo));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }

    @GetMapping("/total-revenue")
    public ResponseEntity<ApiResponse> getTotalRevenue(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            TotalRevenueDTO revenue = statisticsService.getTotalRevenue(startDate, endDate);
            return ResponseEntity.ok(new ApiResponse(200, "Total revenue retrieved successfully", revenue));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new ApiResponse(e.getStatusCode(), e.getMessage()));
        }
    }
}
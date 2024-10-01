package com.example.hospital_management.dto;

import java.time.LocalDate;

public record PatientVisitDTO(
        String patientName,
        String visitId,
        LocalDate dateIn,
        LocalDate dateOut,
        String diseaseName,
        double totalPrice,
        int visitSequence,
        String status
) {}
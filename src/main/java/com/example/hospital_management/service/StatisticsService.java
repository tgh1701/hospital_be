package com.example.hospital_management.service;

import com.example.hospital_management.dto.*;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public List<DiseaseStatDTO> getDiseaseStatistics(LocalDate startDate, LocalDate endDate) {
        List<DiseaseStatDTO> stats = statisticsRepository.getDiseaseStatistics(startDate, endDate);
        if (stats.isEmpty()) {
            throw new CustomException(404, "No disease statistics found for the given date range.");
        }
        return stats;
    }

    public List<DoctorSalaryDTO> getDoctorSalaries(LocalDate startDate, LocalDate endDate) {
        List<DoctorSalaryDTO> salaries = statisticsRepository.getDoctorSalaries(startDate, endDate);
        if (salaries.isEmpty()) {
            throw new CustomException(404, "No doctor salaries found for the given date range.");
        }
        return salaries;
    }

    public List<NurseSalaryDTO> getNurseSalaries(LocalDate startDate, LocalDate endDate) {
        List<NurseSalaryDTO> salaries = statisticsRepository.getNurseSalaries(startDate, endDate);
        if (salaries.isEmpty()) {
            throw new CustomException(404, "No nurse salaries found for the given date range.");
        }
        return salaries;
    }

    public List<PatientVisitDTO> getPatientInfo(String patientId) {
        List<PatientVisitDTO> patientInfo = statisticsRepository.getPatientInfo(patientId);
        if (patientInfo.isEmpty()) {
            throw new CustomException(404, "No patient information found for the given patient ID.");
        }
        return patientInfo;
    }

    public TotalRevenueDTO getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        TotalRevenueDTO revenue = statisticsRepository.getTotalRevenue(startDate, endDate);
        if (revenue == null || revenue.totalRevenue() == 0) {
            throw new CustomException(404, "No revenue data found for the given date range.");
        }
        return revenue;
    }
}
package com.example.hospital_management.service;

import com.example.hospital_management.entity.Patient;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.PatientRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(String id) {
        return patientRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Patient with ID " + id + " not found."));
    }

    public Patient savePatient(Patient patient) {
        if (patient.getPatientId() != null && patientRepository.existsById(patient.getPatientId())) {
            throw new CustomException(409, "Patient with ID " + patient.getPatientId() + " already exists.");
        }
        return patientRepository.save(patient);
    }

    public ApiResponse deletePatient(String id) {
        if (!patientRepository.existsById(id)) {
            throw new CustomException(404, "Patient with ID " + id + " not found.");
        }
        try {
            patientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this patient as they have existing records.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

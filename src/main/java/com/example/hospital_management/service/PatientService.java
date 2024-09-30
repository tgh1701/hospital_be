package com.example.hospital_management.service;

import com.example.hospital_management.entity.Patient; // Nếu bạn có class Patient, hãy import
import com.example.hospital_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return patientRepository.findById(id).orElse(null);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}

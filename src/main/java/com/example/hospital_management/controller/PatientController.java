package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Patient; // Nếu bạn có class Patient, hãy import
import com.example.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable String id, @RequestBody Patient patient) {
        patient.setPatientId(id); // Cập nhật ID nếu cần thiết
        return patientService.savePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }
}

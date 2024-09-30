package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Prescription; // Nếu bạn có class Prescription, hãy import
import com.example.hospital_management.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public Prescription getPrescriptionById(@PathVariable String id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @PostMapping
    public Prescription createPrescription(@RequestBody Prescription prescription) {
        return prescriptionService.savePrescription(prescription);
    }

    @PutMapping("/{id}")
    public Prescription updatePrescription(@PathVariable String id, @RequestBody Prescription prescription) {
        prescription.setPrescriptionId(id); // Cập nhật ID nếu cần thiết
        return prescriptionService.savePrescription(prescription);
    }

    @DeleteMapping("/{id}")
    public void deletePrescription(@PathVariable String id) {
        prescriptionService.deletePrescription(id);
    }
}

package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Medicine; // Nếu bạn có class Medicine, hãy import
import com.example.hospital_management.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public Medicine getMedicineById(@PathVariable String id) {
        return medicineService.getMedicineById(id);
    }

    @PostMapping
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineService.saveMedicine(medicine);
    }

    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable String id, @RequestBody Medicine medicine) {
        medicine.setMedicineId(id); // Cập nhật ID nếu cần thiết
        return medicineService.saveMedicine(medicine);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable String id) {
        medicineService.deleteMedicine(id);
    }
}

package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Care; // Nếu bạn có class Care, hãy import
import com.example.hospital_management.service.CareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/care")
public class CareController {
    @Autowired
    private CareService careService;

    @GetMapping
    public List<Care> getAllCares() {
        return careService.getAllCares();
    }

    @GetMapping("/{id}")
    public Care getCareById(@PathVariable String id) {
        return careService.getCareById(id);
    }

    @PostMapping
    public Care createCare(@RequestBody Care care) {
        return careService.saveCare(care);
    }

    @PutMapping("/{id}")
    public Care updateCare(@PathVariable String id, @RequestBody Care care) {
        care.setCareId(id); // Cập nhật ID nếu cần thiết
        return careService.saveCare(care);
    }

    @DeleteMapping("/{id}")
    public void deleteCare(@PathVariable String id) {
        careService.deleteCare(id);
    }
}

package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Nurse;
import com.example.hospital_management.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {
    @Autowired
    private NurseService nurseService;

    @GetMapping
    public List<Nurse> getAllNurses() {
        return nurseService.getAllNurses();
    }

    @GetMapping("/{id}")
    public Nurse getNurseById(@PathVariable String id) {
        return nurseService.getNurseById(id);
    }

    @PostMapping
    public Nurse createNurse(@RequestBody Nurse nurse) {
        return nurseService.saveNurse(nurse);
    }

    @PutMapping("/{id}")
    public Nurse updateNurse(@PathVariable String id, @RequestBody Nurse nurse) {
        nurse.setNurseId(id);
        return nurseService.saveNurse(nurse);
    }

    @DeleteMapping("/{id}")
    public void deleteNurse(@PathVariable String id) {
        nurseService.deleteNurse(id);
    }
}

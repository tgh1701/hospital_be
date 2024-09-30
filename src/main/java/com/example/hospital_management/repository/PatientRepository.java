package com.example.hospital_management.repository;

import com.example.hospital_management.entity.Patient; // Nếu bạn có class Patient, hãy import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}

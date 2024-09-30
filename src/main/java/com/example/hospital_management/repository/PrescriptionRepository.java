package com.example.hospital_management.repository;

import com.example.hospital_management.entity.Prescription; // Nếu bạn có class Prescription, hãy import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
}

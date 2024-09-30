package com.example.hospital_management.repository;

import com.example.hospital_management.entity.Medicine; // Nếu bạn có class Medicine, hãy import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {
}

package com.example.hospital_management.repository;

import com.example.hospital_management.entity.Disease; // Nếu bạn có class Disease, hãy import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, String> {
}

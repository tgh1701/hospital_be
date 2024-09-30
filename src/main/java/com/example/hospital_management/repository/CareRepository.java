package com.example.hospital_management.repository;

import com.example.hospital_management.entity.Care; // Nếu bạn có class Care, hãy import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareRepository extends JpaRepository<Care, String> {
}

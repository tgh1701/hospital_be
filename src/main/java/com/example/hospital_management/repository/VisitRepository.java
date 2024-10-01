package com.example.hospital_management.repository;

import com.example.hospital_management.dto.VisitInfoDTO;
import com.example.hospital_management.entity.Visit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitRepository extends CrudRepository<Visit, String> {

    @Query("SELECT new com.example.hospital_management.dto.VisitInfoDTO(v.visitId, p.patientName, d.doctorName, dis.diseaseName, v.dateIn, v.dateOut, v.totalPrice, v.status, n.nurseName) " +
            "FROM Visit v " +
            "JOIN Patient p ON v.patientId = p.patientId " +
            "JOIN Doctor d ON v.doctorId = d.doctorId " +
            "JOIN Disease dis ON v.diseaseId = dis.diseaseId " +
            "LEFT JOIN Care c ON v.visitId = c.visitId " +
            "LEFT JOIN Nurse n ON c.nurseId = n.nurseId")
    List<VisitInfoDTO> findAllVisitInfo();
}

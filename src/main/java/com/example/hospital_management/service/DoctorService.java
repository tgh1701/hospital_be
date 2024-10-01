package com.example.hospital_management.service;

import com.example.hospital_management.entity.Doctor;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.DoctorRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors;
    }

    public Doctor getDoctorById(String id) {
        return doctorRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Doctor with ID " + id + " not found."));
    }

    public Doctor saveDoctor(Doctor doctor) {
        if (doctor.getDoctorId() != null && doctorRepository.existsById(doctor.getDoctorId())) {
            throw new CustomException(409, "Doctor with ID " + doctor.getDoctorId() + " already exists.");
        }
        return doctorRepository.save(doctor);
    }

    public ApiResponse deleteDoctor(String id) {
        if (!doctorRepository.existsById(id)) {
            throw new CustomException(404, "Doctor with ID " + id + " not found.");
        }
        try {
            doctorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this doctor as they have existing visits.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

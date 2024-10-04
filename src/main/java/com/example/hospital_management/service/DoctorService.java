package com.example.hospital_management.service;

import com.example.hospital_management.entity.Doctor;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.DoctorRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
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

    public Doctor updateDoctor(String id, Doctor updatedDoctor) throws CustomException {
        Optional<Doctor> existingDoctorOptional = doctorRepository.findById(id);
        if (existingDoctorOptional.isPresent()) {
            Doctor existingDoctor = existingDoctorOptional.get();

            existingDoctor.setDoctorName(updatedDoctor.getDoctorName());
            existingDoctor.setIdentityCard(updatedDoctor.getIdentityCard());
            existingDoctor.setDateOfBirth(updatedDoctor.getDateOfBirth());
            existingDoctor.setAddress(updatedDoctor.getAddress());
            existingDoctor.setCareerLevel(updatedDoctor.getCareerLevel());
            existingDoctor.setSeniority(updatedDoctor.getSeniority());
            existingDoctor.setLevel(updatedDoctor.getLevel());
            existingDoctor.setDepartment(updatedDoctor.getDepartment());

            return doctorRepository.save(existingDoctor);
        } else {
            throw new CustomException(404, "Doctor not found");
        }
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

package com.example.hospital_management.service;

import com.example.hospital_management.entity.Department;
import com.example.hospital_management.exception.CustomException;
import com.example.hospital_management.repository.DepartmentRepository;
import com.example.hospital_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(String id) {
        return departmentRepository.findById(id).orElseThrow(() ->
                new CustomException(404, "Department with ID " + id + " not found."));
    }

    public Department saveDepartment(Department department) {
        if (department.getDepartmentId() != null && departmentRepository.existsById(department.getDepartmentId())) {
            throw new CustomException(409, "Department with ID " + department.getDepartmentId() + " already exists.");
        }
        return departmentRepository.save(department);
    }

    public Department updateDepartment(String id, Department updatedDepartment) {
        Optional<Department> existingDepartmentOptional = departmentRepository.findById(id);
        if (existingDepartmentOptional.isPresent()) {
            Department existingDepartment = existingDepartmentOptional.get();
            existingDepartment.setDepartmentName(updatedDepartment.getDepartmentName());
            return departmentRepository.save(existingDepartment);
        } else {
            throw new CustomException(404, "Department not found");
        }
    }

    public ApiResponse deleteDepartment(String id) {
        if (!departmentRepository.existsById(id)) {
            throw new CustomException(404, "Department with ID " + id + " not found.");
        }
        try {
            departmentRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(400, "Cannot delete this department as it is associated with existing records.");
        }
        return new ApiResponse(200, "Delete Success");
    }
}

package com.example.hospital_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @Column(name = "Departmentid", length = 10)
    private String departmentId;

    @Column(name = "Departmentname", length = 50)
    private String departmentName;

    // Getters and Setters
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    // toString method
    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department department)) return false;
        return Objects.equals(departmentId, department.departmentId) &&
                Objects.equals(departmentName, department.departmentName);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName);
    }
}

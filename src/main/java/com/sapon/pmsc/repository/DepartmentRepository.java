package com.sapon.pmsc.repository;

import com.sapon.pmsc.model.Clinic;
import com.sapon.pmsc.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    @Query("SELECT s FROM Department s where s.name = ?1")
    Optional<Department> findDepartmentByName(String name);
}
package com.mnov34.CUBES4solo.repository;

import com.mnov34.CUBES4solo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByDepartmentName(String departmentName);
}

package com.mnov34.CUBES4solo.service;

import com.mnov34.CUBES4solo.model.Department;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface DepartmentService {
    List<Department> getAllDepartment();

    Department getDepartmentById(Long id);

    Department createDepartment(Department Department);

    Department updateDepartment(Long id, Department Department);

    void deleteDepartmentById(Long id);
}

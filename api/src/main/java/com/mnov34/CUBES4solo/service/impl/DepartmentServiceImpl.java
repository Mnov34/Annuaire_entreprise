package com.mnov34.CUBES4solo.service.impl;

import com.mnov34.CUBES4solo.exception.OperationNotAllowedException;
import com.mnov34.CUBES4solo.model.Department;
import com.mnov34.CUBES4solo.repository.DepartmentRepository;
import com.mnov34.CUBES4solo.repository.EmployeeRepository;
import com.mnov34.CUBES4solo.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentServiceImpl(DepartmentRepository serviceRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = serviceRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existingDepartment.setDepartmentName(department.getDepartmentName());

        return departmentRepository.saveAndFlush(existingDepartment);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));

        if (employeeRepository.existsByDepartment(department)) {
            throw new OperationNotAllowedException(
                    "Cannot delete department '" + department.getDepartmentName() + "' - it has associated employees"
            );
        }

        departmentRepository.deleteById(id);

    }
}

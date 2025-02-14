package com.mnov34.CUBES4solo.service;

import com.mnov34.CUBES4solo.dto.EmployeeDto;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployeeById(Long id);
}

package com.mnov34.CUBES4solo.service;

import com.mnov34.CUBES4solo.model.Employee;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployeeById(Long id);
}

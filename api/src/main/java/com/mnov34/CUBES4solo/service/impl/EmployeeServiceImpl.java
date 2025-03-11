package com.mnov34.CUBES4solo.service.impl;

import com.mnov34.CUBES4solo.model.Employee;
import com.mnov34.CUBES4solo.repository.EmployeeRepository;
import com.mnov34.CUBES4solo.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee Employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setFirstName(Employee.getFirstName());
        existingEmployee.setLastName(Employee.getLastName());
        existingEmployee.setPhone(Employee.getPhone());
        existingEmployee.setMobilePhone(Employee.getMobilePhone());
        existingEmployee.setEmail(Employee.getEmail());
        existingEmployee.setSite(Employee.getSite());
        existingEmployee.setDepartment(Employee.getDepartment());

        return employeeRepository.saveAndFlush(existingEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

}

package com.mnov34.CUBES4solo.service.impl;

import com.mnov34.CUBES4solo.dto.EmployeeDto;
import com.mnov34.CUBES4solo.mapper.EmployeeMapper;
import com.mnov34.CUBES4solo.model.Employee;
import com.mnov34.CUBES4solo.repository.EmployeeRepository;
import com.mnov34.CUBES4solo.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }




    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setPhone(employeeDto.getPhone());
        existingEmployee.setMobilePhone(employeeDto.getMobilePhone());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setSite(employeeMapper.mapSiteIdToSite(employeeDto.getSiteId()));
        existingEmployee.setService(employeeMapper.mapServiceIdToService(employeeDto.getServiceId()));

        Employee updatedEmployee = employeeRepository.saveAndFlush(existingEmployee);
        return employeeMapper.toDto(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

}

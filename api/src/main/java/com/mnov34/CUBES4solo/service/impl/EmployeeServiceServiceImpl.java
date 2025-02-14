package com.mnov34.CUBES4solo.service.impl;

import com.mnov34.CUBES4solo.dto.EmployeeServiceDto;
import com.mnov34.CUBES4solo.mapper.EmployeeServiceMapper;
import com.mnov34.CUBES4solo.repository.EmployeeServiceRepository;
import com.mnov34.CUBES4solo.service.EmployeeServiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Service
public class EmployeeServiceServiceImpl implements EmployeeServiceService {
    private final EmployeeServiceRepository serviceRepository;
    private final EmployeeServiceMapper serviceMapper;

    public EmployeeServiceServiceImpl(EmployeeServiceRepository serviceRepository, EmployeeServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    public List<EmployeeServiceDto> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}

package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.dto.EmployeeServiceDto;
import com.mnov34.CUBES4solo.service.EmployeeServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@RestController
@RequestMapping("/api/services")
public class EmployeeServiceController {
    private final EmployeeServiceService serviceService;

    public EmployeeServiceController(EmployeeServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<EmployeeServiceDto> getAllSites() {
        return serviceService.getAllServices();
    }
}

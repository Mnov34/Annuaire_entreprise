package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.model.Department;
import com.mnov34.CUBES4solo.service.DepartmentService;
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
public class DepartmentController {
    private final DepartmentService serviceService;

    public DepartmentController(DepartmentService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Department> getAllSites() {
        return serviceService.getAllDepartment();
    }
}

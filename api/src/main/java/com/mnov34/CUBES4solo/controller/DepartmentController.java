package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.model.Department;
import com.mnov34.CUBES4solo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@RestController
@RequestMapping("/api/services")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllSites() {
        return departmentService.getAllDepartment();
    }

    @DeleteMapping("/{id}")
    public void deleteSiteById(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
    }
}

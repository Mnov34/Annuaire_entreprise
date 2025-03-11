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
    public List<Department> getAllServices() {
        return departmentService.getAllDepartment();
    }

    @PostMapping
    public Department createService(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{id}")
    public Department updateService(@PathVariable("id") Long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceById(@PathVariable("id") Long id) {
        departmentService.deleteDepartmentById(id);
    }
}

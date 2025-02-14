package com.mnov34.CUBES4solo.repository;

import com.mnov34.CUBES4solo.model.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface EmployeeServiceRepository extends JpaRepository<EmployeeService, Long> {
    Optional<EmployeeService> findByServiceName(String nomService);
}

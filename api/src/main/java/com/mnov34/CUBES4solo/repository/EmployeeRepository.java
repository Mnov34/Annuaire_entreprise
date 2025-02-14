package com.mnov34.CUBES4solo.repository;

import com.mnov34.CUBES4solo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastNameContainingIgnoreCase(String partialName);

    List<Employee> findBySiteId(Long siteId);

    List<Employee> findByServiceId(Long serviceId);
}

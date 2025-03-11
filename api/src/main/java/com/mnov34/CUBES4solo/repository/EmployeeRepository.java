package com.mnov34.CUBES4solo.repository;

import com.mnov34.CUBES4solo.model.Department;
import com.mnov34.CUBES4solo.model.Employee;
import com.mnov34.CUBES4solo.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastNameContainingIgnoreCaseAndSite_SiteContainingIgnoreCaseAndDepartment_DepartmentNameContainingIgnoreCase(
            String lastName, String site, String department);

    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findByLastNameContainingIgnoreCase(String lastName);

    List<Employee> findByDepartmentId(Long serviceId);

    List<Employee> findBySiteId(Long siteId);

    boolean existsByDepartment(Department department);

    boolean existsBySite(Site site);
}

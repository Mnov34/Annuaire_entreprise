package com.mnov34.CUBES4solo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name", nullable = false)
    private String serviceName;
}
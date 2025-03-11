package com.mnov34.CUBES4solo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maël NOUVEL <br>
 * 10/03/2025
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String mobilePhone;
    private String email;
    private Department department;
    private Site site;
}
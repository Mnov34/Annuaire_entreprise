package com.mnov34.CUBES4solo.dto;

import lombok.Data;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private EmployeeDto employee;
}

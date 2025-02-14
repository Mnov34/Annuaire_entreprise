package com.mnov34.CUBES4solo.dto;

import lombok.Data;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String mobilePhone;
    private String email;

    private Long serviceId;
    private Long siteId;

    public String getFirstAndLastName() {
        return firstName + " " + lastName;
    }
}

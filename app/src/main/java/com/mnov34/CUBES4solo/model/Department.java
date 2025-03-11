package com.mnov34.CUBES4solo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maël NOUVEL <br>
 * 10/03/2025
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Long id;
    private String departmentName;

    public Department(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return departmentName;
    }
}

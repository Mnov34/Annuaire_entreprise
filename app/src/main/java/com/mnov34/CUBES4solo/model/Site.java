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
public class Site {
    private Long id;
    private String site;

    @Override
    public String toString() {
        return site;
    }
}

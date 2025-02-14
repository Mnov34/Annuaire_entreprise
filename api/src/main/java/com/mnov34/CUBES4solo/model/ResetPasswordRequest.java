package com.mnov34.CUBES4solo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Getter
@Setter
public class ResetPasswordRequest {
    private String token;
    private String newPassword;
}

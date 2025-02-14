package com.mnov34.CUBES4solo.exception;

import lombok.Getter;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Getter
public class CustomException extends RuntimeException {
    private final int status;
    private final String error;

    public CustomException(String message, int status, String error) {
        super(message);
        this.status = status;
        this.error = error;
    }
}

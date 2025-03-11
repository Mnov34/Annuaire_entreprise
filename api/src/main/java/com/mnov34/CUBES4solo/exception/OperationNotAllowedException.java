package com.mnov34.CUBES4solo.exception;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public class OperationNotAllowedException extends RuntimeException {
    public OperationNotAllowedException(String message) {
        super(message);
    }
}

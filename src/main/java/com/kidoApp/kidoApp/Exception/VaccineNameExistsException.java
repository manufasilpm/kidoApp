package com.kidoApp.kidoApp.Exception;

public class VaccineNameExistsException extends RuntimeException {
    public VaccineNameExistsException(String message) {
        super(message);
    }
}
package com.kidoApp.kidoApp.dto;

public class AppointmentResponseDTO {
    private String message;

    public AppointmentResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
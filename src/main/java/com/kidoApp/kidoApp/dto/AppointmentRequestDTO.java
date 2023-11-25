package com.kidoApp.kidoApp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentRequestDTO {
    private Long hospitalId;
    private Long vaccineId;
    private String appointmentDate;

}


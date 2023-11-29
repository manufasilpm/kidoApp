package com.kidoApp.kidoApp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentRequestDTO {
    private Long hospitalId;
    private Long vaccineId;
    private LocalDateTime appointmentDate;

}


package com.kidoApp.kidoApp.dto;

import com.kidoApp.kidoApp.model.Child;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AppointmentDetailsDTO {

    private Long id;
    private ChildDTO child;
    private LocalDateTime appointmentDate;

    public AppointmentDetailsDTO(Long id, ChildDTO child, LocalDateTime appointmentDate) {
        this.id = id;
        this.child = child;
        this.appointmentDate = appointmentDate;
    }
}
package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.dto.AppointmentRequestDTO;
import com.kidoApp.kidoApp.dto.AppointmentResponseDTO;
import com.kidoApp.kidoApp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")

public class AppointmentController{
    @Autowired
    private AppointmentService appointmentService;

    // Other CRUD operations...

    @PostMapping("/addChildAppointment")
    public ResponseEntity<?> addChildAppointment(@RequestParam Long childId, @RequestBody AppointmentRequestDTO appointmentRequest) {
        try {
            appointmentService.addChildAppointment(childId, appointmentRequest);
            // Assuming you have a class AppointmentResponseDTO with a message property
            AppointmentResponseDTO response = new AppointmentResponseDTO("Appointment added successfully.");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding appointment.");
        }
    }


}

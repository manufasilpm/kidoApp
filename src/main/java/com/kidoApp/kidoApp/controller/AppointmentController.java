package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.dto.APIResponseDTO;
import com.kidoApp.kidoApp.dto.AppointmentRequestDTO;
import com.kidoApp.kidoApp.dto.AppointmentResponseDTO;
import com.kidoApp.kidoApp.services.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")

public class AppointmentController{
    @Autowired
    private AppointmentService appointmentService;


    @PostMapping("/addChildAppointment")
    public ResponseEntity<APIResponseDTO> addChildAppointment(@RequestParam Long childId, @RequestBody AppointmentRequestDTO appointmentRequest) {
        try {
            appointmentService.addChildAppointment(childId, appointmentRequest);

            APIResponseDTO response = new APIResponseDTO();
            response.setMessage("Appointment added successfully.");

            return ResponseEntity.ok().body(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponseDTO("Error: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponseDTO("Error adding appointment: " + e.getMessage()));
        }
    }


//     try {
//            childService.addChild(parentId, childRequest);
//            return ResponseEntity.ok().body();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error adding child.\"}");
//        }


}

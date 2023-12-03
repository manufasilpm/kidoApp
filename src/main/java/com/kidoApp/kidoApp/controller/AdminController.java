package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.model.Appointment;
import com.kidoApp.kidoApp.services.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final AppointmentService appointmentService;

    public AdminController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }





}

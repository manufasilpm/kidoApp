package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.dto.AppointmentRequestDTO;
import com.kidoApp.kidoApp.model.Appointment;
import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Vaccine;
import com.kidoApp.kidoApp.repository.AppointmentRepository;
import com.kidoApp.kidoApp.repository.ChildRepository;
import com.kidoApp.kidoApp.repository.HospitalRepository;
import com.kidoApp.kidoApp.repository.VaccineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public ResponseEntity<?>  addChildAppointment(Long childId, AppointmentRequestDTO appointmentRequest) {
        try {
            Child child = childRepository.findById(childId)
                    .orElseThrow(() -> new EntityNotFoundException("Child not found"));

            Hospital hospital = hospitalRepository.findById(appointmentRequest.getHospitalId())
                    .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));

            Vaccine vaccine = vaccineRepository.findById(appointmentRequest.getVaccineId())
                    .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));

            Appointment appointment = new Appointment();
            appointment.setChild(child);
            appointment.setHospital(hospital);
            appointment.setVaccine(vaccine);
            appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());

            // Set other appointment properties as needed

            // Save the appointment
            Appointment savedAppointment = appointmentRepository.save(appointment);

            // Update the child with the appointment ID
            child.setAppointment(savedAppointment);
            childRepository.save(child);

            return ResponseEntity.ok().body("Appointment added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding appointment.");
        }
    }

}

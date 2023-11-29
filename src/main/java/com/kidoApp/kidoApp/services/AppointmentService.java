package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.dto.APIResponseDTO;
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

    public void addChildAppointment(Long childId, AppointmentRequestDTO appointmentRequest) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        Hospital hospital = hospitalRepository.findById(appointmentRequest.getHospitalId())
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));
        Vaccine vaccine = vaccineRepository.findById(appointmentRequest.getVaccineId())
                .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));

        Appointment appointment = new Appointment();
        appointment.setChild(child);
        child.setLatest_vaccine(appointmentRequest.getAppointmentDate());
        appointment.setHospital(hospital);
        appointment.setVaccine(vaccine);
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());

        Appointment savedAppointment = appointmentRepository.save(appointment);

        child.setAppointment(savedAppointment);
        childRepository.save(child);



//        try {
//            Child child = childRepository.findById(childId)
//                    .orElseThrow(() -> new EntityNotFoundException("Child not found"));
//
//            Hospital hospital = hospitalRepository.findById(appointmentRequest.getHospitalId())
//                    .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));
//            Vaccine vaccine = vaccineRepository.findById(appointmentRequest.getVaccineId())
//                    .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));
//            Appointment appointment = new Appointment();
//            appointment.setChild(child);
//            appointment.setHospital(hospital);
//            appointment.setVaccine(vaccine);
//            appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
//
//            Appointment savedAppointment = appointmentRepository.save(appointment);
//
//            child.setAppointment(savedAppointment);
//            childRepository.save(child);
//
//            // Return the ResponseEntity with the success response
//            return ResponseEntity.ok().body(new APIResponseDTO("Appointment added successfully."));
//        } catch (EntityNotFoundException e) {
//            // Return the ResponseEntity with the not found response
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponseDTO("Error: " + e.getMessage()));
//        } catch (Exception e) {
//            // Return the ResponseEntity with the internal server error response
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponseDTO("Error adding appointment: " + e.getMessage()));

    }


}

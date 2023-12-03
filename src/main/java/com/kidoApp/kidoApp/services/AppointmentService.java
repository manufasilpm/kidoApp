package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.constants.DayOfWeek;
import com.kidoApp.kidoApp.dto.APIResponseDTO;
import com.kidoApp.kidoApp.dto.AppointmentDetailsDTO;
import com.kidoApp.kidoApp.dto.AppointmentRequestDTO;
import com.kidoApp.kidoApp.model.*;
import com.kidoApp.kidoApp.repository.*;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.kidoApp.kidoApp.constants.DayOfWeek.MONDAY;

@Service
public class AppointmentService {
    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private VaccinationSlotRepository vaccinationSlotRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void addChildAppointment(Long childId, AppointmentRequestDTO appointmentRequest) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        Hospital hospital = hospitalRepository.findById(appointmentRequest.getHospitalId())
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));

        Vaccine vaccine = vaccineRepository.findById(appointmentRequest.getVaccineId())
                .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss z");
        LocalDateTime dateTime =appointmentRequest.getAppointmentDate();
        String dayOfWeek = DayOfWeek.valueOf(dateTime.getDayOfWeek().toString()).name();
        VaccinationSlot vaccinationSlot=vaccinationSlotRepository.findByHospitalHospitalIdAndDayOfWeek(appointmentRequest.getHospitalId(),dayOfWeek);
        if (vaccinationSlot.getSlotCount() <= 0){
            throw new RuntimeException("Slot fully occupied");

        }

        if (vaccinationSlot.getSlotCount() > 0) {
            // Reduce the slot count by 1
            vaccinationSlot.setSlotCount(vaccinationSlot.getSlotCount() - 1);
        } else {
            throw new RuntimeException("No available slots for vaccination.");
        }
        Appointment appointment = new Appointment();
        appointment.setChild(child);

        child.setLatest_vaccine(appointmentRequest.getAppointmentDate().toString());
        appointment.setHospital(hospital);
        appointment.setVaccine(vaccine);
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());

        Appointment savedAppointment = appointmentRepository.save(appointment);

        child.setAppointments(savedAppointment);
        childRepository.save(child);
    }


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }




    // Other dependencies and methods...

//    public void sendEmailNotification(Long childId, AppointmentRequestDTO appointmentRequest) {
//        // Save the appointment...
//
//        // Fetch parent's email
//        String parentEmail = getParentEmailByChildId(childId);
//
//        // Compose email content
//        String subject = "Appointment Notification";
//        String content = "Your child has a new appointment on " + appointmentRequest.getAppointmentDate() + ". Details...";
//
//        // Send email notification
//        try {
//            emailService.sendNotificationEmail(parentEmail, subject, content);
//        } catch (MessagingException e) {
//            // Handle email sending exception
//            e.printStackTrace();
//        }
//
//
//    }
//    private String getParentEmailByChildId(Long childId) {
//        return "parent@example.com";
//    }
//
////    public void sendEmailNotification(Long hospitalId, Long childId, String appointmentDate) {
////    }

    public void sendEmailNotification() throws MessagingException {
        emailService.sendNotificationEmail("manufasilpm@gmail.com","vaccine","hey");
    }







    public List<AppointmentDetailsDTO> getAppointmentsByHospitalId(Long hospitalId) {
        return appointmentRepository.findByHospitalId(hospitalId);
    }
}

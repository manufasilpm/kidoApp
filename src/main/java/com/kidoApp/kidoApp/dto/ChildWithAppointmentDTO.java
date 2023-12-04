package com.kidoApp.kidoApp.dto;

import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Vaccine;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChildWithAppointmentDTO {

    private Long childId;
    private String childName;
    private String gender;
    private String dob;
    private String age;
    private String latestVaccine;
    private String completedVaccine;

    // Include appointment details
    private Long appointmentId;
    private LocalDate appointmentDate;
    private String status;
    private Vaccine vaccine;

    public ChildWithAppointmentDTO(Child child) {
        this.childId = child.getChildId();
        this.childName = child.getChildName();
        this.gender = child.getGender();
        this.dob = child.getDob();
        this.age = child.getAge();
        this.latestVaccine = child.getLatest_vaccine();
        this.completedVaccine = child.getCompleted_vaccine();

        // Check if the child has an appointment
        if (child.getAppointments() != null) {
            this.appointmentId = child.getAppointments().getId();
            this.appointmentDate = LocalDate.from(child.getAppointments().getAppointmentDate());
            this.status=child.getAppointments().getStatus();
            this.vaccine=child.getAppointments().getVaccine();
        }
    }
}
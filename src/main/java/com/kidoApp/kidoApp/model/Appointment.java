package com.kidoApp.kidoApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "child_id")
    private Child child;
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
    @ManyToOne
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;
    @Column(name = "appointment_date", nullable = false)
    private String appointmentDate;

    @Column(name = "status")
    private String status;



}

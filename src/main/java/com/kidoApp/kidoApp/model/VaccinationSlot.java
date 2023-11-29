package com.kidoApp.kidoApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kidoApp.kidoApp.controller.DayOfWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationSlot {

    @Id
    @GeneratedValue
    @Column(name = "slot_id")
    private Long slotId;

    @Column(name = "slot_count")
    private Long slotCount;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    // Constructors, getters, setters, and other methods
}
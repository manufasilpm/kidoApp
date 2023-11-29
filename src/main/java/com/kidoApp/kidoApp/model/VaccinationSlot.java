package com.kidoApp.kidoApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kidoApp.kidoApp.constants.DayOfWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


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
    @Column(name = "from_time")
    private String fromTime;

    @Column(name = "to_time")
    private String toTime;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "hospital_id")  // Assuming this is the ID of the hospital
    private Hospital hospital;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;


}
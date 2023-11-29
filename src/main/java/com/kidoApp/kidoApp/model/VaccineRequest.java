package com.kidoApp.kidoApp.model;

import com.kidoApp.kidoApp.constants.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "vaccine_requests")
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "company")
    private String company;

    @Column(name = "description")
    private String description;
    @Column(name = "vaccineCategory")
    private String vaccineCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus status;

}
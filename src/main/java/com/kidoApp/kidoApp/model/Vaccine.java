package com.kidoApp.kidoApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue
    @Column(name = "vaccine_id")
    public  Long vaccineId;

    @Column(name = "vaccine_name")
    public String vaccineName;

    @Column(name = "vaccine_category")
    public String vaccineCategory;

    @Column(name = "company")
    public String company;

    @Column(name = "description")
    public String description;

    @Column(name = "status")
    public String status;




}

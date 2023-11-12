package com.kidoApp.kidoApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Vaccine {

    @Id
    @GeneratedValue
    @Column(name = "vaccine_id")
    public  Long vaccine_id;

    @Column(name = "vaccine_name")
    public String vaccine_name;

    @Column(name = "vaccine_category")
    public String vaccine_category;

    @Column(name = "company")
    public String company;

    @Column(name = "description")
    public String description;


}

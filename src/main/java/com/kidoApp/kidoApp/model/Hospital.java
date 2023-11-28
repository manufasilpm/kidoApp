package com.kidoApp.kidoApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue
    @Column(name = "hospital_id")
    private  Long hospitalId;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "location")
    private String location;

    @Column(name = "phone_no")
    private String phoneNo;


    @Column(name = "password")
    private   String password;

    @OneToMany(targetEntity = Vaccine.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "Vaccince_fk",referencedColumnName ="hospital_id" )
    private List<Vaccine> vaccine;



}

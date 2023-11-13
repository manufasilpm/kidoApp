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
    private  Long hospital_id;

    @Column(name = "hospital_name")
    public String hospital_name;

    @Column(name = "location")
    public String location;

    @Column(name = "phone_no")
    public String phone_no;


    @Column(name = "password")
    public   String password;

    @OneToMany(targetEntity = Vaccine.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "Vaccince_fk",referencedColumnName ="hospital_id" )
    private List<Vaccine> vaccine;



}

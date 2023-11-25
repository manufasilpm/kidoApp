package com.kidoApp.kidoApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Child {

    @Id
    @GeneratedValue
    @Column(name = "child_id")
    private Long childId;

    @Column(name = "child_name")
    private String childName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private String dob;

    @Column(name = "latest_vaccine")
    private String latest_vaccine;
    @Column(name = "completed_vaccine")
    private String completed_vaccine;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Parent parent;


    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "appoiment_id")
    private Appointment appointment;






}

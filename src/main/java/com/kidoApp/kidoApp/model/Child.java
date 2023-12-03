package com.kidoApp.kidoApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name="age")
    private String age;

    @Column(name = "latest_vaccine")
    private String latest_vaccine;
    @Column(name = "completed_vaccine")
    private String completed_vaccine;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Parent parent;

    @OneToOne(mappedBy = "child")
    @JsonBackReference // Add this line
    private Appointment appointments;


}

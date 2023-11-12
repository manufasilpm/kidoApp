package com.kidoApp.kidoApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Child {

    @Id
    @GeneratedValue
    @Column(name = "child_id")
    public  Long child_id;

    @Column(name = "child_name")
    public String child_name;

    @Column(name = "gender")
    public String gender;

    @Column(name = "dob")
    public String dob;

    @Column(name = "latest_vaccine")
    public String latest_vaccine;
    @Column(name = "completed_vaccine")
    public String completed_vaccine;

    @Column(name = "parent_id")
    public String parent_id;

}

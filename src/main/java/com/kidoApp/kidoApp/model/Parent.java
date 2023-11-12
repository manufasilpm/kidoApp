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
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "parent_id")
    public  Long parent_id;

    @Column(name = "parent_name")
    public String parent_name;

    @Column(name = "password")
    public String password;

    @Column(name = "address")
    public String address;

    @Column(name = "phone_no")
    public String phone_no;


}
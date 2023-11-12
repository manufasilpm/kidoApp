package com.kidoApp.kidoApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
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

    @Column(name = "s_mon")
    public String s_mon;

    @Column(name = "s_tue")
    public String s_tue;


    @Column(name = "s_wed")
    public  String s_wed;
    @Column(name = "s_thurs")
    public  String s_thurs;
    @Column(name = "s_fri")
    public  String s_fri;
    @Column(name = "s_sat")
    public  String s_sat;
    @Column(name = "s_sun")
    public  String s_sun;
    @Column(name = "password")
    public   String password;

    public Hospital(){}

}

package com.kidoApp.kidoApp.dto;

import com.kidoApp.kidoApp.model.Hospital;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HospitalDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;


    public HospitalDTO(Hospital hospital) {
        this.id = hospital.getHospitalId();
        this.name = hospital.getHospitalName();
        this.address = hospital.getLocation();
        this.phoneNumber = hospital.getPhoneNo();

    }
}
package com.kidoApp.kidoApp.dto;

import com.kidoApp.kidoApp.model.Hospital;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HospitalDTO {
    private Long id;
    private String hospitalName;
    private String address;
    private String phoneNo;


    public HospitalDTO(Hospital hospital) {
        this.id = hospital.getHospitalId();
        this.hospitalName = hospital.getHospitalName();
        this.address = hospital.getLocation();
        this.phoneNo = hospital.getPhoneNo();

    }
}
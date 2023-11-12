package com.kidoApp.kidoApp.services;


import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;
    public Hospital createHospital(Hospital hospital) {
        Hospital savedHospital = hospitalRepository.save(hospital);

        return savedHospital;


    }
}

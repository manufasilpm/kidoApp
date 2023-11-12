package com.kidoApp.kidoApp.services;


import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Vaccine;
import com.kidoApp.kidoApp.repository.HospitalRepository;
import com.kidoApp.kidoApp.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccineService {
    @Autowired
    VaccineRepository vaccineRepository;
    public Vaccine createVaccine(Vaccine vaccine) {
        Vaccine savedVaccine = vaccineRepository.save(vaccine);

        return savedVaccine;


    }
}

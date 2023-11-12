package com.kidoApp.kidoApp.controller;


import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.model.Vaccine;
import com.kidoApp.kidoApp.services.ParentService;
import com.kidoApp.kidoApp.services.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vaccine")

public class VaccineController {


    @Autowired
    VaccineService vaccineService;


    @PostMapping("saveVaccine")
    public ResponseEntity<Vaccine> createHospital(@RequestBody Vaccine vaccine) {

        Vaccine savedVaccine = vaccineService.createVaccine(vaccine);


        return new ResponseEntity<>(savedVaccine, HttpStatus.CREATED);
    }
}

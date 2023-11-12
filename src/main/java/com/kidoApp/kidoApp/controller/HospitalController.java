package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.services.HospitalService;
import com.kidoApp.kidoApp.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Hospital")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;


    @PostMapping("saveHospital")
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        // Validate and save the child entity using the ChildService (you'll need to create this service)
        Hospital savedHospital = hospitalService.createHospital(hospital);

        // Return a ResponseEntity with the created child and an HTTP status code
        return new ResponseEntity<>(savedHospital, HttpStatus.CREATED);
    }
}

package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.services.HospitalService;
import com.kidoApp.kidoApp.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getHospital/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) {
        Hospital hospital = hospitalService.getHospitalById(id);
        if (hospital != null) {
            return new ResponseEntity<>(hospital, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateHospital/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable Long id, @RequestBody Hospital updatedHospital) {
        Hospital existingHospital = hospitalService.getHospitalById(id);

        if (existingHospital != null) {

            Hospital savedHospital = hospitalService.createHospital(updatedHospital);
            return new ResponseEntity<>(savedHospital, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteHospital/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        Hospital existingHospital = hospitalService.getHospitalById(id);

        if (existingHospital != null) {
            hospitalService.deleteHospital(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

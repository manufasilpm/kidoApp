package com.kidoApp.kidoApp.controller;


import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.model.Vaccine;
import com.kidoApp.kidoApp.services.ParentService;
import com.kidoApp.kidoApp.services.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/updateVaccine/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Long id, @RequestBody Vaccine updatedVaccine) {
        Vaccine existingVaccine = vaccineService.getVaccineById(id);

        if (existingVaccine != null) {
            updatedVaccine.setId(id);
            Vaccine savedVaccine = vaccineService.createVaccine(updatedVaccine);
            return new ResponseEntity<>(savedVaccine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteVaccine/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        Vaccine existingVaccine = vaccineService.getVaccineById(id);

        if (existingVaccine != null) {
            vaccineService.deleteVaccine(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.dto.HospitalDTO;
import com.kidoApp.kidoApp.dto.HospitalRequestDTO;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.services.HospitalService;
import com.kidoApp.kidoApp.services.ParentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/add")
    public ResponseEntity<?> addHospital(@RequestBody HospitalRequestDTO hospitalRequest) {
        try {
            hospitalService.addHospital(hospitalRequest);
            return ResponseEntity.ok().body("Hospital added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding hospital.");
        }
    }

    @PutMapping("/update/{hospitalId}")
    public ResponseEntity<?> updateHospital(@PathVariable Long hospitalId, @RequestBody HospitalRequestDTO hospitalRequest) {
        try {
            hospitalService.updateHospital(hospitalId, hospitalRequest);
            return ResponseEntity.ok().body("Hospital updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating hospital.");
        }
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<?> getHospital(@PathVariable Long hospitalId) {
        try {
            HospitalDTO hospitalDTO = hospitalService.getHospital(hospitalId);
            return ResponseEntity.ok().body(hospitalDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving hospital.");
        }
    }

    @DeleteMapping("/delete/{hospitalId}")
    public ResponseEntity<?> deleteHospital(@PathVariable Long hospitalId) {
        try {
            hospitalService.deleteHospital(hospitalId);
            return ResponseEntity.ok().body("Hospital deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting hospital.");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllHospitals() {
        try {
            List<HospitalDTO> hospitals = hospitalService.getAllHospitals();
            return ResponseEntity.ok().body(hospitals);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving hospitals.");
        }
    }
}

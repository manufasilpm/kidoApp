package com.kidoApp.kidoApp.controller;


import com.kidoApp.kidoApp.dto.VaccineDTO;
import com.kidoApp.kidoApp.dto.VaccineRequestDTO;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.model.Vaccine;
import com.kidoApp.kidoApp.services.ParentService;
import com.kidoApp.kidoApp.services.VaccineService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("vaccine")

public class VaccineController {


    @Autowired
    private VaccineService vaccineService;

    @PostMapping("/add")
    public ResponseEntity<?> addVaccine(@RequestBody VaccineRequestDTO vaccineRequest) {
        try {
            vaccineService.addVaccine(vaccineRequest);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vaccine added successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding vaccine.");
        }
    }

    @PutMapping("/update/{vaccineId}")
    public ResponseEntity<?> updateVaccine(@PathVariable Long vaccineId, @RequestBody VaccineRequestDTO vaccineRequest) {
        try {
            vaccineService.updateVaccine(vaccineId, vaccineRequest);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vaccine updated successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating vaccine.");
        }
    }

    @GetMapping("/{vaccineId}")
    public ResponseEntity<?> getVaccine(@PathVariable Long vaccineId) {
        try {
            VaccineDTO vaccineDTO = vaccineService.getVaccine(vaccineId);
            return ResponseEntity.ok().body(vaccineDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccine not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving vaccine.");
        }
    }

    @DeleteMapping("/delete/{vaccineId}")
    public ResponseEntity<?> deleteVaccine(@PathVariable Long vaccineId) {
        try {
            vaccineService.deleteVaccine(vaccineId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vaccine deleted successfully.");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccine not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting vaccine.");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try{
            List<Vaccine> vaccines=vaccineService.getAllVaccines();
            return ResponseEntity.ok().body(vaccines);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in retrieving Vaccines ");
        }
    }
}
//try {
//        List<HospitalDTO> hospitals = hospitalService.getAllHospitals();
//        return ResponseEntity.ok().body(hospitals);
//        } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving hospitals.");
//        }
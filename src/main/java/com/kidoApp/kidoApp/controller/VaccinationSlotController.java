package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.constants.DayOfWeek;
import com.kidoApp.kidoApp.dto.SlotRequestDTO;
import com.kidoApp.kidoApp.model.VaccinationSlot;
import com.kidoApp.kidoApp.services.VaccinationSlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccination")
public class VaccinationSlotController {

    @Autowired
    private VaccinationSlotService vaccinationSlotService ;

    @PostMapping("/add-slot")
    public void addSlot(@RequestBody SlotRequestDTO slotRequestDTO) {
        vaccinationSlotService.addSlot(slotRequestDTO);
    }

    @GetMapping("/hospitals-by-day")
    public List<VaccinationSlot> getHospitalsByDay(@RequestParam String dayOfWeek) {
        System.out.println(vaccinationSlotService.getHospitalsByDay(dayOfWeek));
        return vaccinationSlotService.getHospitalsByDay(dayOfWeek);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSlot(@RequestParam Long hospitalId,@RequestParam String dayOfWeek, @RequestBody SlotRequestDTO slotRequestDTO) {
        try {
            vaccinationSlotService.updateSlot(hospitalId, dayOfWeek,slotRequestDTO);
            return ResponseEntity.ok().body("Slot updated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Slot not found.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating slot.");
        }
    }


}
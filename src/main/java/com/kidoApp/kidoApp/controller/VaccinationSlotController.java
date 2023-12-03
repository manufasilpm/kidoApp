package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.dto.SlotRequestDTO;
import com.kidoApp.kidoApp.dto.SlotUpdateDto;
import com.kidoApp.kidoApp.model.VaccinationSlot;
import com.kidoApp.kidoApp.services.VaccinationSlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/slots-by-hospital")
    public List<VaccinationSlot> getSlotsByHospitals(@RequestParam Long hospitalId) {
        System.out.println(vaccinationSlotService.getSlotsByHospitalId(hospitalId));
        return vaccinationSlotService.getSlotsByHospitalId(hospitalId);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSlot(@RequestParam Long hospitalId,@RequestParam String dayOfWeek, @RequestBody SlotUpdateDto slotUpdateDto) {
        try {
            vaccinationSlotService.updateSlot(hospitalId, dayOfWeek, slotUpdateDto);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Slot updated successfully.");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Slot not found.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating slot.");
        }
    }


}
package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.dto.SlotRequestDTO;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.VaccinationSlot;
import com.kidoApp.kidoApp.repository.HospitalProjection;
import com.kidoApp.kidoApp.services.VaccinationSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccination")
public class VaccinationSlotController {

    @Autowired
    private VaccinationSlotService vaccinationSlotService ;

//    @GetMapping("/slots")
//    public List<VaccinationSlot> getAvailableSlots(@RequestParam Long hospitalId, @RequestParam String date) {
//        return vaccinationSlotService.getAllSlotsByDay(hospitalId, date);
//    }

    @PostMapping("/add-slot")
    public void addSlot(@RequestBody SlotRequestDTO slotRequestDTO) {
        vaccinationSlotService.addSlot(slotRequestDTO);
    }

    @GetMapping("/hospitals-by-day")
    public List<VaccinationSlot> getHospitalsByDay(@RequestParam DayOfWeek dayOfWeek) {
        System.out.println(vaccinationSlotService.getHospitalsByDay(dayOfWeek));
        return vaccinationSlotService.getHospitalsByDay(dayOfWeek);
    }
}
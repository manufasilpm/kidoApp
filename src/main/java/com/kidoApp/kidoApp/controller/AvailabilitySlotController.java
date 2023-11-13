package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.model.AvailabilitySlot;
import com.kidoApp.kidoApp.services.AvailabilitySlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/availability")
public class AvailabilitySlotController {
    @Autowired
    private AvailabilitySlotService availabilitySlotService;

    // Create Availability Slot
    @PostMapping("/create")
    public ResponseEntity<AvailabilitySlot> createAvailabilitySlot(@RequestBody AvailabilitySlot availabilitySlot) {
        AvailabilitySlot savedSlot = availabilitySlotService.createAvailabilitySlot(availabilitySlot);
        return new ResponseEntity<>(savedSlot, HttpStatus.CREATED);
    }

    // Get Availability Slot by ID
    @GetMapping("/{id}")
    public ResponseEntity<AvailabilitySlot> getAvailabilitySlotById(@PathVariable Long id) {
        AvailabilitySlot slot = availabilitySlotService.getAvailabilitySlotById(id);

        if (slot != null) {
            return new ResponseEntity<>(slot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Availability Slot
    @PutMapping("/{id}")
    public ResponseEntity<AvailabilitySlot> updateAvailabilitySlot(@PathVariable Long id, @RequestBody AvailabilitySlot updatedSlot) {
        AvailabilitySlot existingSlot = availabilitySlotService.getAvailabilitySlotById(id);

        if (existingSlot != null) {
            updatedSlot.setId(id);
            AvailabilitySlot savedSlot = availabilitySlotService.createAvailabilitySlot(updatedSlot);
            return new ResponseEntity<>(savedSlot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Availability Slot
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailabilitySlot(@PathVariable Long id) {
        AvailabilitySlot existingSlot = availabilitySlotService.getAvailabilitySlotById(id);

        if (existingSlot != null) {
            availabilitySlotService.deleteAvailabilitySlot(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
package com.kidoApp.kidoApp.controller;


import com.kidoApp.kidoApp.dto.VaccineRequestDTO;
import com.kidoApp.kidoApp.services.VaccineRequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("vaccine-request")
public class VaccineRequestController {
    @Autowired
    private VaccineRequestService vaccineRequestService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitVaccineRequest(@RequestBody VaccineRequestDTO vaccineRequest) {
        try {
            vaccineRequestService.submitVaccineRequest(vaccineRequest);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vaccine request submitted successfully. Awaiting admin approval.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting vaccine request.");
        }
    }

    @PutMapping("/approve/{requestId}")
    public ResponseEntity<?> approveVaccineRequest(@PathVariable Long requestId) {
        try {
            vaccineRequestService.approveVaccineRequest(requestId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vaccine request approved successfully.");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccine request not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error approving vaccine request.");
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPendingVaccineRequests() {
        try {
            List<VaccineRequestDTO> pendingRequests = vaccineRequestService.getPendingVaccineRequests();
            return ResponseEntity.ok(pendingRequests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving pending vaccine requests.");
        }
    }
}

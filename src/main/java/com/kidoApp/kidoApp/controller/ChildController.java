package com.kidoApp.kidoApp.controller;


import com.kidoApp.kidoApp.dto.ChildDTO;
import com.kidoApp.kidoApp.dto.ChildRequestDTO;
import com.kidoApp.kidoApp.dto.ChildWithAppointmentDTO;
import com.kidoApp.kidoApp.model.Appointment;
import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.services.ChildService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("child")
public class ChildController {
    @Autowired
    private ChildService childService;


    @PostMapping("/add")
    public ResponseEntity<?> addChild(@RequestParam Long parentId, @RequestBody ChildRequestDTO childRequest) {
        try {
            childService.addChild(parentId, childRequest);
            return ResponseEntity.ok().body("{\"message\": \"Child added successfully.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": "+e.getMessage()+"}");
        }
    }

    @PutMapping("/update/{childId}")
    public ResponseEntity<?> updateChild(@PathVariable Long childId, @RequestBody ChildRequestDTO childRequest) {
        try {
            childService.updateChild(childId, childRequest);
            return ResponseEntity.ok().body("Child updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating child.");
        }
    }

    @GetMapping("/{childId}")
    public ResponseEntity<?> getChild(@PathVariable Long childId) {
        try {
            ChildDTO childDTO = childService.getChild(childId);
            return ResponseEntity.ok().body(childDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Child not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving child.");
        }
    }
    @GetMapping("/count")
    public Integer getChildCount(@RequestParam Long parent_id){
        return childService.GetChildCount(parent_id);
    }

    @DeleteMapping("/delete/{childId}")
    public ResponseEntity<?> deleteChild(@PathVariable Long childId) {
        try {
            childService.deleteChild(childId);
            return ResponseEntity.ok().body("Child deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Child not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting child.");
        }
    }

    @GetMapping("/with-appointments/{parentId}")
    public ResponseEntity<?> getChildrenWithAppointments(@PathVariable Long parentId) {
        try {
            List<ChildWithAppointmentDTO> children = childService.getChildrenWithAppointments(parentId);
            return ResponseEntity.ok().body(children);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving children with appointments.");
        }
    }

    @GetMapping("appointment/childId")
    public ResponseEntity<?> getAppointmentByChildId(@RequestParam Long childId){

        return ResponseEntity.ok().body(childService.getAppointmentBychildId(childId));

    }

}

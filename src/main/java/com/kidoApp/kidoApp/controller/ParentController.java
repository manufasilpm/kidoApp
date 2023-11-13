package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.Exception.PhoneNumberAlreadyExistsException;
import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parent")
public class ParentController {

    @Autowired
    ParentService parentService;


    @PostMapping("saveParent")
    public ResponseEntity createParent(@RequestBody Parent parent) {
        try {
            Parent savedParent = parentService.createParent(parent);
            return new ResponseEntity<>(savedParent, HttpStatus.CREATED);
        } catch (PhoneNumberAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/parent_id/{phoneNumber}")
    public String getParentIdByPhoneNumber(@PathVariable String phoneNumber) {
        return parentService.getParentIdByPhoneNumber(phoneNumber);
    }

    @PutMapping("/updateParent/{id}")
    public ResponseEntity<?> updateParent(@PathVariable Long id, @RequestBody Parent updatedParent) {
        try {
            Parent existingParent = parentService.getParentById(id);

            if (existingParent != null) {
                updatedParent.setId(id);
                Parent savedParent = parentService.createParent(updatedParent);
                return new ResponseEntity<>(savedParent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Parent not found", HttpStatus.NOT_FOUND);
            }
        } catch (PhoneNumberAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getParent/{id}")
    public ResponseEntity<Parent> getParentById(@PathVariable Long id) {
        Parent parent = parentService.getParentById(id);

        if (parent != null) {
            return new ResponseEntity<>(parent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteParent/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        Parent existingParent = parentService.getParentById(id);

        if (existingParent != null) {
            parentService.deleteParent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


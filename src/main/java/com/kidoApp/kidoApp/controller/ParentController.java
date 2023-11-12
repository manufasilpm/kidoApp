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
}


package com.kidoApp.kidoApp.controller;


import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.services.ChildService;
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

    @GetMapping("details")
        public String  getDetails(){
            return "Hey";
        }


    @PostMapping("saveChild")
    public ResponseEntity<Child> createChild(@RequestBody Child child) {
        // Validate and save the child entity using the ChildService (you'll need to create this service)
        Child savedChild = childService.createChild(child);

        // Return a ResponseEntity with the created child and an HTTP status code
        return new ResponseEntity<>(savedChild, HttpStatus.CREATED);
    }

    @GetMapping("/all/{parent_Id}")
    public List<Child> getChildrenByParentId(@PathVariable String parent_Id) {
        return childService.getChildrenByParentId(parent_Id);
    }
}

package com.kidoApp.kidoApp.controller;

import com.kidoApp.kidoApp.Exception.PhoneNumberAlreadyExistsException;
import com.kidoApp.kidoApp.dto.ChildDTO;
import com.kidoApp.kidoApp.dto.ParentDTO;
import com.kidoApp.kidoApp.dto.ParentRequestDTO;
import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.services.ParentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("parent")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addParent(@RequestBody ParentRequestDTO parentRequest) {
        try {
            parentService.addParent(parentRequest);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Parent added successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error adding parent."));
        }
    }

    @PutMapping("/update/{parentId}")
    public ResponseEntity<?> updateParent(@PathVariable Long parentId, @RequestBody ParentRequestDTO parentRequest) {
        try {
            parentService.updateParent(parentId, parentRequest);
            return ResponseEntity.ok().body("Parent updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating parent.");
        }
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<?> getParent(@PathVariable Long parentId) {
        try {
            ParentDTO parentDTO = parentService.getParent(parentId);
            return ResponseEntity.ok().body(parentDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving parent.");
        }
    }

    @DeleteMapping("/delete/{parentId}")
    public ResponseEntity<?> deleteParent(@PathVariable Long parentId) {
        try {
            parentService.deleteParent(parentId);
            return ResponseEntity.ok().body("Parent deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting parent.");
        }
    }

    @GetMapping("/{parentId}/children")
    public ResponseEntity<?> getChildrenByParentId(@PathVariable Long parentId) {
        try {
            List<ChildDTO> children = parentService.getChildrenByParentId(parentId);
            return ResponseEntity.ok().body(children);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving children.");
        }
    }
    @GetMapping("/getIdByPhone/{phoneNo}")
    public ResponseEntity<Long> getParentIdByPhone(@PathVariable String phoneNo) {
        Parent parent = parentService.findByPhoneNumber(phoneNo);
        if (parent != null) {
            return ResponseEntity.ok(parent.getParentId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{parentId}/details")
    public ResponseEntity<Parent> getParentDetails(@PathVariable Long parentId) {
        try {
            Parent parent = parentService.getParentDetails(parentId);
            return ResponseEntity.ok(parent);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }




}


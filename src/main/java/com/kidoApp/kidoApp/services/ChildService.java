package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository; // Assuming you have a ChildRepository

    public Child createChild(Child child) {
        Child savedChild = childRepository.save(child);
        return savedChild;
    }

    public List<Child> getChildrenByParentId(String parent_Id) {
        System.out.println("hey");
        return childRepository.findByParentId(parent_Id);
    }
}
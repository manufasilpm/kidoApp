package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.Exception.PhoneNumberAlreadyExistsException;
import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParentService {

    @Autowired
    ParentRepository parentRepository;
    @Transactional
    public Parent createParent(Parent parent) {
        // Check if the phone number already exists
        if (parentRepository.existsByPhoneNo(parent.getPhone_no())) {
            throw new PhoneNumberAlreadyExistsException("Phone number already exists");
        }


        return parentRepository.save(parent);
    }

    public String getParentIdByPhoneNumber(String phoneNumber) {

        try{

            return parentRepository.findParentIdByPhoneNumber(phoneNumber).toString();

        }catch(Exception e){
            return null;
        }
    }
}

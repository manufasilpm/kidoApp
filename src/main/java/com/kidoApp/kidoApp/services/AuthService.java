package com.kidoApp.kidoApp.services;


import com.kidoApp.kidoApp.Exception.InvalidCredentialsException;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ParentRepository parentRepository;
    @Autowired
    public AuthService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public Parent validateCredentials(String phoneNumber, String password) {
        Parent parent = parentRepository.findByPhoneNumber(phoneNumber);

        if (parent == null || !parent.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return parent;
    }

}

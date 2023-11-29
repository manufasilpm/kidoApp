package com.kidoApp.kidoApp.services;



import com.kidoApp.kidoApp.Exception.InvalidCredentialsException;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.repository.HospitalRepository;
import com.kidoApp.kidoApp.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ParentRepository parentRepository;
    private final HospitalRepository hospitalRepository;
    @Autowired
    public AuthService(ParentRepository parentRepository,
                       HospitalRepository hospitalRepository) {
        this.parentRepository = parentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Parent validateCredentials(String phoneNumber, String password) {
        Parent parent = parentRepository.findByPhoneNoContaining(phoneNumber);

        if (parent == null || !parent.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return parent;
    }

    public Hospital validateHospitalCredentials(String phoneNumber, String password) {
        Hospital hospital = hospitalRepository.findByPhoneNoContaining(phoneNumber);

        if (hospital == null || !hospital.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return hospital;
    }

}

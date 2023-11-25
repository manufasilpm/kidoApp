package com.kidoApp.kidoApp.services;


import com.kidoApp.kidoApp.Exception.ChildAlreadyExistsException;
import com.kidoApp.kidoApp.Exception.HospitalPhoneNumberExistsException;
import com.kidoApp.kidoApp.Exception.PhoneNumberAlreadyExistsException;
import com.kidoApp.kidoApp.dto.HospitalDTO;
import com.kidoApp.kidoApp.dto.HospitalRequestDTO;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.repository.HospitalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    public void addHospital(HospitalRequestDTO hospitalRequest) {

        if (hospitalRepository.existsByPhoneNo(hospitalRequest.getPhoneNumber())) {
            throw new HospitalPhoneNumberExistsException("Hospital with the same phone number already exists.");
        }
        Hospital hospital = new Hospital();
        hospital.setHospitalName(hospitalRequest.getName());
        hospital.setLocation(hospitalRequest.getAddress());
        hospital.setPassword(hospitalRequest.getPassword());
        hospital.setPhoneNo(hospitalRequest.getPhoneNumber());
        // Set other hospital properties as needed
        hospitalRepository.save(hospital);
    }

    public void updateHospital(Long hospitalId, HospitalRequestDTO hospitalRequest) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));

        hospital.setHospitalName(hospitalRequest.getName());
        hospitalRepository.save(hospital);
    }

    public HospitalDTO getHospital(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));

        return new HospitalDTO(hospital);
    }

    public void deleteHospital(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));

        hospitalRepository.delete(hospital);
    }
    public List<HospitalDTO> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        // Convert the list of Hospital entities to HospitalDTOs
        List<HospitalDTO> hospitalDTOs = hospitals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return hospitalDTOs;
    }


    private HospitalDTO convertToDTO(Hospital hospital) {
        HospitalDTO hospitalDTO = new HospitalDTO();
        hospitalDTO.setId(hospital.getHospital_id());
        hospitalDTO.setName(hospital.getHospitalName());
        // Set other properties if needed
        return hospitalDTO;
    }
}

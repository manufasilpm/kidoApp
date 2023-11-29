package com.kidoApp.kidoApp.services;


import com.kidoApp.kidoApp.Exception.VaccineNameExistsException;
import com.kidoApp.kidoApp.dto.VaccineDTO;
import com.kidoApp.kidoApp.dto.VaccineRequestDTO;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Vaccine;
import com.kidoApp.kidoApp.repository.HospitalRepository;
import com.kidoApp.kidoApp.repository.VaccineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService {
    @Autowired
    private VaccineRepository vaccineRepository;

    public void addVaccine(VaccineRequestDTO vaccineRequest) {
        // Check if a vaccine with the same name already exists
        if (vaccineRepository.existsByVaccineName(vaccineRequest.getVaccineName())) {
            throw new VaccineNameExistsException("Vaccine with the same name already exists.");
        }

        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName(vaccineRequest.getVaccineName());
        vaccine.setVaccineCategory(vaccineRequest.getVaccineCategory());
        vaccine.setCompany(vaccineRequest.getCompany());
        vaccine.setDescription(vaccineRequest.getDescription());
        vaccine.setStatus(vaccineRequest.getStatus());
        // Set other vaccine properties as needed

        vaccineRepository.save(vaccine);
    }

    public void updateVaccine(Long vaccineId, VaccineRequestDTO vaccineRequest) {
        Vaccine vaccine = vaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));

        vaccine.setVaccineName(vaccineRequest.getVaccineName());
        vaccine.setVaccineCategory(vaccineRequest.getVaccineCategory());
        vaccine.setCompany(vaccineRequest.getCompany());
        vaccine.setDescription(vaccineRequest.getDescription());
        vaccine.setStatus(vaccineRequest.getStatus());


        vaccineRepository.save(vaccine);
    }

    public VaccineDTO getVaccine(Long vaccineId) {
        Vaccine vaccine = vaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));

        return new VaccineDTO(vaccine);
    }

    public void deleteVaccine(Long vaccineId) {
        Vaccine vaccine = vaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));

        vaccineRepository.delete(vaccine);
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }
}

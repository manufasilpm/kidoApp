package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.constants.RequestStatus;
import com.kidoApp.kidoApp.dto.VaccineRequestDTO;
import com.kidoApp.kidoApp.model.Vaccine;
import com.kidoApp.kidoApp.model.VaccineRequest;
import com.kidoApp.kidoApp.repository.VaccineRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineRequestService {

    @Autowired
    private VaccineRequestRepository vaccineRequestRepository;

    @Autowired
    private VaccineService vaccineService;

    public void submitVaccineRequest(VaccineRequestDTO vaccineRequest) {
        // You might want to perform some validation or additional checks here

        // Save the vaccine request to the database with a pending status
        VaccineRequest newRequest = new VaccineRequest();
        newRequest.setVaccineName(vaccineRequest.getVaccineName());
        newRequest.setCompany(vaccineRequest.getCompany());
        newRequest.setDescription(vaccineRequest.getDescription());
        newRequest.setStatus(RequestStatus.PENDING); // Enum representing status: PENDING, APPROVED, REJECTED
        vaccineRequestRepository.save(newRequest);
    }

    public void approveVaccineRequest(Long requestId) {
        // Retrieve the vaccine request from the database
        VaccineRequest vaccineRequest = vaccineRequestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Vaccine request not found"));


        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName(vaccineRequest.getVaccineName());
        vaccine.setCompany(vaccineRequest.getCompany());
        vaccine.setDescription(vaccineRequest.getDescription());
        vaccineService.addVaccine(vaccine);


        vaccineRequest.setStatus(RequestStatus.APPROVED);
        vaccineRequestRepository.save(vaccineRequest);
    }

    public List<VaccineRequestDTO> getPendingVaccineRequests() {
        // Retrieve all pending vaccine requests from the database
        List<VaccineRequest> pendingRequests = vaccineRequestRepository.findByStatus(RequestStatus.PENDING);

        // Convert VaccineRequest entities to DTOs for response
        return pendingRequests.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private VaccineRequestDTO mapToDto(VaccineRequest vaccineRequest) {
        VaccineRequestDTO dto = new VaccineRequestDTO();
        dto.setVaccineName(vaccineRequest.getVaccineName());
        dto.setCompany(vaccineRequest.getCompany());
        dto.setDescription(vaccineRequest.getDescription());
        // Set other DTO properties as needed
        return dto;
    }
}

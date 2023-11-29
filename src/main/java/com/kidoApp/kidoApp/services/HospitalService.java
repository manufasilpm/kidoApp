package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.Exception.HospitalPhoneNumberExistsException;
import com.kidoApp.kidoApp.constants.DayOfWeek;
import com.kidoApp.kidoApp.dto.HospitalDTO;
import com.kidoApp.kidoApp.dto.HospitalRequestDTO;
import com.kidoApp.kidoApp.dto.SlotRequestDTO;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.VaccinationSlot;
import com.kidoApp.kidoApp.repository.HospitalRepository;
import com.kidoApp.kidoApp.repository.VaccinationSlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private VaccinationSlotRepository vaccinationSlot;


    @Autowired
    private VaccinationSlotService vaccinationSlotService;

    public void addHospital(HospitalRequestDTO hospitalRequest) {

        if (hospitalRepository.existsByPhoneNo(hospitalRequest.getPhoneNo())) {
            throw new HospitalPhoneNumberExistsException("Hospital with the same phone number already exists.");
        }
        Hospital hospital = new Hospital();
        hospital.setHospitalName(hospitalRequest.getHospitalName());
        hospital.setLocation(hospitalRequest.getAddress());
        hospital.setPassword(hospitalRequest.getPassword());
        hospital.setPhoneNo(hospitalRequest.getPhoneNo());

        hospitalRepository.save(hospital);
        Long hospitalId = hospital.getHospitalId();

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {



                SlotRequestDTO slotRequest=new SlotRequestDTO(hospitalId,dayOfWeek.toString(),10L,"9","17");
                vaccinationSlotService.addSlot(slotRequest);

        }


    }

    public void updateHospital(Long hospitalId, HospitalRequestDTO hospitalRequest) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));

        hospital.setHospitalName(hospitalRequest.getHospitalName());
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
        HospitalDTO hospitalDTO = new HospitalDTO(hospital);
        hospitalDTO.setId(hospital.getHospitalId());
        hospitalDTO.setHospitalName(hospital.getHospitalName());

        return hospitalDTO;
    }

}

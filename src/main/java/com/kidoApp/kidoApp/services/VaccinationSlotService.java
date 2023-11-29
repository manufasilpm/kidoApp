package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.constants.DayOfWeek;
import com.kidoApp.kidoApp.dto.SlotRequestDTO;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.VaccinationSlot;
import com.kidoApp.kidoApp.repository.HospitalRepository;
import com.kidoApp.kidoApp.repository.VaccinationSlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationSlotService {
    @Autowired
    private VaccinationSlotRepository vaccinationSlotRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    public void addSlot(SlotRequestDTO slotRequestDTO) {
        Long hospitalId = slotRequestDTO.getHospitalId();
        String day = slotRequestDTO.getDay();
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));
        VaccinationSlot vaccinationSlot = new VaccinationSlot();
        vaccinationSlot.setHospital(hospital);
        vaccinationSlot.setDayOfWeek(DayOfWeek.valueOf(day));
        vaccinationSlot.setSlotCount(slotRequestDTO.getSlotCount());
        vaccinationSlot.setFromTime(slotRequestDTO.getFromTime());
        vaccinationSlot.setToTime(slotRequestDTO.getToTime());// Assuming 'day' is an enum value



        vaccinationSlotRepository.save(vaccinationSlot);
    }

    public List<VaccinationSlot> getHospitalsByDay(DayOfWeek dayOfWeek) {
        try {
            return vaccinationSlotRepository.findDistinctByDayOfWeek(dayOfWeek);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            // Handle the exception or rethrow as needed
            throw new RuntimeException("Error fetching hospitals by day", e);
        }
    }

    public void updateSlot(Long hospitalId, SlotRequestDTO slotRequestDTO) {
        VaccinationSlot existingSlot = vaccinationSlotRepository.findByHospitalHospitalId(hospitalId);


        existingSlot.setSlotCount(slotRequestDTO.getSlotCount());


        vaccinationSlotRepository.save(existingSlot);
    }

}

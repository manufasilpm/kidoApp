package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.model.AvailabilitySlot;
import com.kidoApp.kidoApp.repository.AvailabilitySlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilitySlotService {
    @Autowired
    private AvailabilitySlotRepository availabilitySlotRepository;

    public AvailabilitySlot createAvailabilitySlot(AvailabilitySlot availabilitySlot) {
        return availabilitySlotRepository.save(availabilitySlot);
    }

    public AvailabilitySlot getAvailabilitySlotById(Long id) {
        return availabilitySlotRepository.findById(id).orElse(null);
    }

    public void deleteAvailabilitySlot(Long id) {
        availabilitySlotRepository.deleteById(id);
    }
}
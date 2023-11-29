package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.constants.DayOfWeek;
import com.kidoApp.kidoApp.model.VaccinationSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VaccinationSlotRepository extends JpaRepository<VaccinationSlot, Long> {
    VaccinationSlot findByHospitalHospitalId(Long hospitalId);
    // Add other methods if needed

    List<VaccinationSlot> findDistinctByDayOfWeek(DayOfWeek dayOfWeek);




}
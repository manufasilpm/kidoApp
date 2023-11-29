package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.controller.DayOfWeek;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.VaccinationSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationSlotRepository extends JpaRepository<VaccinationSlot, Long> {
//    List<VaccinationSlot> findAllByHospital_HospitalIdAndDayOfWeek(Long hospital_hospitalId, DayOfWeek dayOfWeek);

    List<VaccinationSlot> findDistinctByDayOfWeek(DayOfWeek dayOfWeek);
    // Add custom queries if needed
}
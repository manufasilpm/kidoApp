package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.constants.DayOfWeek;
import com.kidoApp.kidoApp.model.VaccinationSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationSlotRepository extends JpaRepository<VaccinationSlot, Long> {
    VaccinationSlot findByHospitalHospitalIdAndDayOfWeek(Long hospitalId,String dayOfWeek);


    List<VaccinationSlot> findDistinctByDayOfWeek(String dayOfWeek);


    List<VaccinationSlot> findByHospitalHospitalId(Long hospitalId);


}
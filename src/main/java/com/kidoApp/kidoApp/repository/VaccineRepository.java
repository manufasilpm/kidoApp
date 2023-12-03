package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    boolean existsByVaccineName(String name);

    List<Vaccine> findByVaccineCategory(String vaccineCategory);
}

package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    Hospital findByPhoneNoContaining(String phoneNumber);

    boolean existsByPhoneNo(String phoneNumber);
}



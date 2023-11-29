package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.constants.RequestStatus;
import com.kidoApp.kidoApp.model.VaccineRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRequestRepository extends JpaRepository<VaccineRequest, Long> {

    List<VaccineRequest> findByStatus(RequestStatus status);
}
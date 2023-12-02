package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.model.Appointment;
import com.kidoApp.kidoApp.model.Parent;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {



}

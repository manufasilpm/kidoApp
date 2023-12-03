package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.dto.AppointmentDetailsDTO;
import com.kidoApp.kidoApp.model.Appointment;
import com.kidoApp.kidoApp.model.Parent;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


//    @Query("SELECT new com.kidoApp.kidoApp.model.Appointment(\n" +
//            "    a.id, \n" +
//            "    new com.kidoApp.kidoApp.dto.ChildDTO(a.child), \n" +
//            "    a.appointmentDate, \n" +
//            "    a.status\n" +
//            ")\n" +
//            "FROM Appointment a\n" +
//            "WHERE a.hospital.id = :hospitalId")
    List<Appointment> findByHospitalHospitalId( Long hospitalId);

Appointment findByChildChildId(Long childId);
}

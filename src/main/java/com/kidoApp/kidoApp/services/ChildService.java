package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.Exception.ChildAlreadyExistsException;
import com.kidoApp.kidoApp.dto.ChildDTO;
import com.kidoApp.kidoApp.dto.ChildRequestDTO;
import com.kidoApp.kidoApp.dto.ChildWithAppointmentDTO;
import com.kidoApp.kidoApp.model.Appointment;
import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.repository.AppointmentRepository;
import com.kidoApp.kidoApp.repository.ChildRepository;
import com.kidoApp.kidoApp.repository.ParentRepository;
import com.kidoApp.kidoApp.services.commonServices.AgeFinder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private ParentRepository parentRepository; // Assuming you have a ChildRepository

@Autowired
private AppointmentRepository appointmentRepository;

    public void addChild(Long parentId, ChildRequestDTO childRequest) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

        if (childRepository.existsByChildNameAndParent(childRequest.getName(), parent)) {
            throw new ChildAlreadyExistsException("Child with the same name already exists for the parent.");
        }
        Child child = new Child();
AgeFinder ageFinder=new AgeFinder();
        child.setChildName(childRequest.getName());
        child.setGender(childRequest.getGender());
        child.setDob(childRequest.getDob());
        child.setAge(ageFinder.AgeFind(childRequest.getDob()));
        child.setCompleted_vaccine(childRequest.getCompleted_vaccine());
        child.setLatest_vaccine(childRequest.getLatest_vaccine());

        child.setParent(parent);

        childRepository.save(child);
    }
    public ChildDTO getChild(Long childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        return new ChildDTO(child);
    }

    public void deleteChild(Long childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        childRepository.delete(child);
    }
    public void updateChild(Long childId, ChildRequestDTO childRequest) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        child.setChildName(childRequest.getName());
        // Update other child properties as needed
        child.setDob(childRequest.getDob());
        child.setLatest_vaccine(childRequest.getLatest_vaccine());
        child.setCompleted_vaccine(childRequest.getCompleted_vaccine());

        childRepository.save(child);
    }


    public List<ChildWithAppointmentDTO> getChildrenWithAppointments(Long parentId) {
        List<Child> children=childRepository.findByParentParentIdAndAppointmentsIsNotNull(parentId);
        List<ChildWithAppointmentDTO> childDTOs = new ArrayList<>();
        for (Child child : children) {
            ChildWithAppointmentDTO childDTO = new ChildWithAppointmentDTO(child);
            childDTOs.add(childDTO);
        }
        return childDTOs;
    }


    public Integer GetChildCount(Long parentId) {

        return  childRepository.countByParent_ParentId(parentId);
    }


    public Optional<Child> getAppointmentBychildId(Long ChildId) {

        return  childRepository.findByChildIdAndAppointmentsIdIsNotNull(ChildId);
    }
}
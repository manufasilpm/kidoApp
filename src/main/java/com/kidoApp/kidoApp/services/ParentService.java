package com.kidoApp.kidoApp.services;

import com.kidoApp.kidoApp.Exception.PhoneNumberAlreadyExistsException;
import com.kidoApp.kidoApp.dto.AppointmentRequestDTO;
import com.kidoApp.kidoApp.dto.ChildDTO;
import com.kidoApp.kidoApp.dto.ParentDTO;
import com.kidoApp.kidoApp.dto.ParentRequestDTO;
import com.kidoApp.kidoApp.model.Appointment;
import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.repository.AppointmentRepository;
import com.kidoApp.kidoApp.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void addParent(ParentRequestDTO parentRequest) {

        if (parentRepository.existsByPhoneNo(parentRequest.getPhoneNo())) {
            throw new PhoneNumberAlreadyExistsException("Phone number alredy exist");
        }
        Parent parent = new Parent();
        parent.setParentName(parentRequest.getParentName());
        parent.setPassword(parentRequest.getPassword());
        parent.setAddress(parentRequest.getAddress());
        parent.setPhoneNo(parentRequest.getPhoneNo());

        // Initialize the children list if needed
        parent.setChildren(new ArrayList<>());

        parentRepository.save(parent);
    }

    public void updateParent(Long parentId, ParentRequestDTO parentRequest) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

        parent.setParentName(parentRequest.getParentName());
        parent.setPassword(parentRequest.getPassword());
        parent.setAddress(parentRequest.getAddress());
        parent.setPhoneNo(parentRequest.getPhoneNo());

        parentRepository.save(parent);
    }
    public ParentDTO getParent(Long parentId) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

        return new ParentDTO(parent);
    }
    public Parent getParentDetails(Long parentId) {

        return parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));
    }


    public void deleteParent(Long parentId) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

        parentRepository.delete(parent);
    }

    public List<ChildDTO> getChildrenByParentId(Long parentId) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

        List<ChildDTO> children = parent.getChildren().stream()
                .map(ChildDTO::new)
                .collect(Collectors.toList());

        return children;
    }

    public Parent findByPhoneNumber(String phoneNo) {
        return parentRepository.findByPhoneNo(phoneNo);
    }

    public Parent findParentById(Long id) {
        return parentRepository.findParentByParentId(id);
    }


}

package com.kidoApp.kidoApp.dto;

import com.kidoApp.kidoApp.model.Child;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildDTO {
    private Long id;
    private String name;
    private String gender;
    private String dob;
    private String Age;
    private String latest_vaccine;
    private String completed_vaccine;



    public ChildDTO(Child child) {
        this.id = child.getChildId();
        this.name = child.getChildName();
        this.gender=child.getGender();
        this.dob= child.getDob();
        this.latest_vaccine= child.getLatest_vaccine();
        this.completed_vaccine=child.getCompleted_vaccine();
        // Set other fields
    }
}


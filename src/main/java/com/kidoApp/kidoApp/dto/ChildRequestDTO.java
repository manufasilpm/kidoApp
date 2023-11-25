package com.kidoApp.kidoApp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildRequestDTO {
    private String name;


    private String gender;

    private String dob;

    private String latest_vaccine;

    private String completed_vaccine;

}


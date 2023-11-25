package com.kidoApp.kidoApp.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class VaccineRequestDTO {

    public String vaccineName;
    public String vaccineCategory;
    public String company;
    public String description;
   
}

package com.kidoApp.kidoApp.dto;

import com.kidoApp.kidoApp.model.Vaccine;
import lombok.Data;

@Data
public class VaccineDTO {
    private Long id;
    public String vaccineName;
    public String vaccineCategory;
    public String company;
    public String description;
    public String status;

    public VaccineDTO(Vaccine vaccine) {
        this.id = vaccine.getVaccineId();
        this.vaccineName = vaccine.getVaccineName();
        this.vaccineCategory=vaccine.getVaccineCategory();
        this.company=vaccine.company;
        this.description=vaccine.getDescription();
        this.status=vaccine.getStatus();

        // Set other fields
    }
}

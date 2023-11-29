package com.kidoApp.kidoApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue
    @Column(name = "hospital_id")
    private  Long hospitalId;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "location")
    private String location;

    @Column(name = "phone_no")
    private String phoneNo;


    @Column(name = "password")
    private   String password;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
   @JsonBackReference
    private List<VaccinationSlot> vaccinationSlots;

    @OneToMany(targetEntity = Vaccine.class, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "Vaccine_fk", referencedColumnName = "hospital_id")
    private List<Vaccine> vaccine;
    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalId=" + hospitalId +
                ", hospitalName='" + hospitalName + '\'' +
                ", location='" + location + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

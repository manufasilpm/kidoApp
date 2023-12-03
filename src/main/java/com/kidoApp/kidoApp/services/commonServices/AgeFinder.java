package com.kidoApp.kidoApp.services.commonServices;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgeFinder {

    public String AgeFind(String dateOfBirth){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
        return calculateAge(dob, LocalDate.now());

    }
    public static String calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return String.valueOf(Math.toIntExact(birthDate.until(currentDate).toTotalMonths() / 12));
        } else {
            return "0";
        }
    }
}

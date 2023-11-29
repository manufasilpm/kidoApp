package com.kidoApp.kidoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotRequestDTO {

    private Long hospitalId;
    private String day;
    private Long slotCount;
    private String fromTime;
    private String toTime;



}

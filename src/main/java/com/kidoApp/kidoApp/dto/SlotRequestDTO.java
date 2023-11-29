package com.kidoApp.kidoApp.dto;

import lombok.Data;

@Data
public class SlotRequestDTO {

    private Long hospitalId;
    private String day;
    private Long slotCount;


}

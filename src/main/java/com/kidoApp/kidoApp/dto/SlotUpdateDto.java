package com.kidoApp.kidoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotUpdateDto {

    private Long slotCount;
    private String fromTime;
    private String toTime;

    public SlotUpdateDto(Long slotCount) {
        this.slotCount = slotCount;
    }
}





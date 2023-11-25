package com.kidoApp.kidoApp.dto;

import com.kidoApp.kidoApp.model.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentDTO {
    private Long parentId;
    private String parentName;
    private String address;
    private String phoneNo;

    public ParentDTO(Parent parent) {
        this.parentId = parent.getParentId();
        this.parentName = parent.getParentName();
        this.address = parent.getAddress();
        this.phoneNo = parent.getPhoneNo();
    }
}
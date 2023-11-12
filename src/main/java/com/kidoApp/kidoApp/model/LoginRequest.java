package com.kidoApp.kidoApp.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;
    private String password;

}

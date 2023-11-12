package com.kidoApp.kidoApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serial;

@Data
@Entity
public class LogIn {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private  int UserId;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String UserType;
    @Column(nullable = false)
    private String Password;

    public int getUserId() {
        return UserId;
    }

    public String getUserName() {
        return username;
    }

    public String getUserType() {
        return UserType;
    }

    public String getPassword() {
        return Password;
    }
}

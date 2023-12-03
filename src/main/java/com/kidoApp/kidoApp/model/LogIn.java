package com.kidoApp.kidoApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;

@Data
@Entity
public class LogIn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

package com.kidoApp.kidoApp.Authentication;

import com.kidoApp.kidoApp.Exception.InvalidCredentialsException;
import com.kidoApp.kidoApp.model.LoginRequest;
import com.kidoApp.kidoApp.model.Parent;
import com.kidoApp.kidoApp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/auth/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        String phoneNumber = loginRequest.getPhoneNumber();
//        String password = loginRequest.getPassword();
//
//        if (authService.validateCredentials(phoneNumber,password)) {
//            System.out.println(phoneNumber + ": "+ loginRequest.getPhoneNumber());
//            System.out.println(password + ": "+ loginRequest.getPassword());
//
//            return new ResponseEntity<String>("Login successful", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
//        }
//    }
    @PostMapping(value = "/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String phoneNumber = loginRequest.getPhoneNumber();
            String password = loginRequest.getPassword();

            Parent parent = authService.validateCredentials(phoneNumber, password);

            if (parent != null) {
                System.out.println(phoneNumber + ": " + loginRequest.getPhoneNumber());
                System.out.println(password + ": " + loginRequest.getPassword());

                // Include parent ID in the response
                String responseJson = String.format("{\"message\": \"Login successful\", \"parentId\": %d}", parent.getParentId());

                return ResponseEntity.ok(responseJson);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid username or password\"}");
            }
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid username or password\"}");
        }
    }
}

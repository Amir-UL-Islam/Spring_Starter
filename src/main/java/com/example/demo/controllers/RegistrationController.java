package com.example.demo.controllers;

import com.example.demo.entities.RegistrationRequest;
import com.example.demo.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    // Calling the service
    private RegistrationService registrationService;
    // Start From Here
    // 1. ---- Taking Required Data According to RegistrationRequest.java file Using AppUser.java Getter and Setter Methods.
    // And the Whole thing is Happening in AppUserService.java File
    // EndPoints
    @PostMapping
    public String request(@RequestBody RegistrationRequest request) throws IllegalAccessException {
        return registrationService.register(request); // Passing Request from User/FrontEnd
    }

    @GetMapping(path = "confirm")
    public String confirmUserToken(@RequestParam("token") String token) {
        return registrationService.confirmToken(token); // confirmToken Method is a Transactional
    }
}

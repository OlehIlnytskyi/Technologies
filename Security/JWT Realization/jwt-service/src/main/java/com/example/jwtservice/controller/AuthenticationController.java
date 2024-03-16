package com.example.jwtservice.controller;

import com.example.jwtservice.persistence.dto.LoginRequest;
import com.example.jwtservice.persistence.dto.RegisterRequest;
import com.example.jwtservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String jwtToken) {
        return authenticationService.validate(jwtToken);
    }
}

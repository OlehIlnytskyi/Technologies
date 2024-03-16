package com.example.jwtservice.service;

import com.example.jwtservice.persistence.dto.LoginRequest;
import com.example.jwtservice.persistence.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<String> register(RegisterRequest request);

    ResponseEntity<String> login(LoginRequest request);

    ResponseEntity<Boolean> validate(String jwtToken);
}

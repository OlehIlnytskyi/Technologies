package com.example.jwtservice.service.impl;

import com.example.jwtservice.jwt.JwtUtils;
import com.example.jwtservice.persistence.domain.UserDetails;
import com.example.jwtservice.persistence.repository.UserDetailsRepository;
import com.example.jwtservice.persistence.dto.LoginRequest;
import com.example.jwtservice.persistence.dto.RegisterRequest;
import com.example.jwtservice.service.AuthenticationService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsRepository userDetailsRepository;

    public AuthenticationServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }


    @Override
    public ResponseEntity<String> register(RegisterRequest request) {
        log.info(request.username);
        log.info(request.password);
        UserDetails userDetails = requestToUserDetails(request);

        if (userDetailsRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("There is already user with username: " + userDetails.getUsername());
        }

        userDetailsRepository.save(userDetails);

        String jwtToken = JwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(jwtToken);
    }

    @Override
    public ResponseEntity<String> login(LoginRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> validate(String jwtToken) {
        return null;
    }


    private UserDetails requestToUserDetails(RegisterRequest request) {
        return UserDetails.builder()
                .username(request.username)
                .password(request.password)
                .build();
    }
}

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

    private final static String USER_ALREADY_IN_DATABASE = "There is already user with username: ";

    private final UserDetailsRepository userDetailsRepository;

    public AuthenticationServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }


    @Override
    public ResponseEntity<String> register(RegisterRequest request) {
        UserDetails userDetails = requestToUserDetails(request);

        if (isUserPresentInDatabase(userDetails))
            return userAlreadyInDatabaseResponse(userDetails);

        saveUserDetails(userDetails);
        return jwtTokenResponse(userDetails);
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

    private boolean isUserPresentInDatabase(UserDetails userDetails) {
        return userDetailsRepository.findByUsername(userDetails.getUsername()).isPresent();
    }

    private void saveUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    private ResponseEntity<String> userAlreadyInDatabaseResponse(UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(USER_ALREADY_IN_DATABASE + userDetails.getUsername());
    }

    private ResponseEntity<String> jwtTokenResponse(UserDetails userDetails) {
        String jwtToken = JwtUtils.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(jwtToken);
    }
}

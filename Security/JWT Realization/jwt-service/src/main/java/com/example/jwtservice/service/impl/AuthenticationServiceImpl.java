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

import java.util.Objects;

@Log
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final static String USER_ALREADY_IN_DATABASE_RESPONSE = "There is already user with username: ";
    private final static String INCORRECT_USERNAME_OR_PASSWORD_RESPONSE = "Incorrect username or password!";

    private final UserDetailsRepository userDetailsRepository;

    public AuthenticationServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }


    @Override
    public ResponseEntity<String> register(RegisterRequest request) {
        UserDetails userDetails = registerRequestToUserDetails(request);

        if (isUserPresentInDatabase(userDetails))
            return userAlreadyInDatabaseResponse(userDetails);

        saveUserDetails(userDetails);
        return jwtTokenResponse(userDetails);
    }

    @Override
    public ResponseEntity<String> login(LoginRequest request) {
        UserDetails userDetails = loginRequestToUserDetails(request);
        UserDetails savedUserDetails = getUserDetailsOrBlank(userDetails);

        if (!arePasswordsEquals(userDetails, savedUserDetails))
            // This will also be triggered if the username is incorrect
            return incorrectUsernameOrPasswordResponse();

        return jwtTokenResponse(userDetails);
    }

    @Override
    public ResponseEntity<Boolean> validate(String jwtToken) {
        return isTokenValidResponse(jwtToken);
    }


    private UserDetails registerRequestToUserDetails(RegisterRequest request) {
        return UserDetails.builder()
                .username(request.username)
                .password(request.password)
                .build();
    }

    private UserDetails loginRequestToUserDetails(LoginRequest request) {
        return UserDetails.builder()
                .username(request.username)
                .password(request.password)
                .build();
    }

    private boolean isUserPresentInDatabase(UserDetails userDetails) {
        return userDetailsRepository.findByUsername(userDetails.getUsername()).isPresent();
    }

    private boolean arePasswordsEquals(UserDetails userDetails1, UserDetails userDetails2) {
        return Objects.equals(userDetails1.getPassword(), userDetails2.getPassword());
    }

    private UserDetails getUserDetailsOrBlank(UserDetails userDetails) {
        return userDetailsRepository.findByUsername(userDetails.getUsername())
                .orElse(new UserDetails());
    }

    private void saveUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    private ResponseEntity<String> userAlreadyInDatabaseResponse(UserDetails userDetails) {
        String body = USER_ALREADY_IN_DATABASE_RESPONSE + userDetails.getUsername();
        return response(HttpStatus.UNAUTHORIZED, body);
    }

    private ResponseEntity<String> incorrectUsernameOrPasswordResponse() {
        return response(HttpStatus.UNAUTHORIZED, INCORRECT_USERNAME_OR_PASSWORD_RESPONSE);
    }

    private ResponseEntity<String> jwtTokenResponse(UserDetails userDetails) {
        String jwtToken = JwtUtils.generateToken(userDetails);
        return response(HttpStatus.OK, jwtToken);
    }

    private ResponseEntity<Boolean> isTokenValidResponse(String jwtToken) {
        boolean isTokenValid = JwtUtils.isTokenValid(jwtToken);
        return response(HttpStatus.OK, isTokenValid);
    }

    private <T> ResponseEntity<T> response(HttpStatus status, T body) {
        return ResponseEntity.status(status).body(body);
    }
}

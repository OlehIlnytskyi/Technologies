package com.example.jwtservice.security;

public interface PasswordHashing {

    String hash(String password);
}

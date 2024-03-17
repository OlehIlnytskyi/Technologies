package com.example.jwtservice.security;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class PasswordHashingSHA256 implements PasswordHashing {

    @Override
    public String hash(String password) {
        return "{sha256}" + Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8);
    }
}

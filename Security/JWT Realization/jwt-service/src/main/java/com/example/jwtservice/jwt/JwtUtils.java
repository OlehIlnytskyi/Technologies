package com.example.jwtservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.example.jwtservice.persistence.domain.UserDetails;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtils {

    private final static String SECRET_KEY = "gKxOf0o/lPL8r1wED+EMEoZYL31MI2upiyKS7TqKabM2sNYOfoH1E+s3Sd6oUMYx";
    private final static String ISSUER = "HomemadeJwtService";

    private final static Long HOURS_TO_EXPIRE = 24L;


    public static String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setSubject(userDetails.getUsername())
                .setIssuer(ISSUER)
                .setIssuedAt(getCurrentDate())
                .setExpiration(getExpirationDate())
                .compact();
    }

    private static Date getCurrentDate() {
        return Date.from(Instant.now());
    }

    private static Date getExpirationDate() {
        return Date.from(getCurrentDate().toInstant()
                .plus(HOURS_TO_EXPIRE, ChronoUnit.HOURS));
    }
}

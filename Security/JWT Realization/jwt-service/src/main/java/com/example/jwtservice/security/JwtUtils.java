package com.example.jwtservice.security;

import io.jsonwebtoken.*;

import com.example.jwtservice.persistence.domain.UserDetails;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtils {

    private final static String SECRET_KEY = "gKxOf0o/lPL8r1wED+EMEoZYL31MI2upiyKS7TqKabM2sNYOfoH1E+s3Sd6oUMYx";
    private final static String ISSUER = "HomemadeJwtService";

    private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private final static Long HOURS_TO_EXPIRE = 24L;


    public static String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .signWith(getSigningKey(), SIGNATURE_ALGORITHM)
                .setSubject(userDetails.getUsername())
                .setIssuer(ISSUER)
                .setIssuedAt(getCurrentDate())
                .setExpiration(getExpirationDate())
                .compact();
    }

    public static boolean isTokenValid(String jwtToken) {
        try {
            Date expirationDate = getTokenExpirationOrThrow(jwtToken);
            return Instant.now().isBefore(expirationDate.toInstant());
        } catch (Exception e) {
            return false;
        }
    }

    private static Key getSigningKey() {
        byte[] decodedKeyInBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKeyInBytes);
    }

    private static Date getCurrentDate() {
        return Date.from(Instant.now());
    }

    private static Date getExpirationDate() {
        return Date.from(getCurrentDate().toInstant()
                .plus(HOURS_TO_EXPIRE, ChronoUnit.HOURS));
    }

    private static Date getTokenExpirationOrThrow(String jwtToken)
            throws ExpiredJwtException, UnsupportedJwtException,MalformedJwtException, SignatureException, IllegalArgumentException {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getExpiration();
    }
}

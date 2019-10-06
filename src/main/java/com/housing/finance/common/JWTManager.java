package com.housing.finance.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTManager {

    private String key;

    public JWTManager(@Value("${Secret.Key}") String key) {
        this.key = key;
    }

    private static final int ONE_DAY = 1000 * 60 * 60 * 24;

    public String createJWT(String userId) {

        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + ONE_DAY);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("API Server")
                .setSubject("JWT Token")
                .setExpiration(tomorrow)
                .setIssuedAt(today)
                .claim("userId", userId)
                .signWith(generateKey(key))
                .compact();
    }

    private SecretKey generateKey(String key) {
        byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(byteKey);
    }

}

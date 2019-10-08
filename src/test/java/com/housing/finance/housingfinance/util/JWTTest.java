package com.housing.finance.housingfinance.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;

import java.security.Key;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class JWTTest {

    @Test
    public void testCreateJWT() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));

        String jws = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("API Server")
                .setSubject("JWT Token")
                .setExpiration(tomorrow)
                .setIssuedAt(today)
                .claim("userId", "test")
                .signWith(key)
                .compact();

        assertThat(Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().get("userId")).isEqualTo("test");
    }
}

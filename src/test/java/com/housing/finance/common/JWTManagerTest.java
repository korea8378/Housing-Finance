package com.housing.finance.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JWTManagerTest {

    @Test
    public void createJWT() {
        JWTManager jwtManager = new JWTManager("안녕하세요 test용 키입니다.");

        String token = jwtManager.createJWT("test");

        assertThat(token).isNotEmpty();
    }
}
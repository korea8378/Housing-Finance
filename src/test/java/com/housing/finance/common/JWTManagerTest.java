package com.housing.finance.common;

import com.housing.finance.exception.authentication.FailAuthenticationException;
import com.housing.finance.exception.user.ExistUserIdException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class JWTManagerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCreateJWT() {
        JWTManager jwtManager = new JWTManager("안녕하세요 test용 키입니다.");

        String token = jwtManager.createJWT("test");

        assertThat(token).isNotEmpty();
    }

    @Test
    public void testIsNotRefresh() {
        JWTManager jwtManager = new JWTManager("안녕하세요 test용 키입니다.");

        boolean resultTrue = jwtManager.isNotRefresh("true");
        boolean resultFalse = jwtManager.isNotRefresh("Bearer Token");

        assertThat(resultTrue).isTrue();
        assertThat(resultFalse).isFalse();
    }

    @Test
    public void testFailGetPayLoadUserId() {
        expectedException.expect(FailAuthenticationException.class);

        JWTManager jwtManager = new JWTManager("안녕하세요 test용 키입니다.");
        jwtManager.getPayLoadUserId("Fail Bearer Token");
    }

    @Test
    public void testFailAuthenticate() {
        expectedException.expect(FailAuthenticationException.class);
        JWTManager jwtManager = new JWTManager("안녕하세요 test용 키입니다.");

        jwtManager.authenticate("fail token");
    }

}
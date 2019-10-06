package com.housing.finance.user.service;

import com.housing.finance.common.JWTManager;
import com.housing.finance.exception.user.ExistUserIdException;
import com.housing.finance.user.domain.UserRepository;
import com.housing.finance.user.dto.ReqSignUpDto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserService userService;

    private UserRepository userRepository;

    private JWTManager jwtService;

    @Before
    public void mockUp() {
        userRepository = mock(UserRepository.class);
        jwtService = mock(JWTManager.class);

        userService = new UserService(userRepository, jwtService);
    }

    @Test
    public void testSignUpByExistUserId() {
        when(userRepository.existsByUserId(any())).thenReturn(true);

        expectedException.expect(ExistUserIdException.class);

        ReqSignUpDto reqSignUpDto = new ReqSignUpDto("test", "test");

        userService.signUp(reqSignUpDto);
    }
}
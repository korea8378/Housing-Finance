package com.housing.finance.user.service;

import com.housing.finance.common.JWTManager;
import com.housing.finance.exception.user.ExistUserIdException;
import com.housing.finance.exception.user.NotFoundUserException;
import com.housing.finance.exception.user.NotRefreshTokenException;
import com.housing.finance.user.domain.User;
import com.housing.finance.user.domain.UserRepository;
import com.housing.finance.user.dto.ReqUserDto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserService userService;

    private UserRepository userRepository;

    private JWTManager jwtManager;

    @Before
    public void mockUp() {
        userRepository = mock(UserRepository.class);
        jwtManager = mock(JWTManager.class);

        userService = new UserService(userRepository, jwtManager);
    }

    @Test
    public void testSignUpByExistUserId() {
        when(userRepository.existsByUserId(any())).thenReturn(true);

        expectedException.expect(ExistUserIdException.class);

        ReqUserDto reqSignUpDto = new ReqUserDto("test", "test");

        userService.signUp(reqSignUpDto);
    }

    @Test
    public void testFailSignIn() {
        when(userRepository.findByUserIdAndPassword(any(), any())).thenReturn(Optional.empty());

        expectedException.expect(NotFoundUserException.class);

        ReqUserDto reqSignUpDto = new ReqUserDto("test", "test");

        userService.signIn(reqSignUpDto);
    }

    @Test
    public void testRefreshHeaderIsNotSameAuthorizationHeader() {
        when(jwtManager.isNotRefresh(any())).thenReturn(true);

        expectedException.expect(NotRefreshTokenException.class);

        String authorizationHeader = "is Not Same Header";

        userService.refreshToken(authorizationHeader);
    }

}
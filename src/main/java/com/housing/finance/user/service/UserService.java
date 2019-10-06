package com.housing.finance.user.service;

import com.housing.finance.exception.user.ExistUserIdException;
import com.housing.finance.exception.user.NotFoundUserException;
import com.housing.finance.user.domain.User;
import com.housing.finance.user.domain.UserRepository;
import com.housing.finance.user.dto.ReqUserDto;
import com.housing.finance.user.dto.ResSignInDto;
import com.housing.finance.user.dto.ResSignUpDto;
import com.housing.finance.common.JWTManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JWTManager jwtManager;

    public UserService(UserRepository userRepository, JWTManager jwtManager) {
        this.userRepository = userRepository;
        this.jwtManager = jwtManager;
    }

    public ResSignInDto signIn(ReqUserDto reqUserDto) {
        User user = userRepository.findByUserIdAndPassword(reqUserDto.getUserId(), reqUserDto.getPassword())
                .orElseThrow(NotFoundUserException::new);

        String token = jwtManager.createJWT(user.getUserId());

        return new ResSignInDto(user.getUserId(), token);
    }

    public ResSignUpDto signUp(ReqUserDto reqUserDto) {
        if (isUserId(reqUserDto.getUserId())) {
            throw new ExistUserIdException();
        }

        User user = new User(reqUserDto.getUserId(), reqUserDto.getPassword());

        user = userRepository.save(user);

        String token = jwtManager.createJWT(user.getUserId());

        return new ResSignUpDto(token);
    }

    private boolean isUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }
}

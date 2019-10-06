package com.housing.finance.user.service;

import com.housing.finance.exception.user.ExistUserIdException;
import com.housing.finance.exception.user.NotFoundUserException;
import com.housing.finance.exception.user.NotRefreshTokenException;
import com.housing.finance.user.domain.User;
import com.housing.finance.user.domain.UserRepository;
import com.housing.finance.user.dto.ReqUserDto;
import com.housing.finance.user.dto.ResUserDto;
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

    public ResUserDto signIn(ReqUserDto reqUserDto) {
        User user = userRepository.findByUserIdAndPassword(reqUserDto.getUserId(), reqUserDto.getPassword())
                .orElseThrow(NotFoundUserException::new);

        String token = jwtManager.createJWT(user.getUserId());

        return new ResUserDto(token);
    }

    public ResUserDto signUp(ReqUserDto reqUserDto) {
        if (isUserId(reqUserDto.getUserId())) {
            throw new ExistUserIdException();
        }

        User user = new User(reqUserDto.getUserId(), reqUserDto.getPassword());

        user = userRepository.save(user);

        String token = jwtManager.createJWT(user.getUserId());

        return new ResUserDto(token);
    }

    private boolean isUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public ResUserDto refreshToken(String requestToken) {
        if (jwtManager.isNotRefresh(requestToken)) {
            throw new NotRefreshTokenException();
        }

        String userId = jwtManager.getPayLoadUserId(requestToken);

        String refreshToken = jwtManager.createJWT(userId);

        return new ResUserDto(refreshToken);
    }
}

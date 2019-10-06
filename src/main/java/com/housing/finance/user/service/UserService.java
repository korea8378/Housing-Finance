package com.housing.finance.user.service;

import com.housing.finance.exception.user.ExistUserIdException;
import com.housing.finance.user.domain.User;
import com.housing.finance.user.domain.UserRepository;
import com.housing.finance.user.dto.ReqSignUpDto;
import com.housing.finance.user.dto.ResSignUpDto;
import com.housing.finance.common.JWTManager;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JWTManager jwtManager;

    public UserService(UserRepository userRepository, JWTManager jwtManager) {
        this.userRepository = userRepository;
        this.jwtManager = jwtManager;
    }

    public ResSignUpDto signUp(ReqSignUpDto reqSignUpDto) {
        if (isUserId(reqSignUpDto.getUserId())) {
            throw new ExistUserIdException();
        }

        User user = createUser(reqSignUpDto);

        user = userRepository.save(user);

        String token = jwtManager.createJWT(user.getUserId());

        return new ResSignUpDto(token);
    }

    private boolean isUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    private User createUser(ReqSignUpDto reqSignUpDto) {
        String hashPw = encodingPassword(reqSignUpDto.getPassword());
        return new User(reqSignUpDto.getUserId(), hashPw);
    }

    private String encodingPassword(String reqPw) {
        return BCrypt.hashpw(reqPw, BCrypt.gensalt());
    }
}

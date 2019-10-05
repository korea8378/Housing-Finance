package com.housing.finance.user.service;

import com.housing.finance.user.domain.User;
import com.housing.finance.user.domain.UserRepository;
import com.housing.finance.user.dto.ReqSignUpDto;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(ReqSignUpDto reqSignUpDto) {
        if (isUserId(reqSignUpDto.getUserId())) {
            throw new NullPointerException();
        }

        String hashPw = encodingPassword(reqSignUpDto.getPassword());
        User user = new User(reqSignUpDto.getUserId(), hashPw);
        userRepository.save(user);
    }

    private boolean isUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    private String encodingPassword(String reqPw) {
        return BCrypt.hashpw(reqPw, BCrypt.gensalt());
    }
}

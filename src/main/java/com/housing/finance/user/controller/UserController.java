package com.housing.finance.user.controller;

import com.housing.finance.exception.RequestNullFieldException;
import com.housing.finance.user.dto.ReqSignUpDto;
import com.housing.finance.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid ReqSignUpDto reqSignUpDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestNullFieldException();
        }

        userService.signUp(reqSignUpDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

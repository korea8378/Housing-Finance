package com.housing.finance.user.controller;

import com.housing.finance.exception.RequestNullFieldException;
import com.housing.finance.user.dto.ReqUserDto;
import com.housing.finance.user.dto.ResSignInDto;
import com.housing.finance.user.dto.ResSignUpDto;
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
    public ResponseEntity<ResSignUpDto> signUp(@RequestBody @Valid ReqUserDto reqUserDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestNullFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(reqUserDto));
    }

    @PostMapping("/user/sign-in")
    public ResponseEntity<ResSignInDto> signIn(@RequestBody @Valid ReqUserDto reqUserDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestNullFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(reqUserDto));
    }
}

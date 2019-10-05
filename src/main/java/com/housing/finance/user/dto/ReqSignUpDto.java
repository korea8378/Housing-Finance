package com.housing.finance.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqSignUpDto {

    @NotNull
    private String userId;

    @NotNull
    private String password;
}

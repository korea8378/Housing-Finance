package com.housing.finance.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResSignInDto {

    private String userId;
    private String token;
}

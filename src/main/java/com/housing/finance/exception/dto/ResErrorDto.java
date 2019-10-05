package com.housing.finance.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResErrorDto {

    private int code;
    private String massage;
}
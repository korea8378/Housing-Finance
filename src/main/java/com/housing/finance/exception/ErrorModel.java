package com.housing.finance.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorModel {
    private int code;
    private HttpStatus httpStatus;
    private String massage;
}

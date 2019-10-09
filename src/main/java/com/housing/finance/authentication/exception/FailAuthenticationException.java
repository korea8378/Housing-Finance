package com.housing.finance.authentication.exception;

import com.housing.finance.exception.BaseException;
import com.housing.finance.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class FailAuthenticationException extends BaseException {

    public FailAuthenticationException() {
        this(HttpStatus.UNAUTHORIZED);
    }

    public FailAuthenticationException(HttpStatus httpStatus) {
        this(4101, httpStatus);
    }

    public FailAuthenticationException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Fail Authentication - check token")
                .build());
    }
}
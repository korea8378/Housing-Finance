package com.housing.finance.exception.user;

import com.housing.finance.exception.BaseException;
import com.housing.finance.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotRefreshTokenException extends BaseException {

    public NotRefreshTokenException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotRefreshTokenException(HttpStatus httpStatus) {
        this(4004, httpStatus);
    }

    public NotRefreshTokenException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("check Authorization Header")
                .build());
    }
}

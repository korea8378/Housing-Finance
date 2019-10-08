package com.housing.finance.user.exception;

import com.housing.finance.exception.BaseException;
import com.housing.finance.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsNotEqualToPasswordException extends BaseException {

    public IsNotEqualToPasswordException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public IsNotEqualToPasswordException(HttpStatus httpStatus) {
        this(4005, httpStatus);
    }

    public IsNotEqualToPasswordException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("check password")
                .build());
    }
}

package com.housing.finance.exception.user;

import com.housing.finance.exception.BaseException;
import com.housing.finance.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BaseException {

    public NotFoundUserException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundUserException(HttpStatus httpStatus) {
        this(4002, httpStatus);
    }

    public NotFoundUserException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("check userId or password")
                .build());
    }
}

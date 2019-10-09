package com.housing.finance.supportamount.infrastructure.exception;

import com.housing.finance.exception.BaseException;
import com.housing.finance.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundAmountException extends BaseException {
    public NotFoundAmountException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundAmountException(HttpStatus httpStatus) {
        this(4007, httpStatus);
    }

    public NotFoundAmountException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Amount - not registered data")
                .build());
    }
}
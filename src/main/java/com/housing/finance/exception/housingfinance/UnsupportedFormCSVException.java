package com.housing.finance.exception.housingfinance;

import com.housing.finance.exception.BaseException;
import com.housing.finance.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class UnsupportedFormCSVException extends BaseException {

    public UnsupportedFormCSVException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public UnsupportedFormCSVException(HttpStatus httpStatus) {
        this(4004, httpStatus);
    }

    public UnsupportedFormCSVException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Unsupported Form CSV - check CSV header")
                .build());
    }
}
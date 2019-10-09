package com.housing.finance.supportamount.exception;

import com.housing.finance.exception.BaseException;
import com.housing.finance.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundBankException extends BaseException {

    public NotFoundBankException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundBankException(HttpStatus httpStatus) {
        this(4006, httpStatus);
    }

    public NotFoundBankException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Bank - Check Bank Name")
                .build());
    }
}

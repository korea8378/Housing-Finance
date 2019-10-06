package com.housing.finance.exception.user;

import com.housing.finance.exception.BaseException;
import com.housing.finance.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class ExistUserIdException extends BaseException {

    public ExistUserIdException() {
        this(HttpStatus.CONFLICT);
    }

    public ExistUserIdException(HttpStatus httpStatus) {
        this(4901, httpStatus);
    }

    public ExistUserIdException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Exist UserId")
                .build());
    }
}

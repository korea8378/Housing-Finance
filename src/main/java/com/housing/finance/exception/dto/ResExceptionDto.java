package com.housing.finance.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResExceptionDto {

    private ResErrorDto error;
}

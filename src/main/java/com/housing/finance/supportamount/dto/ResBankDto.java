package com.housing.finance.supportamount.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResBankDto {
    private String bankName;
    private String bankCode;
}

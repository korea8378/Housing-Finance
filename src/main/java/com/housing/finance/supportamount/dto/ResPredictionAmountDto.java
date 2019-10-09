package com.housing.finance.supportamount.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResPredictionAmountDto {
    private String bank;
    private Long year;
    private Long month;
    private Long amount;
}

package com.housing.finance.supportamount.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReqPredictionAmountDto {
    @NotNull
    private String bank;
    @NotNull
    private Long month;
}

package com.housing.finance.housingfinance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResAvgOfBankDto {
    private String bank;

    @JsonProperty("support_amount")
    private List<ResDetailAvgAmountDto> supportAmount;

    public ResAvgOfBankDto(ResDetailAvgAmountDto max, ResDetailAvgAmountDto min) {
        this.bank = max.getBank();
        supportAmount = new ArrayList<>();
        supportAmount.add(min);
        supportAmount.add(max);
    }
}

package com.housing.finance.supportamount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResMaxMinAvgAMountDto {
    private String bank;

    @JsonProperty("support_amount")
    private List<ResDetailAvgAmountDto> supportAmount;

    public ResMaxMinAvgAMountDto(ResDetailAvgAmountDto max, ResDetailAvgAmountDto min) {
        this.bank = max.getBank();
        supportAmount = new ArrayList<>();
        supportAmount.add(min);
        supportAmount.add(max);
    }
}

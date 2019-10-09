package com.housing.finance.supportamount.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class ResDetailAvgAmountDto {
    @JsonIgnore
    private String bank;
    private Long year;
    private Long amount;

    public ResDetailAvgAmountDto(String bank, Long year, Double amount) {
        this.bank = bank;
        this.year = year;
        this.amount = Math.round(amount);
    }

}

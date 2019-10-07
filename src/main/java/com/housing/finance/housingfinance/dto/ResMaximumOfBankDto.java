package com.housing.finance.housingfinance.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class ResMaximumOfBankDto {

    private Long year;
    private String bank;
    @JsonIgnore
    private Long amount;

    public ResMaximumOfBankDto(Long year, String bank, Long amount) {
        this.year = year;
        this.bank = bank;
        this.amount = amount;
    }
}

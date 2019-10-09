package com.housing.finance.supportamount.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class ResMaxAmountDto {

    private Long year;
    private String bank;
    @JsonIgnore
    private Long amount;

    public ResMaxAmountDto(Long year, String bank, Long amount) {
        this.year = year;
        this.bank = bank;
        this.amount = amount;
    }
}

package com.housing.finance.supportamount.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ResBanksDto {
    List<ResBankDto> banks;

    public ResBanksDto() {
        banks = new ArrayList<>();
    }
}

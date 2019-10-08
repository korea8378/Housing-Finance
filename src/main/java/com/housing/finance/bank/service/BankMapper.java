package com.housing.finance.bank.service;

import com.housing.finance.bank.domain.Bank;
import com.housing.finance.bank.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankMapper {

    public ResBanksDto toResBanksDto(List<Bank> banks) {
        return new ResBanksDto(banks.stream()
                .map(bank -> ResBankDto.builder()
                        .bankName(bank.getName())
                        .bankCode(bank.getCode())
                        .build())
                .collect(Collectors.toList()));
    }

    public ResBanksDto toNoneBanksDto() {
        return new ResBanksDto();
    }
}

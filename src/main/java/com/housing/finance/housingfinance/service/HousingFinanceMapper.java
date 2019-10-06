package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.domain.Bank;
import com.housing.finance.housingfinance.dto.ResBankDto;
import com.housing.finance.housingfinance.dto.ResBanksDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HousingFinanceMapper {

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

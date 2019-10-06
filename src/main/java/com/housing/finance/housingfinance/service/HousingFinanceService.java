package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.domain.Bank;
import com.housing.finance.housingfinance.domain.BankRepository;
import com.housing.finance.housingfinance.dto.ResBanksDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousingFinanceService {

    private final BankRepository bankRepository;
    private final HousingFinanceMapper housingFinanceMapper;

    public HousingFinanceService(BankRepository bankRepository,
                                 HousingFinanceMapper housingFinanceMapper) {
        this.bankRepository = bankRepository;
        this.housingFinanceMapper = housingFinanceMapper;
    }

    public ResBanksDto getBanks() {
        List<Bank> banks = bankRepository.findAll();

        if (banks.isEmpty()) {
            return housingFinanceMapper.toNoneBanksDto();
        }

        return housingFinanceMapper.toResBanksDto(banks);
    }
}

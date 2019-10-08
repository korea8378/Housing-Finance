package com.housing.finance.bank.application;

import com.housing.finance.bank.domain.Bank;
import com.housing.finance.bank.domain.BankRepository;
import com.housing.finance.bank.dto.ResBanksDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    public BankService(BankRepository bankRepository,
                       BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    public ResBanksDto getBanks() {
        List<Bank> banks = bankRepository.findAll();

        if (banks.isEmpty()) {
            return bankMapper.toNoneBanksDto();
        }

        return bankMapper.toResBanksDto(banks);
    }
}

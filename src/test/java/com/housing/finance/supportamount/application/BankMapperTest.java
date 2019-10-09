package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.domain.bank.Bank;
import com.housing.finance.supportamount.dto.ResBanksDto;
import com.housing.finance.supportamount.application.BankMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BankMapperTest {

    @Test
    public void MappingToResBanksDto() {
        BankMapper bankMapper = new BankMapper();

        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank("은행1", "code1"));
        banks.add(new Bank("은행2", "code2"));
        banks.add(new Bank("은행3", "code3"));

        ResBanksDto resBanksDto = bankMapper.toResBanksDto(banks);

        assertThat(resBanksDto.getBanks().get(0).getBankName()).isEqualTo("은행1");
        assertThat(resBanksDto.getBanks().get(1).getBankName()).isEqualTo("은행2");
    }

}
package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.domain.bank.BankRepository;
import com.housing.finance.supportamount.dto.ResBanksDto;
import com.housing.finance.supportamount.application.BankMapper;
import com.housing.finance.supportamount.application.BankService;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankServiceTest {
    private BankService bankService;

    private BankRepository bankRepository;

    private BankMapper bankMapper;


    @Before
    public void mockUp() {
        bankRepository = mock(BankRepository.class);

        bankMapper = mock(BankMapper.class);

        bankService = new BankService(bankRepository, bankMapper);
    }

    @Test
    public void testBanksIsEmpty() {
        when(bankRepository.findAll()).thenReturn(Collections.emptyList());
        when(bankMapper.toNoneBanksDto()).thenReturn(new ResBanksDto());

        ResBanksDto resBanksDto = bankService.getBanks();

        assertThat(resBanksDto.getBanks()).isEmpty();
    }
}
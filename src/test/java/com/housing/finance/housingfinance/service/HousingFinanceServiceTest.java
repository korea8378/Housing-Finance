package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.domain.BankRepository;
import com.housing.finance.housingfinance.dto.ResBanksDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HousingFinanceServiceTest {
    private HousingFinanceService housingFinanceService;

    private BankRepository bankRepository;

    private HousingFinanceMapper housingFinanceMapper;


    @Before
    public void mockUp() {
        bankRepository = mock(BankRepository.class);

        housingFinanceMapper = mock(HousingFinanceMapper.class);

        housingFinanceService = new HousingFinanceService(bankRepository, housingFinanceMapper);
    }

    @Test
    public void testBanksIsEmpty() {
        when(bankRepository.findAll()).thenReturn(Collections.emptyList());
        when(housingFinanceMapper.toNoneBanksDto()).thenReturn(new ResBanksDto());

        ResBanksDto resBanksDto = housingFinanceService.getBanks();

        assertThat(resBanksDto.getBanks()).isEmpty();
    }
}
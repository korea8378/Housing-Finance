package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.domain.bank.BankRepository;
import com.housing.finance.supportamount.domain.supportamount.SupportAmountRepository;
import com.housing.finance.supportamount.dto.ReqPredictionAmountDto;
import com.housing.finance.supportamount.exception.NotFoundBankException;
import com.housing.finance.supportamount.util.PredictiveCalculator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupportAmountPredictServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BankRepository bankRepository;
    private SupportAmountRepository supportAmountRepository;
    private PredictiveCalculator predictiveCalculator;
    private SupportAmountPredictService supportAmountPredictService;


    @Before
    public void mockUp() {
        bankRepository = mock(BankRepository.class);
        supportAmountRepository = mock(SupportAmountRepository.class);
        predictiveCalculator = mock(PredictiveCalculator.class);

        supportAmountPredictService = new SupportAmountPredictService(
                bankRepository, supportAmountRepository, predictiveCalculator);
    }

    @Test
    public void testNotFoundBank() {
        when(bankRepository.findByName(any())).thenReturn(Optional.empty());
        ReqPredictionAmountDto reqPredictionAmountDto = new ReqPredictionAmountDto();

        expectedException.expect(NotFoundBankException.class);

        supportAmountPredictService.getByBankAndMonth(reqPredictionAmountDto);
    }
}
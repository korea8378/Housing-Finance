package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.domain.bank.Bank;
import com.housing.finance.supportamount.domain.bank.BankRepository;
import com.housing.finance.supportamount.domain.supportamount.SupportAmount;
import com.housing.finance.supportamount.domain.supportamount.SupportAmountRepository;
import com.housing.finance.supportamount.dto.ReqPredictionAmountDto;
import com.housing.finance.supportamount.dto.ResPredictionAmountDto;
import com.housing.finance.supportamount.exception.NotFoundBankException;
import com.housing.finance.supportamount.util.PredictiveCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportAmountPredictService {

    private final BankRepository bankRepository;
    private final SupportAmountRepository supportAmountRepository;
    private final PredictiveCalculator predictiveCalculator;
    private final static Long PREDICTION_YEAR = 2018L;

    public SupportAmountPredictService(BankRepository bankRepository,
                                       SupportAmountRepository supportAmountRepository,
                                       PredictiveCalculator predictiveCalculator) {
        this.bankRepository = bankRepository;
        this.supportAmountRepository = supportAmountRepository;
        this.predictiveCalculator = predictiveCalculator;
    }

    public ResPredictionAmountDto getByBankAndMonth(ReqPredictionAmountDto reqPredictionAmountDto) {

        Bank bank = bankRepository.findByName(reqPredictionAmountDto.getBank())
                .orElseThrow(NotFoundBankException::new);

        List<SupportAmount> supportAmounts =
                supportAmountRepository.findByBankName(reqPredictionAmountDto.getBank());

        Long predictionResult = predictiveCalculator.getSupportAmount(
                supportAmounts, reqPredictionAmountDto, PREDICTION_YEAR);

        return ResPredictionAmountDto.builder()
                .bank(bank.getCode())
                .month(reqPredictionAmountDto.getMonth())
                .year(PREDICTION_YEAR)
                .amount(predictionResult)
                .build();
    }


}


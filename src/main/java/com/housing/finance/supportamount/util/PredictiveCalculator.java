package com.housing.finance.supportamount.util;

import com.housing.finance.supportamount.domain.supportamount.SupportAmount;
import com.housing.finance.supportamount.dto.ReqPredictionAmountDto;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PredictiveCalculator {

    private final static Long OCT = 10L;
    private final static String FRONT_MONTH = "0";

    public Long getSupportAmount(List<SupportAmount> supportAmounts,
                                 ReqPredictionAmountDto reqPredictionAmountDto,
                                 Long year) {
        SimpleRegression simpleRegression = new SimpleRegression(true);

        supportAmounts.forEach(supportAmount -> this.registerInSimpleRegression(simpleRegression, supportAmount));

        Double predictionDate = makePredictionDate(reqPredictionAmountDto, year);

        Double predictionResult = simpleRegression.predict(predictionDate);

        return Math.round(predictionResult);
    }

    private Double makePredictionDate(ReqPredictionAmountDto reqPredictionAmountDto, Long year) {
        StringBuilder date = new StringBuilder();

        if (beforeOct(reqPredictionAmountDto.getMonth())) {
            date = createBeforeOctDate(date, year, reqPredictionAmountDto.getMonth());
        }

        if (OverOct(reqPredictionAmountDto.getMonth())) {
            date = createOverOctDate(date, year, reqPredictionAmountDto.getMonth());
        }

        return Double.parseDouble(date.toString());
    }

    private void registerInSimpleRegression(SimpleRegression simpleRegression, SupportAmount supportAmount) {
        Long amount = supportAmount.getAmount();
        Long year = supportAmount.getYear();
        Long month = supportAmount.getMonth();

        StringBuilder date = new StringBuilder();

        if (beforeOct(supportAmount.getMonth())) {
            date = createBeforeOctDate(date, year, month);
        }

        if (OverOct(supportAmount.getMonth())) {
            date = createOverOctDate(date, year, month);
        }

        simpleRegression.addData(Double.parseDouble(date.toString()), amount.doubleValue());
    }

    private boolean beforeOct(Long month) {
        return month < OCT;
    }

    private boolean OverOct(Long month) {
        return month >= OCT;
    }

    private StringBuilder createBeforeOctDate(StringBuilder date, Long year, Long month) {
        return date.append(year).append(FRONT_MONTH).append(month);
    }

    private StringBuilder createOverOctDate(StringBuilder date, Long year, Long month) {
        return date.append(year).append(month);
    }
}

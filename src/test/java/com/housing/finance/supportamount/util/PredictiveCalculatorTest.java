package com.housing.finance.supportamount.util;

import com.housing.finance.supportamount.domain.supportamount.SupportAmount;
import com.housing.finance.supportamount.dto.ReqPredictionAmountDto;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PredictiveCalculatorTest {


    @Test
    public void testGetSupportAmount() {
        PredictiveCalculator predictiveCalculator = new PredictiveCalculator();
        List<SupportAmount> supportAmounts = new ArrayList<>();
        ReqPredictionAmountDto reqPredictionAmountDto = new ReqPredictionAmountDto();
        reqPredictionAmountDto.setBank("국민은행");
        reqPredictionAmountDto.setMonth(2L);

        SupportAmount supportAmountOne = new SupportAmount(2014L, 2L, "국민은행", 1111L);
        SupportAmount supportAmountTwo = new SupportAmount(2015L, 2L, "국민은행", 2222L);
        SupportAmount supportAmountThree = new SupportAmount(2016L, 2L, "국민은행", 3333L);
        SupportAmount supportAmountFour = new SupportAmount(2017L, 2L, "국민은행", 4444L);


        supportAmounts.add(supportAmountOne);
        supportAmounts.add(supportAmountTwo);
        supportAmounts.add(supportAmountThree);
        supportAmounts.add(supportAmountFour);
        Long actual = createActualAmount();


        Long result = predictiveCalculator.getSupportAmount(
                supportAmounts, reqPredictionAmountDto, 2018L);


        assertThat(result).isEqualTo(actual);
    }


    private Long createActualAmount() {
        SimpleRegression simpleRegression = new SimpleRegression(true);

        simpleRegression.addData(201402, 1111);
        simpleRegression.addData(201502, 2222);
        simpleRegression.addData(201602, 3333);
        simpleRegression.addData(201702, 4444);

        return Math.round(simpleRegression.predict(201802));
    }
}
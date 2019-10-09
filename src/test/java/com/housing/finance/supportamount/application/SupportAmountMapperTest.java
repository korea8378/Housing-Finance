package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.domain.SupportAmount;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SupportAmountMapperTest {

    @Test
    public void testMappingCSVToHousingFinance() {
        SupportAmountMapper supportAmountMapper = new SupportAmountMapper();
        SupportAmountVO supportAmountVO = new SupportAmountVO();
        supportAmountVO.setYear("2019");
        supportAmountVO.setMonth("12");
        supportAmountVO.setEtcBank("1");
        supportAmountVO.setHanaBank("2");
        supportAmountVO.setHousingCityFund("3");
        supportAmountVO.setKookminBank("4");
        supportAmountVO.setKoreaCityBank("5");
        supportAmountVO.setKoreaExchangeBank("6");
        supportAmountVO.setNonghyupSuhyupBank("7");
        supportAmountVO.setShinhanBank("8");
        supportAmountVO.setWooriBank("9");

        List<SupportAmount> supportAmounts = supportAmountMapper.toSupportAmounts(supportAmountVO);

        assertThat(supportAmounts.get(0).getYear()).isEqualTo(2019L);
        assertThat(supportAmounts.get(0).getName()).isEqualTo("주택도시기금");
        assertThat(supportAmounts.get(0).getAmount()).isEqualTo(3L);

    }

}
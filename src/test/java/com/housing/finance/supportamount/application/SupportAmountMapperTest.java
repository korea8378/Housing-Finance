package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.domain.supportamount.SupportAmount;
import com.housing.finance.supportamount.exception.UnsupportedFormCSVException;
import com.housing.finance.user.exception.NotFoundUserException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SupportAmountMapperTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testMappingCSVToSupportAmount() {
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
        assertThat(supportAmounts.get(0).getBankName()).isEqualTo("주택도시기금");
        assertThat(supportAmounts.get(0).getAmount()).isEqualTo(3L);

    }

    @Test
    public void testFailMappingCSVToSupportAmount() {
        SupportAmountMapper supportAmountMapper = new SupportAmountMapper();
        SupportAmountVO supportAmountVO = new SupportAmountVO();
        supportAmountVO.setYear("Fail test");
        supportAmountVO.setMonth("Fail test");
        supportAmountVO.setEtcBank("Fail test");
        supportAmountVO.setHanaBank("Fail test");
        supportAmountVO.setHousingCityFund("Fail test");
        supportAmountVO.setKookminBank("Fail test");
        supportAmountVO.setKoreaCityBank("Fail test");
        supportAmountVO.setKoreaExchangeBank("Fail test");
        supportAmountVO.setNonghyupSuhyupBank("Fail test");
        supportAmountVO.setShinhanBank("Fail test");
        supportAmountVO.setWooriBank("Fail test");

        expectedException.expect(UnsupportedFormCSVException.class);

        supportAmountMapper.toSupportAmounts(supportAmountVO);


    }

}
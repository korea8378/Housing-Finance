package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.domain.HousingFinance;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HousingFinanceMapperTest {


    @Test
    public void testMappingCSVToHousingFinance() {
        HousingFinanceMapper housingFinanceMapper = new HousingFinanceMapper();
        HousingFinanceVO housingFinanceVO = new HousingFinanceVO();
        housingFinanceVO.setYear("2019");
        housingFinanceVO.setMonth("12");
        housingFinanceVO.setEtcBank("1");
        housingFinanceVO.setHanaBank("2");
        housingFinanceVO.setHousingCityFund("3");
        housingFinanceVO.setKookminBank("4");
        housingFinanceVO.setKoreaCityBank("5");
        housingFinanceVO.setKoreaExchangeBank("6");
        housingFinanceVO.setNonghyupSuhyupBank("7");
        housingFinanceVO.setShinhanBank("8");
        housingFinanceVO.setWooriBank("9");

        List<HousingFinance> housingFinances = housingFinanceMapper.toHousingFinance(housingFinanceVO);

        assertThat(housingFinances.get(0).getYear()).isEqualTo(2019L);
        assertThat(housingFinances.get(0).getName()).isEqualTo("주택도시기금");
        assertThat(housingFinances.get(0).getAmount()).isEqualTo(3L);

    }

}
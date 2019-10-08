package com.housing.finance.housingfinance.application;

import com.housing.finance.housingfinance.exception.UnsupportedFormCSVException;
import com.housing.finance.housingfinance.domain.HousingFinance;
import com.housing.finance.housingfinance.dto.ResAvgOfBankDto;
import com.housing.finance.housingfinance.dto.ResDetailAvgAmountDto;
import com.housing.finance.housingfinance.dto.ResTotalAmountDto;
import com.housing.finance.housingfinance.dto.ResTotalHousingFinanceDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HousingFinanceMapper {

    private static final String COMMA = ",";
    private static final String EMPTY = "";


    public List<HousingFinance> toHousingFinance(HousingFinanceVO housingFinanceVO) {
        List<HousingFinance> housingFinances = new ArrayList<>();
        String year = housingFinanceVO.getYear();
        String month = housingFinanceVO.getMonth();

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.HOUSING_CITY_FUND.getName(), housingFinanceVO.getHousingCityFund()));

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.KOOKMIN_BANK.getName(), housingFinanceVO.getKookminBank()));

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.WOORI_BANK.getName(), housingFinanceVO.getWooriBank()));

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.SHINHAN_BANK.getName(), housingFinanceVO.getShinhanBank()));

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.KOREA_CITY_BANK.getName(), housingFinanceVO.getKoreaCityBank()));

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.HANA_BANK.getName(), housingFinanceVO.getHanaBank()));

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.NONGHYUP_SUHYUP_BANK.getName(), housingFinanceVO.getNonghyupSuhyupBank()));

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.KOREA_EXCHANGE_BANK.getName(), housingFinanceVO.getKoreaExchangeBank()));

        housingFinances.add(toHousingFinance(year, month,
                CSVHeader.ETC_BANK.getName(), housingFinanceVO.getEtcBank()));

        return housingFinances;

    }

    private HousingFinance toHousingFinance(String year, String month, String bank, String amount) {
        return new HousingFinance(convertingToLong(year), convertingToLong(month),
                bank, convertingToLong(amount));
    }

    private Long convertingToLong(String text) {
        Long result;
        try {
            result = Long.parseLong(text.replaceAll(COMMA, EMPTY));
        } catch (NumberFormatException e) {
            throw new UnsupportedFormCSVException();
        }
        return result;
    }

    public ResTotalHousingFinanceDto toResTotalHousingFinanceDto(List<ResTotalAmountDto> resTotalAmountDtoList) {
        return ResTotalHousingFinanceDto.builder()
                .name("주택금융 공급현황")
                .amountList(resTotalAmountDtoList)
                .build();
    }

    public ResAvgOfBankDto toResAvgOfBankDto(ResDetailAvgAmountDto max, ResDetailAvgAmountDto min) {
        return new ResAvgOfBankDto(max, min);
    }
}

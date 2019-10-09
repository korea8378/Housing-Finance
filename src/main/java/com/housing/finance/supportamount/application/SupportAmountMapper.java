package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dto.ResDetailTotalAmountDto;
import com.housing.finance.supportamount.dto.ResTotalAmountsDto;
import com.housing.finance.supportamount.exception.UnsupportedFormCSVException;
import com.housing.finance.supportamount.domain.supportamount.SupportAmount;
import com.housing.finance.supportamount.dto.ResMaxMinAvgAMountDto;
import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupportAmountMapper {

    private static final String COMMA = ",";
    private static final String EMPTY = "";


    public List<SupportAmount> toSupportAmounts(SupportAmountVO housingFinanceVO) {
        List<SupportAmount> housingFinances = new ArrayList<>();
        String year = housingFinanceVO.getYear();
        String month = housingFinanceVO.getMonth();

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.HOUSING_CITY_FUND.getName(), housingFinanceVO.getHousingCityFund()));

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.KOOKMIN_BANK.getName(), housingFinanceVO.getKookminBank()));

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.WOORI_BANK.getName(), housingFinanceVO.getWooriBank()));

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.SHINHAN_BANK.getName(), housingFinanceVO.getShinhanBank()));

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.KOREA_CITY_BANK.getName(), housingFinanceVO.getKoreaCityBank()));

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.HANA_BANK.getName(), housingFinanceVO.getHanaBank()));

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.NONGHYUP_SUHYUP_BANK.getName(), housingFinanceVO.getNonghyupSuhyupBank()));

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.KOREA_EXCHANGE_BANK.getName(), housingFinanceVO.getKoreaExchangeBank()));

        housingFinances.add(toSupportAmount(year, month,
                CSVHeader.ETC_BANK.getName(), housingFinanceVO.getEtcBank()));

        return housingFinances;

    }

    private SupportAmount toSupportAmount(String year, String month, String bank, String amount) {
        return new SupportAmount(convertingToLong(year), convertingToLong(month),
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

    public ResTotalAmountsDto toResTotalAmountsDto(List<ResDetailTotalAmountDto> resDetailTotalAmountDtos) {
        return ResTotalAmountsDto.builder()
                .name("주택금융 공급현황")
                .amountList(resDetailTotalAmountDtos)
                .build();
    }

    public ResMaxMinAvgAMountDto toResMaxMinAvgAMountDto(ResDetailAvgAmountDto max, ResDetailAvgAmountDto min) {
        return new ResMaxMinAvgAMountDto(max, min);
    }
}

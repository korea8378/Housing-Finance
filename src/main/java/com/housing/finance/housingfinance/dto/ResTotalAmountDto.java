package com.housing.finance.housingfinance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResTotalAmountDto {
    private String year;

    @JsonProperty("total_amount")
    private Long totalAmount;

    @JsonProperty("detail_amount")
    private ResDetailAmount resDetailAmount;

    public ResTotalAmountDto(Long year,
                             Long totalAmount, Long housingCityFund,
                             Long koominBank, Long wooriBank,
                             Long shinhanBank, Long koreaCityBank,
                             Long hanaBank, Long nonghyupSuhyupBank,
                             Long koreaExchangeBank, Long etcBank) {

        this.year = year.toString() + "년";
        this.totalAmount = totalAmount;
        resDetailAmount = ResDetailAmount.builder()
                .housingCityFund(housingCityFund)
                .koominBank(koominBank)
                .wooriBank(wooriBank)
                .shinhanBank(shinhanBank)
                .koreaCityBank(koreaCityBank)
                .hanaBank(hanaBank)
                .nonghyupSuhyupBank(nonghyupSuhyupBank)
                .koreaExchangeBank(koreaExchangeBank)
                .etcBank(etcBank)
                .build();
    }
}

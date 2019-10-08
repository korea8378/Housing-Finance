package com.housing.finance.housingfinance.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResTotalHousingFinanceDto {

    private String name;
    private List<ResTotalAmountDto> amountList;
}

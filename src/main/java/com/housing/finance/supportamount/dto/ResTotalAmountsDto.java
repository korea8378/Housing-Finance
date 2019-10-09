package com.housing.finance.supportamount.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResTotalAmountsDto {

    private String name;
    private List<ResDetailTotalAmountDto> amountList;
}

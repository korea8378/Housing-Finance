package com.housing.finance.housingfinance.dao;

import com.housing.finance.housingfinance.dto.ResDetailAvgAmountDto;

public interface AvgBankDao {

    ResDetailAvgAmountDto selectMaxAvgAmountGroupByYear();
    ResDetailAvgAmountDto selectMinAvgAmountGroupByYear();
}

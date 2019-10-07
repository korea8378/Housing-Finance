package com.housing.finance.housingfinance.dao;

import com.housing.finance.housingfinance.dto.ResMaximumOfBankDto;

public interface MaximumBankDao {

    ResMaximumOfBankDto selectMaximumAmountGroupByYear();
}

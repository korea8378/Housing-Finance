package com.housing.finance.supportamount.dao;

import com.housing.finance.supportamount.dto.ResMaxAmountDto;

public interface MaxBankDao {

    ResMaxAmountDto selectMaxAmountGroupByYear();
}

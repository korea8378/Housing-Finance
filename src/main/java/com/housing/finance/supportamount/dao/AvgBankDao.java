package com.housing.finance.supportamount.dao;

import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;

public interface AvgBankDao {

    ResDetailAvgAmountDto selectMaxAvgAmountGroupByYear();
    ResDetailAvgAmountDto selectMinAvgAmountGroupByYear();
}

package com.housing.finance.supportamount.dao;

import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;

public interface AvgAmountDao {

    ResDetailAvgAmountDto selectMaxGroupByYear();
    ResDetailAvgAmountDto selectMinGroupByYear();
}

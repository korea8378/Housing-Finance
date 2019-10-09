package com.housing.finance.supportamount.dao;

import com.housing.finance.supportamount.dto.ResDetailTotalAmountDto;

import java.util.List;

public interface TotalAmountDao {

    List<ResDetailTotalAmountDto> selectGroupByYear();

}

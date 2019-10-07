package com.housing.finance.housingfinance.dao;

import com.housing.finance.housingfinance.dto.ResTotalAmountDto;

import java.util.List;

public interface TotalAmountDao {

    List<ResTotalAmountDto> selectGroupByYear();

}

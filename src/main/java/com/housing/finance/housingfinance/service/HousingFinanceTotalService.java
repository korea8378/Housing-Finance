package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.dao.JpaTotalAmountDao;
import com.housing.finance.housingfinance.dto.ResTotalAmountDto;
import com.housing.finance.housingfinance.dto.ResTotalHousingFinanceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousingFinanceTotalService {

    private final JpaTotalAmountDao jpaTotalViewDao;
    private final HousingFinanceMapper housingFinanceMapper;

    public HousingFinanceTotalService(JpaTotalAmountDao jpaTotalViewDao,
                                      HousingFinanceMapper housingFinanceMapper) {
        this.jpaTotalViewDao = jpaTotalViewDao;
        this.housingFinanceMapper = housingFinanceMapper;
    }

    public ResTotalHousingFinanceDto getTotalAmountByYear() {
        List<ResTotalAmountDto> resTotalAmountDtoList = jpaTotalViewDao.selectGroupByYear();

        return housingFinanceMapper.toResTotalHousingFinanceDto(resTotalAmountDtoList);
    }
}

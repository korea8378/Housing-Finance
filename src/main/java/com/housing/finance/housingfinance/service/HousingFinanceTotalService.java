package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.dao.JpaTotalAmountDao;
import com.housing.finance.housingfinance.dto.ResTotalAmountDto;
import com.housing.finance.housingfinance.dto.ResTotalHousingFinanceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousingFinanceTotalService {

    private final JpaTotalAmountDao jpaTotalViewDao;

    public HousingFinanceTotalService(JpaTotalAmountDao jpaTotalViewDao) {
        this.jpaTotalViewDao = jpaTotalViewDao;
    }

    public ResTotalHousingFinanceDto getTotalAmountByYear() {
        List<ResTotalAmountDto> resTotalAmountDtoList = jpaTotalViewDao.selectGroupByYear();
       return ResTotalHousingFinanceDto.builder()
               .name("주택금융 공급현황")
               .amountList(resTotalAmountDtoList)
               .build();
    }
}

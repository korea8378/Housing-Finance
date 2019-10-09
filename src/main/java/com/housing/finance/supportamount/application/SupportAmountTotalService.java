package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dto.ResDetailTotalAmountDto;
import com.housing.finance.supportamount.dto.ResTotalAmountsDto;
import com.housing.finance.supportamount.infrastructure.JpaTotalAmountDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportAmountTotalService {

    private final JpaTotalAmountDao jpaTotalViewDao;

    public SupportAmountTotalService(JpaTotalAmountDao jpaTotalViewDao) {
        this.jpaTotalViewDao = jpaTotalViewDao;
    }

    public ResTotalAmountsDto getByYear() {
        List<ResDetailTotalAmountDto> resDetailTotalAmountDtos = jpaTotalViewDao.selectGroupByYear();

        return ResTotalAmountsDto.builder()
                .name("주택금융 공급현황")
                .amountList(resDetailTotalAmountDtos)
                .build();
    }
}

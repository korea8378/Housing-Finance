package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dto.ResDetailTotalAmountDto;
import com.housing.finance.supportamount.dto.ResTotalAmountsDto;
import com.housing.finance.supportamount.infrastructure.JpaTotalAmountDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportAmountTotalService {

    private final JpaTotalAmountDao jpaTotalViewDao;
    private final SupportAmountMapper supportAmountMapper;

    public SupportAmountTotalService(JpaTotalAmountDao jpaTotalViewDao,
                                     SupportAmountMapper supportAmountMapper) {
        this.jpaTotalViewDao = jpaTotalViewDao;
        this.supportAmountMapper = supportAmountMapper;
    }

    public ResTotalAmountsDto getByYear() {
        List<ResDetailTotalAmountDto> resDetailTotalAmountDtos = jpaTotalViewDao.selectGroupByYear();

        return supportAmountMapper.toResTotalAmountsDto(resDetailTotalAmountDtos);
    }
}

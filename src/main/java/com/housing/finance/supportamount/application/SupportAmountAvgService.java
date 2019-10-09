package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dao.AvgAmountDao;
import com.housing.finance.supportamount.dto.ResMaxMinAvgAMountDto;
import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;
import org.springframework.stereotype.Service;

@Service
public class SupportAmountAvgService {

    private final SupportAmountMapper supportAmountMapper;
    private final AvgAmountDao avgAmountDao;

    public SupportAmountAvgService(SupportAmountMapper supportAmountMapper, AvgAmountDao avgAmountDao) {
        this.supportAmountMapper = supportAmountMapper;
        this.avgAmountDao = avgAmountDao;
    }

    public ResMaxMinAvgAMountDto getMaxMinOfKoreaExchangeBank() {

        ResDetailAvgAmountDto max = avgAmountDao.selectMaxGroupByYear();
        ResDetailAvgAmountDto min = avgAmountDao.selectMinGroupByYear();

        return supportAmountMapper.toResMaxMinAvgAMountDto(max, min);
    }
}

package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dao.AvgAmountDao;
import com.housing.finance.supportamount.dto.ResMaxMinAvgAMountDto;
import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;
import org.springframework.stereotype.Service;

@Service
public class SupportAmountAvgService {

    private final AvgAmountDao avgAmountDao;

    public SupportAmountAvgService(AvgAmountDao avgAmountDao) {
        this.avgAmountDao = avgAmountDao;
    }

    public ResMaxMinAvgAMountDto getMaxMinOfKoreaExchangeBank() {

        ResDetailAvgAmountDto max = avgAmountDao.selectMaxGroupByYear();
        ResDetailAvgAmountDto min = avgAmountDao.selectMinGroupByYear();

        return new ResMaxMinAvgAMountDto(max, min);
    }

}

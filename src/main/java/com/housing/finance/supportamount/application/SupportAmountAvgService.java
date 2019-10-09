package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dao.AvgBankDao;
import com.housing.finance.supportamount.dto.ResMaxMinAvgAMountDto;
import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;
import org.springframework.stereotype.Service;

@Service
public class SupportAmountAvgService {

    private final SupportAmountMapper supportAmountMapper;
    private final AvgBankDao avgBankDao;

    public SupportAmountAvgService(SupportAmountMapper supportAmountMapper, AvgBankDao avgBankDao) {
        this.supportAmountMapper = supportAmountMapper;
        this.avgBankDao = avgBankDao;
    }

    public ResMaxMinAvgAMountDto getMaxMinOfKoreaExchangeBank() {

        ResDetailAvgAmountDto max = avgBankDao.selectMaxAvgAmountGroupByYear();
        ResDetailAvgAmountDto min = avgBankDao.selectMinAvgAmountGroupByYear();

        return supportAmountMapper.toResMaxMinAvgAMountDto(max, min);
    }
}

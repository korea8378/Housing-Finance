package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dao.MaxAmountDao;
import com.housing.finance.supportamount.dto.ResMaxAmountDto;
import org.springframework.stereotype.Service;

@Service
public class SupportAmountMaxService {

    private final MaxAmountDao maxAmountDao;

    public SupportAmountMaxService(MaxAmountDao maxAmountDao) {
        this.maxAmountDao = maxAmountDao;
    }

    public ResMaxAmountDto getOfBanks() {
        return maxAmountDao.selectGroupByYear();
    }
}

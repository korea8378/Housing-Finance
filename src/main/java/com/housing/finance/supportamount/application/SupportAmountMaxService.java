package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dao.MaxBankDao;
import com.housing.finance.supportamount.dto.ResMaxAmountDto;
import org.springframework.stereotype.Service;

@Service
public class SupportAmountMaxService {

    private final MaxBankDao maxBankDao;

    public SupportAmountMaxService(MaxBankDao maxBankDao) {
        this.maxBankDao = maxBankDao;
    }

    public ResMaxAmountDto getOfAllBank() {
        return maxBankDao.selectMaxAmountGroupByYear();
    }
}

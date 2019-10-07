package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.dao.MaximumBankDao;
import com.housing.finance.housingfinance.dto.ResMaximumOfBankDto;
import org.springframework.stereotype.Service;

@Service
public class MaximumOfBankService {

    private final MaximumBankDao maximumBankDao;

    public MaximumOfBankService(MaximumBankDao maximumBankDao) {
        this.maximumBankDao = maximumBankDao;
    }

    public ResMaximumOfBankDto getMaximumOfAllBank() {
        return maximumBankDao.selectMaximumAmountGroupByYear();
    }
}
